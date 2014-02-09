package resteasy.spi;


import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import resteasy.core.ValueInjector;
import resteasy.spi.metadata.Parameter;
import resteasy.spi.metadata.ResourceClass;
import resteasy.spi.metadata.ResourceConstructor;
import resteasy.spi.metadata.ResourceLocator;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface InjectorFactory
{
   ConstructorInjector createConstructor(Constructor constructor, ResteasyProviderFactory factory);
   PropertyInjector createPropertyInjector(Class resourceClass, ResteasyProviderFactory factory);
   ValueInjector createParameterExtractor(Class injectTargetClass, AccessibleObject injectTarget, Class type, Type genericType, Annotation[] annotations, ResteasyProviderFactory factory);
   ValueInjector createParameterExtractor(Class injectTargetClass, AccessibleObject injectTarget, Class type, Type genericType, Annotation[] annotations, boolean useDefault, ResteasyProviderFactory factory);

   ValueInjector createParameterExtractor(Parameter parameter, ResteasyProviderFactory providerFactory);

   MethodInjector createMethodInjector(ResourceLocator method, ResteasyProviderFactory factory);

   PropertyInjector createPropertyInjector(ResourceClass resourceClass, ResteasyProviderFactory providerFactory);

   ConstructorInjector createConstructor(ResourceConstructor constructor, ResteasyProviderFactory providerFactory);
}
