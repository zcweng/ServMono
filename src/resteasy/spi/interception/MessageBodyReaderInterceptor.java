package resteasy.spi.interception;


import java.io.IOException;

import javax.ws.rs.WebApplicationException;

/**
 * Wraps around invocations of MessageBodyReader.readFrom().
 *
 * @deprecated
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Deprecated
public interface MessageBodyReaderInterceptor
{
   /**
    * @param context
    * @return the object read
    * @throws IOException
    * @throws WebApplicationException
    */
   Object read(MessageBodyReaderContext context) throws IOException, WebApplicationException;

}