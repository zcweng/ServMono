package resteasy.plugins.server;



import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import resteasy.core.SynchronousDispatcher;
import resteasy.plugins.providers.FormUrlEncodedProvider;
import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;
import resteasy.util.Encode;

/**
 * Helper for creating HttpRequest implementations.  The async code is a fake implementation to work with
 * http engines that don't support async HTTP.
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public abstract class BaseHttpRequest implements HttpRequest
{
   protected SynchronousDispatcher dispatcher;
   protected MultivaluedMap<String, String> formParameters;
   protected MultivaluedMap<String, String> decodedFormParameters;
   protected HttpResponse httpResponse;

   public BaseHttpRequest(SynchronousDispatcher dispatcher)
   {
      this.dispatcher = dispatcher;
   }

   public MultivaluedMap<String, String> getFormParameters()
   {
      if (formParameters != null) return formParameters;
      if (getHttpHeaders().getMediaType().isCompatible(MediaType.valueOf("application/x-www-form-urlencoded")))
      {
         try
         {
            formParameters = FormUrlEncodedProvider.parseForm(getInputStream());
         }
         catch (IOException e)
         {
            throw new RuntimeException(e);
         }
      }
      else
      {
         throw new IllegalArgumentException("Request media type is not application/x-www-form-urlencoded");
      }
      return formParameters;
   }

   public MultivaluedMap<String, String> getDecodedFormParameters()
   {
      if (decodedFormParameters != null) return decodedFormParameters;
      decodedFormParameters = Encode.decode(getFormParameters());
      return decodedFormParameters;
   }

   public boolean isInitial()
   {
      return true;
   }

}
