package resteasy.core;


import javax.ws.rs.core.MediaType;

import resteasy.spi.LoggableFailure;
import resteasy.util.HttpResponseCodes;


@SuppressWarnings("serial")
public class NoMessageBodyWriterFoundFailure extends LoggableFailure
{

   public NoMessageBodyWriterFoundFailure(Class type, MediaType contentType)
   {
      super(
              String
                      .format(
                              "Could not find MessageBodyWriter for response object of type: %s of media type: %s",
                              type.getName(), contentType.toString()),
              HttpResponseCodes.SC_INTERNAL_SERVER_ERROR);
   }
}
