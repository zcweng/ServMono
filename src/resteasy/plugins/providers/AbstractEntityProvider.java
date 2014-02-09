/**
 *
 */
package resteasy.plugins.providers;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * A AbstractEntityProvider.
 *
 * @param <T>
 * @author <a href="ryan@damnhandy.com>Ryan J. McDonough</a>
 * @version $Revision: $
 */
public abstract class AbstractEntityProvider<T>
        implements MessageBodyReader<T>, MessageBodyWriter<T>
{

   public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
   {
      return -1;
   }

}
