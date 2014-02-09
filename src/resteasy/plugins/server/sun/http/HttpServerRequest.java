package resteasy.plugins.server.sun.http;

import com.sun.net.httpserver.HttpExchange;



import java.io.InputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import resteasy.core.SynchronousDispatcher;
import resteasy.core.SynchronousExecutionContext;
import resteasy.plugins.server.BaseHttpRequest;
import resteasy.specimpl.ResteasyHttpHeaders;
import resteasy.spi.HttpResponse;
import resteasy.spi.NotImplementedYetException;
import resteasy.spi.ResteasyAsynchronousContext;
import resteasy.spi.ResteasyUriInfo;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class HttpServerRequest extends BaseHttpRequest
{
   protected HttpExchange exchange;
   protected ResteasyHttpHeaders httpHeaders;
   protected ResteasyUriInfo uriInfo;
   protected String preProcessedPath;
   protected Map<String, Object> attributes = new HashMap<String, Object>();
   protected String httpMethod;


   public HttpServerRequest(SynchronousDispatcher dispatcher, HttpResponse httpResponse, HttpExchange exchange)
   {
      super( dispatcher);
      this.httpResponse = httpResponse;
      this.exchange = exchange;
      this.uriInfo = HttpExchangeUtil.extractUriInfo(exchange);
      this.httpHeaders = HttpExchangeUtil.extractHttpHeaders(exchange);
      this.preProcessedPath = uriInfo.getPath(false);
      this.httpMethod = exchange.getRequestMethod().toUpperCase();
   }

   @Override
   public MultivaluedMap<String, String> getMutableHeaders()
   {
      return httpHeaders.getMutableHeaders();
   }

   @Override
   public void setRequestUri(URI requestUri) throws IllegalStateException
   {
      uriInfo = uriInfo.setRequestUri(requestUri);
   }

   @Override
   public void setRequestUri(URI baseUri, URI requestUri) throws IllegalStateException
   {
      uriInfo = new ResteasyUriInfo(baseUri.resolve(requestUri));
   }


   @Override
   public HttpHeaders getHttpHeaders()
   {
      return httpHeaders;
   }

   @Override
   public InputStream getInputStream()
   {
      return exchange.getRequestBody();
   }

   @Override
   public void setInputStream(InputStream stream)
   {
      exchange.setStreams(stream, exchange.getResponseBody());
   }

   @Override
   public ResteasyUriInfo getUri()
   {
      return uriInfo;
   }

   @Override
   public String getHttpMethod()
   {
      return httpMethod;
   }

   @Override
   public void setHttpMethod(String method)
   {
      this.httpMethod = method;
   }

   @Override
   public Object getAttribute(String attribute)
   {
      Object val = attributes.get(attribute);
      if (val != null) return val;
      return exchange.getAttribute(attribute);
   }

   @Override
   public void setAttribute(String name, Object value)
   {
      attributes.put(name, value);
      exchange.setAttribute(name, value);
   }

   @Override
   public void removeAttribute(String name)
   {
      attributes.remove(name);
      exchange.setAttribute(name, null);
   }

   @Override
   public Enumeration<String> getAttributeNames()
   {
      Enumeration<String> en = new Enumeration<String>()
      {
         private Iterator<String> it = attributes.keySet().iterator();
         @Override
         public boolean hasMoreElements()
         {
            return it.hasNext();
         }

         @Override
         public String nextElement()
         {
            return it.next();
         }
      };
      return en;
   }

   @Override
   public ResteasyAsynchronousContext getAsyncContext()
   {
      return new SynchronousExecutionContext(dispatcher, this, httpResponse);
   }

   @Override
   public void forward(String path)
   {
      throw new NotImplementedYetException();
   }

   @Override
   public boolean wasForwarded()
   {
      return false;
   }
}