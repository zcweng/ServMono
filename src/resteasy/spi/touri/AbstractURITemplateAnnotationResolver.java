package resteasy.spi.touri;

import java.lang.annotation.Annotation;

import resteasy.specimpl.ResteasyUriBuilder;
import resteasy.util.AnnotationResolver;



public abstract class AbstractURITemplateAnnotationResolver implements
        URIResolver
{

   @SuppressWarnings("unchecked")
   public boolean handles(Class type)
   {
      return AnnotationResolver.getClassWithAnnotation(type, getAnnotationType()) != null;
   }

   @SuppressWarnings("unchecked")
   public String resolveURI(Object object)
   {
//      Class<? extends Object> clazz = AnnotationResolver
//              .getClassWithAnnotation(object.getClass(), getAnnotationType());
//      ResteasyUriBuilder uriBuilderImpl = getUriBuilder(clazz);
//      Map<String, PropertyDescriptor> descriptors = getPropertyDescriptors(clazz);
//      List<Object> values = getValues(object, descriptors, uriBuilderImpl
//              .getPathParamNamesInDeclarationOrder());
//      return uriBuilderImpl.build(values.toArray()).toString();
	   throw new RuntimeException("resolveURI");
   }

   protected abstract Class<? extends Annotation> getAnnotationType();

   protected abstract ResteasyUriBuilder getUriBuilder(Class<? extends Object> clazz);

//   private List<Object> getValues(Object object,
//                                  Map<String, PropertyDescriptor> descriptors, List<String> params)
//   {
//      List<Object> values = new ArrayList<Object>();
//      for (String param : params)
//      {
//         PropertyDescriptor propertyDescriptor = descriptors.get(param);
//         if (propertyDescriptor == null)
//         {
//            throw new RuntimeException(
//                    "URITemplateAnnotationResolver could not find a getter for param "
//                            + param);
//         }
//
//         Method readMethod = propertyDescriptor.getReadMethod();
//         if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
//         {
//            readMethod.setAccessible(true);
//         }
//
//         try
//         {
//            values.add(readMethod.invoke(object, new Object[0]));
//         }
//         catch (Exception e)
//         {
//            throw new RuntimeException(
//                    "URITemplateAnnotationResolver could not get a value for "
//                            + param, e);
//         }
//      }
//      return values;
//	   throw new RuntimeException("getValues");
//   }

//   private Map<String, PropertyDescriptor> getPropertyDescriptors(
//           Class<? extends Object> clazz)
//   {
//      try
//      {
//         BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
//         HashMap<String, PropertyDescriptor> results = new HashMap<String, PropertyDescriptor>();
//         PropertyDescriptor[] propertyDescriptors = beanInfo
//                 .getPropertyDescriptors();
//         for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
//         {
//            results.put(propertyDescriptor.getName(), propertyDescriptor);
//         }
//         return results;
//      }
//      catch (IntrospectionException e)
//      {
//         throw new RuntimeException(
//                 "URITemplateAnnotationResolver could not introspect class "
//                         + clazz.getName(), e);
//      }
//   }
}
