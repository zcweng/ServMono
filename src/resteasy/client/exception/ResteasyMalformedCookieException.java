package resteasy.client.exception;

/**
 * @author <a href="ron.sigal@jboss.com">Ron Sigal</a>
 * @version $Revision: 1.1 $
 *
 * Copyright Jul 28, 2012
 */
public class ResteasyMalformedCookieException extends ResteasyProtocolException
{
	private static final long serialVersionUID = -5711578608757689465L;
	
	public ResteasyMalformedCookieException()
	{
	}

	public ResteasyMalformedCookieException(String message)
	{
		super(message);
    }
	
    public ResteasyMalformedCookieException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public ResteasyMalformedCookieException(Throwable cause)
    {
        super(cause);
    }
}
