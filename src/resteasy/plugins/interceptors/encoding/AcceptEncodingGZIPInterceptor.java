package resteasy.plugins.interceptors.encoding;


import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import resteasy.annotations.interception.ClientInterceptor;
import resteasy.annotations.interception.HeaderDecoratorPrecedence;
import resteasy.client.ClientResponse;
import resteasy.spi.interception.ClientExecutionContext;
import resteasy.spi.interception.ClientExecutionInterceptor;


/**
 * Must be used in conjunction with GZIPDecodingInterceptor
 * <p/>
 * Sets
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Provider
@ClientInterceptor
@HeaderDecoratorPrecedence
public class AcceptEncodingGZIPInterceptor implements ClientExecutionInterceptor
{
   public ClientResponse execute(ClientExecutionContext ctx) throws Exception
   {
      String encoding = ctx.getRequest().getHeaders().getFirst(HttpHeaders.ACCEPT_ENCODING);
      if (encoding == null)
      {
         ctx.getRequest().header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate");
      }
      else
      {
         if (!encoding.contains("gzip"))
         {
            encoding += ", gzip";
            ctx.getRequest().header(HttpHeaders.ACCEPT_ENCODING, encoding);
         }
      }
      return ctx.proceed();
   }
}
