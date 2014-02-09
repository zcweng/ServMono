package resteasy.spi.interception;

import resteasy.client.ClientRequest;
import resteasy.client.ClientResponse;

/**
 * @deprecated
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Deprecated
public interface ClientExecutionContext
{
   ClientRequest getRequest();

   ClientResponse proceed() throws Exception;
}
