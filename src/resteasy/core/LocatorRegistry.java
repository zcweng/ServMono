package resteasy.core;


import java.lang.reflect.Proxy;
import java.util.List;

import resteasy.core.registry.RootNode;
import resteasy.specimpl.ResteasyUriBuilder;
import resteasy.spi.HttpRequest;
import resteasy.spi.InjectorFactory;
import resteasy.spi.ResourceFactory;
import resteasy.spi.ResteasyProviderFactory;
import resteasy.spi.metadata.ResourceBuilder;
import resteasy.spi.metadata.ResourceClass;
import resteasy.spi.metadata.ResourceLocator;
import resteasy.spi.metadata.ResourceMethod;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class LocatorRegistry
{
   protected RootNode root = new RootNode();
   protected ResteasyProviderFactory providerFactory;

   public LocatorRegistry(Class<?> clazz, ResteasyProviderFactory providerFactory)
   {
      this.providerFactory = providerFactory;
      if (Proxy.isProxyClass(clazz))
      {
         for (Class<?> intf : clazz.getInterfaces())
         {
            ResourceClass resourceClass = ResourceBuilder.locatorFromAnnotations(intf);
            register(resourceClass);
         }
      }
      else
      {
         ResourceClass resourceClass = ResourceBuilder.locatorFromAnnotations(clazz);
         register(resourceClass);
      }
   }

   public void register(ResourceClass resourceClass)
   {
      for (ResourceMethod method : resourceClass.getResourceMethods())
      {
         processMethod(method);
      }
      for (ResourceLocator method : resourceClass.getResourceLocators())
      {
         processMethod(method);
      }

   }

   protected void processMethod(ResourceLocator method)
   {
      String fullpath = method.getFullpath() == null ? "" : method.getFullpath();
      InjectorFactory injectorFactory = providerFactory.getInjectorFactory();
      if (method instanceof ResourceMethod)
      {
         ResourceMethodInvoker invoker = new ResourceMethodInvoker((ResourceMethod)method, injectorFactory, null, providerFactory);
         root.addInvoker(fullpath, invoker);
      }
      else
      {
         ResourceLocatorInvoker locator = new ResourceLocatorInvoker(null, injectorFactory, providerFactory, method);
         root.addInvoker(fullpath, locator);
      }
   }

   public ResourceInvoker getResourceInvoker(HttpRequest request)
   {
      try
      {
         String currentUri = request.getUri().getEncodedMatchedPaths().get(0);
         int startAt = currentUri.length();
         return root.match(request, startAt);
      }
      catch (RuntimeException e)
      {
         throw e;
      }
   }
}
