package resteasy.core;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.ws.rs.WebApplicationException;

import resteasy.plugins.providers.validation.ViolationsContainer;
import resteasy.spi.ApplicationException;
import resteasy.spi.BadRequestException;
import resteasy.spi.Failure;
import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;
import resteasy.spi.InternalServerErrorException;
import resteasy.spi.MethodInjector;
import resteasy.spi.ResteasyProviderFactory;
import resteasy.spi.metadata.MethodParameter;
import resteasy.spi.metadata.ResourceLocator;
import resteasy.spi.validation.GeneralValidator;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class MethodInjectorImpl implements MethodInjector
{
   protected ValueInjector[] params;
   protected ResteasyProviderFactory factory;
   protected ResourceLocator method;
   protected Method interfaceBasedMethod;
   protected boolean expectsBody;

   public MethodInjectorImpl(ResourceLocator resourceMethod, ResteasyProviderFactory factory)
   {
      this.factory = factory;
      this.method = resourceMethod;
      this.interfaceBasedMethod = findInterfaceBasedMethod(resourceMethod.getResourceClass().getClazz(), resourceMethod.getMethod());
      params = new ValueInjector[resourceMethod.getParams().length];
      int i = 0;
      for (MethodParameter parameter : resourceMethod.getParams())
      {
         params[i] = factory.getInjectorFactory().createParameterExtractor(parameter, factory);
         if (params[i] instanceof MessageBodyParameterInjector) expectsBody = true;
         i++;
      }
   }

   @Override
   public boolean expectsBody()
   {
      return expectsBody;
   }

   public static Method findInterfaceBasedMethod(Class root, Method method)
   {
      if (method.getDeclaringClass().isInterface() || root.isInterface()) return method;

      for (Class intf : root.getInterfaces())
      {
         try
         {
            return intf.getMethod(method.getName(), method.getParameterTypes());
         }
         catch (NoSuchMethodException ignored)
         {}
      }

      if (root.getSuperclass() == null || root.getSuperclass().equals(Object.class)) return method;
      return findInterfaceBasedMethod(root.getSuperclass(), method);

   }

   public ValueInjector[] getParams()
   {
      return params;
   }

   public Object[] injectArguments(HttpRequest input, HttpResponse response)
   {
      try
      {
         Object[] args = null;
         if (params != null && params.length > 0)
         {
            args = new Object[params.length];
            int i = 0;
            for (ValueInjector extractor : params)
            {
               args[i++] = extractor.inject(input, response);
            }
         }
         return args;
      }
      catch (WebApplicationException we)
      {
         throw we;
      }
      catch (Failure f)
      {
         throw f;
      }
      catch (Exception e)
      {
         BadRequestException badRequest = new BadRequestException("Failed processing arguments of " + method.toString(), e);
         badRequest.setLoggable(true);
         throw badRequest;
      }
   }

   public Object invoke(HttpRequest request, HttpResponse httpResponse, Object resource) throws Failure, ApplicationException
   {
      Object[] args = injectArguments(request, httpResponse);

      GeneralValidator validator = GeneralValidator.class.cast(request.getAttribute(GeneralValidator.class.getName()));
      if (validator != null)
      {
         validator.validateAllParameters(request, resource, method.getMethod(), args);
      }

      Method invokedMethod = method.getMethod();
      if (!invokedMethod.getDeclaringClass().isAssignableFrom(resource.getClass()))
      {
         // invokedMethod is for when the target object might be a proxy and
         // resteasy is getting the bean class to introspect.
         // In other words ResourceMethod.getMethod() does not have the same declared class as the proxy:
         // An example is a proxied Spring bean that is a resource
         // interface ProxiedInterface { String get(); }
         // @Path("resource") class MyResource implements ProxiedInterface {
         //     @GET String get() {...}
         // }
         //
         invokedMethod = interfaceBasedMethod;
      }

      Object result = null;
      try
      {
         result = invokedMethod.invoke(resource, args);
      }
      catch (IllegalAccessException e)
      {
         throw new InternalServerErrorException("Not allowed to reflect on method: " + method.toString(), e);
      }
      catch (InvocationTargetException e)
      {
         Throwable cause = e.getCause();
         throw new ApplicationException(cause);
      }
      catch (IllegalArgumentException e)
      {
         String msg = "Bad arguments passed to " + method.toString() + "  (";
         if (args != null)
         {
            boolean first = false;
            for (Object arg : args)
            {
               if (!first)
               {
                  first = true;
               }
               else
               {
                  msg += ",";
               }
               if (arg == null)
               {
                  msg += " null";
                  continue;
               }
               msg += " " + arg.getClass().getName() + " " + arg;
            }
         }
         msg += " )";
         throw new InternalServerErrorException(msg, e);
      }
      if (validator != null)
      {
         validator.validateReturnValue(request, resource, method.getMethod(), result);
      }
      return result;
   }

}
