package resteasy.spi.touri;


import java.lang.annotation.Annotation;

import resteasy.specimpl.ResteasyUriBuilder;


public class URITemplateAnnotationResolver extends
        AbstractURITemplateAnnotationResolver
{

   protected Class<? extends Annotation> getAnnotationType()
   {
      return URITemplate.class;
   }

   protected ResteasyUriBuilder getUriBuilder(Class<? extends Object> clazz)
   {
      String uriTemplate = clazz.getAnnotation(URITemplate.class).value();
      ResteasyUriBuilder uriBuilderImpl = new ResteasyUriBuilder();
      uriBuilderImpl.replacePath(uriTemplate);
      return uriBuilderImpl;
   }
}
