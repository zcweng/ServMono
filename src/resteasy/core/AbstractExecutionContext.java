package resteasy.core;

import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;
import resteasy.spi.ResteasyAsynchronousContext;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public abstract class AbstractExecutionContext implements ResteasyAsynchronousContext
{
   protected SynchronousDispatcher dispatcher;
   protected HttpRequest request;
   protected HttpResponse response;

   protected AbstractExecutionContext(SynchronousDispatcher dispatcher, HttpRequest request, HttpResponse response)
   {
      this.dispatcher = dispatcher;
      this.request = request;
      this.response = response;
   }


}
