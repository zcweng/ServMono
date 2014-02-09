package resteasy.client.core.marshallers;

import resteasy.client.ClientRequest;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface Marshaller
{
   void build(ClientRequest request, Object target);
}