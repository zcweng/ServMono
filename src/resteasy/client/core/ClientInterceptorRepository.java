package resteasy.client.core;



import java.util.LinkedList;

import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.WriterInterceptor;

import resteasy.spi.interception.ClientExecutionInterceptor;

/**
 * @author <a href="mailto:sduskis@gmail.com">Solomon Duskis</a>
 * @version $Revision: 1 $
 */
public interface ClientInterceptorRepository
{

   LinkedList<ReaderInterceptor> getReaderInterceptorList();

   LinkedList<WriterInterceptor> getWriterInterceptorList();

   LinkedList<ClientExecutionInterceptor> getExecutionInterceptorList();

   void registerInterceptor(Object interceptor);
}
