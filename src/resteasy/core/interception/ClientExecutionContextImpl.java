package resteasy.core.interception;


import java.util.List;

import resteasy.client.ClientExecutor;
import resteasy.client.ClientRequest;
import resteasy.client.ClientResponse;
import resteasy.spi.interception.ClientExecutionContext;
import resteasy.spi.interception.ClientExecutionInterceptor;


/**
 * @deprecated
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Deprecated
public class ClientExecutionContextImpl implements ClientExecutionContext
{
   protected List<ClientExecutionInterceptor> interceptors;
   protected ClientExecutor executor;
   protected ClientRequest request;
   protected int index = 0;

   public ClientExecutionContextImpl(List<ClientExecutionInterceptor> interceptors, ClientExecutor executor, ClientRequest request)
   {
      this.interceptors = interceptors;
      this.executor = executor;
      this.request = request;
   }

   public ClientRequest getRequest()
   {
      return request;
   }

   @SuppressWarnings("unchecked")
   public ClientResponse proceed() throws Exception
   {
      if (index >= interceptors.size())
      {
         return executor.execute(request);
      }
      else
      {
         try
         {
            return interceptors.get(index++).execute(this);
         }
         finally
         {
            index--;
         }
      }
   }
}