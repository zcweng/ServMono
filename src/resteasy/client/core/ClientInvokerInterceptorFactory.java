package resteasy.client.core;


import java.lang.reflect.Method;

import resteasy.spi.ResteasyProviderFactory;


@SuppressWarnings("unchecked")
public class ClientInvokerInterceptorFactory
{
   public static void applyDefaultInterceptors(
           ClientInterceptorRepositoryImpl repository,
           ResteasyProviderFactory providerFactory)
   {
      applyDefaultInterceptors(repository, providerFactory, null, null);
   }

   public static void applyDefaultInterceptors(
           ClientInterceptorRepositoryImpl repository,
           ResteasyProviderFactory providerFactory, Class declaring, Method method)
   {
      repository.setReaderInterceptors(providerFactory
              .getClientReaderInterceptorRegistry().postMatch(declaring,
                      method));
      repository.setWriterInterceptors(providerFactory
              .getClientWriterInterceptorRegistry().postMatch(declaring,
                      method));
      repository.setExecutionInterceptors(providerFactory
              .getClientExecutionInterceptorRegistry().bind(declaring, method));
   }

}
