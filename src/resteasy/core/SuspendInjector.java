package resteasy.core;


import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import resteasy.annotations.Suspend;
import resteasy.spi.AsynchronousResponse;
import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;
import resteasy.spi.ResteasyAsynchronousContext;
import resteasy.spi.ResteasyAsynchronousResponse;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class SuspendInjector implements ValueInjector
{
   private long suspend;

   public SuspendInjector(long suspend, Class type)
   {
      if (!type.equals(AsynchronousResponse.class))
         throw new IllegalArgumentException(type.getName() + " is not a valid injectable type for @Suspend");
      this.suspend = suspend;
   }

   public Object inject()
   {
      throw new IllegalStateException("You cannot inject into a form outside the scope of an HTTP request");
   }

   public Object inject(HttpRequest request, HttpResponse response)
   {
      final ResteasyAsynchronousContext asynchronousContext = request.getAsyncContext();
      final ResteasyAsynchronousResponse asynchronousResponse = asynchronousContext.suspend(suspend);
      ResourceMethodInvoker invoker = (ResourceMethodInvoker)request.getAttribute(ResourceMethodInvoker.class.getName());
      invoker.initializeAsync(asynchronousResponse);
      return new AsynchronousResponse()
      {
         @Override
         public void setResponse(Response response)
         {
            asynchronousResponse.resume(response);
         }
      };
   }
}