package resteasy.spi.interception;

import resteasy.client.ClientResponse;

/**
 * @deprecated
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Deprecated
public interface ClientExecutionInterceptor
{
   ClientResponse execute(ClientExecutionContext ctx) throws Exception;
}
