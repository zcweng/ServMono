package resteasy.spi.interception;


import java.io.IOException;

import javax.ws.rs.WebApplicationException;

/**
 * Wraps around invocations of MessageBodyWriter.writeTo()
 *
 * @deprecated
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Deprecated
public interface MessageBodyWriterInterceptor
{
   void write(MessageBodyWriterContext context) throws IOException, WebApplicationException;

}
