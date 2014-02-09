/*
 * @(#)HttpsServerImpl.java	1.4 07/01/02
 *
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package sun.net.httpserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

public class HttpsServerImpl extends HttpsServer {

    ServerImpl server;

    HttpsServerImpl () throws IOException {
	this (new InetSocketAddress(443), 0);
    }

    HttpsServerImpl (
	InetSocketAddress addr, int backlog
    ) throws IOException {
	server = new ServerImpl (this, "https", addr, backlog);
    }

    public void setHttpsConfigurator (HttpsConfigurator config) {
	server.setHttpsConfigurator (config);
    }

    public HttpsConfigurator getHttpsConfigurator () {
	return server.getHttpsConfigurator();
    }

    public void bind (InetSocketAddress addr, int backlog) throws IOException {
	server.bind (addr, backlog);
    }
	
    public void start () {
	server.start();
    }

    public void setExecutor (Executor executor) {
	server.setExecutor(executor);
    }

    public Executor getExecutor () {
	return server.getExecutor();
    }

    public void stop (int delay) {
	server.stop (delay);
    }

    public HttpContextImpl createContext (String path, HttpHandler handler) {
	return server.createContext (path, handler);
    }

    public HttpContextImpl createContext (String path) {
	return server.createContext (path);
    }

    public void removeContext (String path) throws IllegalArgumentException {
	server.removeContext (path);
    }

    public void removeContext (HttpContext context) throws IllegalArgumentException {
	server.removeContext (context);
    }

    public InetSocketAddress getAddress() {
	return server.getAddress();
    }
}
