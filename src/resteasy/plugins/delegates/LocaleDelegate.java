package resteasy.plugins.delegates;



import java.util.Locale;

import javax.ws.rs.ext.RuntimeDelegate;

import resteasy.util.LocaleHelper;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class LocaleDelegate implements RuntimeDelegate.HeaderDelegate<Locale>
{
   public Locale fromString(String value) throws IllegalArgumentException
   {
      if (value == null) throw new IllegalArgumentException("Locale value is null");
      return LocaleHelper.extractLocale(value);
   }

   public String toString(Locale value)
   {
      if (value == null) throw new IllegalArgumentException("param was null");
      return LocaleHelper.toLanguageString(value);
   }

}
