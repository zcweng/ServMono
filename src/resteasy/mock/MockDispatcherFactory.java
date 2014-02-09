package resteasy.mock;

import resteasy.core.Dispatcher;
import resteasy.core.SynchronousDispatcher;
import resteasy.plugins.providers.RegisterBuiltin;
import resteasy.spi.ResteasyProviderFactory;

/**
 * Creates a mock Dispatcher that you can invoke on locally
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class MockDispatcherFactory
{

   public static Dispatcher createDispatcher()
   {
      Dispatcher dispatcher = new SynchronousDispatcher(new ResteasyProviderFactory());
      ResteasyProviderFactory.setInstance(dispatcher.getProviderFactory());
      RegisterBuiltin.register(dispatcher.getProviderFactory());
      return dispatcher;
   }
}
