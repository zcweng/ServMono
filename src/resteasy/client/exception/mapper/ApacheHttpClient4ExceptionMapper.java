package resteasy.client.exception.mapper;


import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ProtocolException;
import org.apache.http.UnsupportedHttpVersionException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.NonRepeatableRequestException;
import org.apache.http.client.RedirectException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.client.TunnelRefusedException;

import resteasy.client.exception.ResteasyAuthenticationException;
import resteasy.client.exception.ResteasyCircularRedirectException;
import resteasy.client.exception.ResteasyClientException;
import resteasy.client.exception.ResteasyClientProtocolException;
import resteasy.client.exception.ResteasyConnectTimeoutException;
import resteasy.client.exception.ResteasyConnectionClosedException;
import resteasy.client.exception.ResteasyConnectionPoolTimeoutException;
import resteasy.client.exception.ResteasyCookieRestrictionViolationException;
import resteasy.client.exception.ResteasyHttpException;
import resteasy.client.exception.ResteasyHttpHostConnectException;
import resteasy.client.exception.ResteasyIOException;
import resteasy.client.exception.ResteasyInvalidCredentialsException;
import resteasy.client.exception.ResteasyMalformedChallengeException;
import resteasy.client.exception.ResteasyMalformedChunkCodingException;
import resteasy.client.exception.ResteasyMalformedCookieException;
import resteasy.client.exception.ResteasyMethodNotSupportedException;
import resteasy.client.exception.ResteasyNTLMEngineException;
import resteasy.client.exception.ResteasyNoHttpResponseException;
import resteasy.client.exception.ResteasyNonRepeatableRequestException;
import resteasy.client.exception.ResteasyProtocolException;
import resteasy.client.exception.ResteasyRedirectException;
import resteasy.client.exception.ResteasyTunnelRefusedException;
import resteasy.client.exception.ResteasyUnsupportedHttpVersionException;


import java.io.IOException;

import javax.ws.rs.ext.Provider;

/**
 * 
 * @author <a href="ron.sigal@jboss.com">Ron Sigal</a>
 * @version $Revision: 1.1 $
 *
 * Copyright Jul 28, 2012
 */
@Provider
public class ApacheHttpClient4ExceptionMapper implements ClientExceptionMapper<Exception>
{
   @Override
   public ResteasyClientException toException(Exception exception)
   {
      if (exception instanceof IOException)
      {
         return mapIOException(IOException.class.cast(exception));
      }
      if (exception instanceof HttpException)
      {
         return mapHttpException(HttpException.class.cast(exception));
      }
      return new ResteasyClientException("Unexpected exception type", exception);
   }

   private ResteasyClientException mapIOException(IOException e)
   {
      if (ClientProtocolException.class.equals(e.getClass()))
      {
         return new ResteasyClientProtocolException(e);
      }
      if (ConnectionClosedException.class.equals(e.getClass()))
      {
         return new ResteasyConnectionClosedException(e);
      }
      if (ConnectionPoolTimeoutException.class.equals(e.getClass()))
      {
         return new ResteasyConnectionPoolTimeoutException(e);
      }
      if (ConnectTimeoutException.class.equals(e.getClass()))
      {
         return new ResteasyConnectTimeoutException(e);
      }
      if (HttpHostConnectException.class.equals(e.getClass()))
      {
         return new ResteasyHttpHostConnectException(e);
      }
      if (MalformedChunkCodingException.class.equals(e.getClass()))
      {
         return new ResteasyMalformedChunkCodingException(e);
      }
      if (NoHttpResponseException.class.equals(e.getClass()))
      {
         return new ResteasyNoHttpResponseException(e);
      }
      if (NoHttpResponseException.class.equals(e.getClass()))
      {
         return new ResteasyNoHttpResponseException(e);
      }
      return new ResteasyIOException("IOException", e);
   }
   
   private ResteasyClientException mapHttpException(HttpException e)
   {
      if (AuthenticationException.class.equals(e.getClass()))
      {
         return new ResteasyAuthenticationException(e);
      }
      if (CircularRedirectException.class.equals(e.getClass()))
      {
         return new ResteasyCircularRedirectException(e);
      }
//      if (CookieRestrictionViolationException.class.equals(e.getClass()))
//      {
//         return new ResteasyCookieRestrictionViolationException(e);
//      }
      if (InvalidCredentialsException.class.equals(e.getClass()))
      {
         return new ResteasyInvalidCredentialsException(e);
      }
      if (MalformedChallengeException.class.equals(e.getClass()))
      {
         return new ResteasyMalformedChallengeException(e);
      }
      if (MalformedCookieException.class.equals(e.getClass()))
      {
         return new ResteasyMalformedCookieException(e);
      }
      if (MethodNotSupportedException.class.equals(e.getClass()))
      {
         return new ResteasyMethodNotSupportedException(e);
      }
      if (NonRepeatableRequestException.class.equals(e.getClass()))
      {
         return new ResteasyNonRepeatableRequestException(e);
      }
      if (NTLMEngineException.class.equals(e.getClass()))
      {
         return new ResteasyNTLMEngineException(e);
      }
      if (ProtocolException.class.equals(e.getClass()))
      {
         return new ResteasyProtocolException(e);
      }
      if (RedirectException.class.equals(e.getClass()))
      {
         return new ResteasyRedirectException(e);
      }
      if (TunnelRefusedException.class.equals(e.getClass()))
      {
         return new ResteasyTunnelRefusedException(e);
      }
      if (UnsupportedHttpVersionException.class.equals(e.getClass()))
      {
         return new ResteasyUnsupportedHttpVersionException(e);
      }
      return new ResteasyHttpException("HttpException", e);
   }
}
