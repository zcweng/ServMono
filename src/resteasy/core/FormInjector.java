package resteasy.core;


import java.lang.reflect.Constructor;

import resteasy.spi.ConstructorInjector;
import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;
import resteasy.spi.PropertyInjector;
import resteasy.spi.ResteasyProviderFactory;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class FormInjector implements ValueInjector
{
   private Class type;
   private ConstructorInjector constructorInjector;
   private PropertyInjector propertyInjector;

   public FormInjector(Class type, ResteasyProviderFactory factory)
   {
      this.type = type;
      Constructor<?> constructor = null;

      try
      {
         constructor = type.getConstructor();
      }
      catch (NoSuchMethodException e)
      {
         throw new RuntimeException("Unable to instantiate @Form class. No no-arg constructor.");
      }

      constructorInjector = factory.getInjectorFactory().createConstructor(constructor, factory);
      propertyInjector = factory.getInjectorFactory().createPropertyInjector(type, factory);

   }

   public Object inject()
   {
      throw new IllegalStateException("You cannot inject into a form outside the scope of an HTTP request");
   }

   public Object inject(HttpRequest request, HttpResponse response)
   {
      Object target = constructorInjector.construct();
      propertyInjector.inject(request, response, target);
      return target;
   }
}
