package resteasy.spi;


import java.util.concurrent.TimeUnit;

import javax.ws.rs.container.AsyncResponse;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface ResteasyAsynchronousContext
{
   boolean isSuspended();
   ResteasyAsynchronousResponse getAsyncResponse();

   ResteasyAsynchronousResponse suspend() throws IllegalStateException;

   ResteasyAsynchronousResponse suspend(long millis) throws IllegalStateException;

   ResteasyAsynchronousResponse suspend(long time, TimeUnit unit) throws IllegalStateException;
}
