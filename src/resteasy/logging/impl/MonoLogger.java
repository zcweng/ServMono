/**
 * 
 */
package resteasy.logging.impl;

import android.util.Log;

/**
 * @author Administrator
 *
 */
public class MonoLogger extends resteasy.logging.Logger{

   private String classname;

   public MonoLogger(String classname)
   {
      this.classname = classname;
   }
	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void trace(String message) {
		// TODO Auto-generated method stub
		Log.v(classname, message);
	}

	@Override
	public void trace(String message, Object... params) {
		// TODO Auto-generated method stub
		Log.v(classname, message);
	}

	@Override
	public void trace(String message, Throwable error) {
		// TODO Auto-generated method stub
		Log.v(classname, message, error);
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		Log.d(classname, message);
	}

	@Override
	public void debug(String message, Object... params) {
		// TODO Auto-generated method stub
		Log.d(classname, message);
	}

	@Override
	public void debug(String message, Throwable error) {
		// TODO Auto-generated method stub
		Log.d(classname, message, error);
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		Log.i(classname, message);
	}

	@Override
	public void info(String message, Object... params) {
		// TODO Auto-generated method stub
		Log.i(classname, message);
	}

	@Override
	public void info(String message, Throwable error) {
		// TODO Auto-generated method stub
		Log.i(classname, message, error);
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		Log.w(classname, message);
	}

	@Override
	public void warn(String message, Object... params) {
		// TODO Auto-generated method stub
		Log.w(classname, message);
	}

	@Override
	public void warn(String message, Throwable error) {
		// TODO Auto-generated method stub
		Log.w(classname, message, error);
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		Log.e(classname, message);
	}

	@Override
	public void error(String message, Object... params) {
		// TODO Auto-generated method stub
		Log.e(classname, message);
	}

	@Override
	public void error(String message, Throwable error) {
		// TODO Auto-generated method stub
		Log.e(classname, message, error);
	}

}
