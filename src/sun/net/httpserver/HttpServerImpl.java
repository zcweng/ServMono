/*
 * @(#)HttpServerImpl.java	1.4 07/01/02
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
import com.sun.net.httpserver.HttpServer;

public class HttpServerImpl extends HttpServer {

    ServerImpl server;

    HttpServerImpl () throws IOException {
	this (new InetSocketAddress(80), 0);
    }

    HttpServerImpl (
	InetSocketAddress addr, int backlog
    ) throws IOException {
	server = new ServerImpl (this, "http", addr, backlog);
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
