/*
 * @(#)DefaultHttpServerProvider.java	1.4 07/01/02
 *
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package sun.net.httpserver;

import java.net.*;
import java.io.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

public class DefaultHttpServerProvider extends HttpServerProvider {
    public HttpServer createHttpServer (InetSocketAddress addr, int backlog) throws IOException {
	return new HttpServerImpl (addr, backlog);
    }

    public HttpsServer createHttpsServer (InetSocketAddress addr, int backlog) throws IOException {
	return new HttpsServerImpl (addr, backlog);
    }
}
