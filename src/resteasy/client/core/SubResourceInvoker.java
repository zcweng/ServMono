package resteasy.client.core;



import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import resteasy.client.ProxyBuilder;
import resteasy.client.ProxyConfig;
import resteasy.client.ProxyFactory;

public class SubResourceInvoker implements MethodInvoker
{
	final ProxyConfig config;
	final Class<?> iface;
	final String base;
	final String format;
	
	public SubResourceInvoker(URI uri, Method method, ProxyConfig config)
	{
		String base = uri.toString();
		if (!base.endsWith("/"))
			base = base + "/";
		this.base = base;
		this.iface = method.getReturnType();
		this.config = config;
		String path = method.getAnnotation(Path.class).value();
		if (path.startsWith("/"))
			path = path.substring(1);
		Annotation[][] params = method.getParameterAnnotations();
		int index = 1;
		for (Annotation[] param : params)
		{
			for (Annotation a : param)
			{
				if (a instanceof PathParam)
				{
					String name = ((PathParam) a).value();
					path = path.replace("{" + name + "}", "%" + index + "$s");
					break;
				}
			}
			index++;
		}
		this.format = path;
	}

	@Override
	public Object invoke(Object[] args)
	{
		String path = String.format(format, args);
		return ProxyBuilder.createProxy(iface, ProxyFactory.createUri(base + path), config);
	}
}
