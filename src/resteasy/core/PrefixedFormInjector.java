package resteasy.core;



import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import resteasy.spi.HttpRequest;
import resteasy.spi.HttpResponse;
import resteasy.spi.ResteasyProviderFactory;
import resteasy.util.PrefixedFormFieldsHttpRequest;

/**
 * Extension of {@link FormInjector} that handles prefixes for associated classes.
 */
public class PrefixedFormInjector extends FormInjector
{

   private final String prefix;

   /**
    * Constructor setting the prefix.
    */
   public PrefixedFormInjector(Class type, String prefix, ResteasyProviderFactory factory)
   {
      super(type, factory);
      this.prefix = prefix;
   }

   /**
    * {@inheritDoc} Wraps the request in a
    */
   @Override
   public Object inject(HttpRequest request, HttpResponse response)
   {
      if (!containsPrefixedFormFieldsWithValue(request.getDecodedFormParameters()))
      {
         return null;
      }
      return doInject(prefix, request, response);
   }

   /**
    * Calls the super {@link #inject(resteasy.spi.HttpRequest, resteasy.spi.HttpResponse)} method.
    */
   protected Object doInject(String prefix, HttpRequest request, HttpResponse response)
   {
      return super.inject(new PrefixedFormFieldsHttpRequest(prefix, request), response);
   }

   /**
    * Checks to see if the decodedParameters contains any form fields starting with the prefix. Also checks if the value is not empty.
    */
   private boolean containsPrefixedFormFieldsWithValue(MultivaluedMap<String, String> decodedFormParameters)
   {
      for (String parameterName : decodedFormParameters.keySet())
      {
         if (parameterName.startsWith(prefix))
         {
            if (hasValue(decodedFormParameters.get(parameterName)))
            {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Checks that the list has an non empty value.
    */
   protected boolean hasValue(List<String> list)
   {
      return !list.isEmpty() && list.get(0).length() > 0;
   }
}