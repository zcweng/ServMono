package resteasy.client.core.marshallers;


import java.util.Collection;

import resteasy.client.core.ClientInvoker;
import resteasy.client.core.ClientInvokerModifier;


/**
 * implemented by every generated proxy
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface ResteasyClientProxy
{
   Collection<ClientInvoker> getResteasyClientInvokers();

   void applyClientInvokerModifier(ClientInvokerModifier modifier);
   
   <T> T as(Class<T> iface);
}
