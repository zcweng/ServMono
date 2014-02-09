package resteasy.logging;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import resteasy.logging.impl.MonoLogger;


/**
 * Logging abstraction.  Call setLoggerType() to the logging framework you want to use.
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public abstract class Logger
{
   public static enum LoggerType
   {
      JUL,
      LOG4J,
      SLF4J,
      ANDROID
   }

   /**
    * Set this variable to set what logger you want.  The default is java.util.logging
    */
   private static Constructor loggerConstructor = null;

   public static void setLoggerType(LoggerType loggerType)
   {
      try
      {
         Class loggerClass = null;
         switch(loggerType){
         case JUL:
        	 loggerClass = Logger.class.getClassLoader().loadClass("resteasy.logging.impl.JULLogger");
        	 break;
         case  LOG4J:
        	 loggerClass = Logger.class.getClassLoader().loadClass("resteasy.logging.impl.Log4jLogger");
        	 break;
         case SLF4J:
        	 loggerClass = Logger.class.getClassLoader().loadClass("resteasy.logging.impl.Slf4jLogger");
        	 break;
         case ANDROID:
        	 loggerClass = MonoLogger.class;
        	 break;
         default:
    		 throw new RuntimeException("Could not match up an implementation for LoggerType: " + loggerType);
         }
         loggerConstructor = loggerClass.getDeclaredConstructor(String.class);
      }
      catch (ClassNotFoundException e)
      {
         throw new RuntimeException(e);
      }
      catch (NoSuchMethodException e)
      {
         throw new RuntimeException(e);
      }

   }

   private static boolean classInClasspath(String className)
   {
      try
      {
         return Thread.currentThread().getContextClassLoader().loadClass(className) != null;
      }
      catch (ClassNotFoundException e)
      {
         return false;
      }

   }

   static
   {
      LoggerType type = LoggerType.ANDROID;

      if (classInClasspath("org.apache.log4j.Logger"))
      {
         type = LoggerType.LOG4J;
      }
      else if (classInClasspath("org.slf4j.Logger"))
      {
         type = LoggerType.SLF4J;
      }
      else if (classInClasspath("org.slf4j.Logger"))
      {
          type = LoggerType.SLF4J;
       }

      setLoggerType(type);
   }


   public static Logger getLogger(Class<?> clazz)
   {
      try
      {
         return (Logger) loggerConstructor.newInstance(clazz.getName());
      }
      catch (InstantiationException e)
      {
         throw new RuntimeException(e);
      }
      catch (IllegalAccessException e)
      {
         throw new RuntimeException(e);
      }
      catch (InvocationTargetException e)
      {
         throw new RuntimeException(e);
      }
   }


   public abstract boolean isTraceEnabled();

   public abstract void trace(String message);

   public abstract void trace(String message, Object... params);

   public abstract void trace(String message, Throwable error);

   public abstract boolean isDebugEnabled();

   public abstract void debug(String message);

   public abstract void debug(String message, Object... params);

   public abstract void debug(String message, Throwable error);

   public abstract void info(String message);

   public abstract void info(String message, Object... params);

   public abstract void info(String message, Throwable error);

   public abstract boolean isWarnEnabled();

   public abstract void warn(String message);

   public abstract void warn(String message, Object... params);

   public abstract void warn(String message, Throwable error);

   public abstract void error(String message);

   public abstract void error(String message, Object... params);

   public abstract void error(String message, Throwable error);
}
