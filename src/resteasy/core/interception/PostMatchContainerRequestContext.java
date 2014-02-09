package resteasy.core.interception;


import java.net.URI;

import resteasy.core.ResourceMethodInvoker;
import resteasy.spi.HttpRequest;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class PostMatchContainerRequestContext extends PreMatchContainerRequestContext
{
   protected final ResourceMethodInvoker resourceMethod;

   public PostMatchContainerRequestContext(HttpRequest request,ResourceMethodInvoker resourceMethod)
   {
      super(request);
      this.resourceMethod = resourceMethod;
   }

   public ResourceMethodInvoker getResourceMethod()
   {
      return resourceMethod;
   }

   @Override
   public void setMethod(String method)
   {
      throw new IllegalStateException("Can't set method after match");
   }

   @Override
   public void setRequestUri(URI requestUri) throws IllegalStateException
   {
      throw new IllegalStateException("Can't set URI after match");
   }

   @Override
   public void setRequestUri(URI baseUri, URI requestUri) throws IllegalStateException
   {
      throw new IllegalStateException("Can't set URI after match");
   }
}
