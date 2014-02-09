package resteasy.core;


import java.lang.reflect.Method;

import resteasy.specimpl.BuiltResponse;
import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface ResourceInvoker
{
   BuiltResponse invoke(HttpRequest request, HttpResponse response);
   BuiltResponse invoke(HttpRequest request, HttpResponse response, Object target);

   Method getMethod();
}
