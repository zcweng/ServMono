package resteasy.client.core.extractors;


import java.net.URI;

import resteasy.client.ClientRequest;
import resteasy.client.core.BaseClientResponse;


public class ClientRequestContext
{
   private ClientRequest request;
   private BaseClientResponse<?> clientResponse;
   private ClientErrorHandler errorHandler;
   private EntityExtractorFactory extractorFactory;
   private URI baseURI;

   public ClientRequestContext(ClientRequest request, BaseClientResponse<?> clientResponse,
                               ClientErrorHandler errorHandler, EntityExtractorFactory extractorFactory, URI baseURI)
   {
      this.request = request;
      this.clientResponse = clientResponse;
      this.errorHandler = errorHandler;
      this.extractorFactory = extractorFactory;
      this.baseURI = baseURI;
   }

   public ClientRequest getRequest()
   {
      return request;
   }

   public BaseClientResponse getClientResponse()
   {
      return clientResponse;
   }

   public ClientErrorHandler getErrorHandler()
   {
      return errorHandler;
   }

   public EntityExtractorFactory getExtractorFactory()
   {
      return extractorFactory;
   }

   public URI getBaseURI()
   {
      return baseURI;
   }
}
