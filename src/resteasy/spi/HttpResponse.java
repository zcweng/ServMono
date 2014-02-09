package resteasy.spi;


import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;

/**
 * Bridge interface between the base Resteasy JAX-RS implementation and the actual HTTP transport (i.e. a servlet container)
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface HttpResponse
{
   int getStatus();

   public void setStatus(int status);

   MultivaluedMap<String, Object> getOutputHeaders();

   OutputStream getOutputStream() throws IOException;
   void setOutputStream(OutputStream os);

   void addNewCookie(NewCookie cookie);

   void sendError(int status) throws IOException;

   void sendError(int status, String message) throws IOException;

   boolean isCommitted();

   /**
    * reset status and headers.  Will fail if response is committed
    */
   void reset();

}
