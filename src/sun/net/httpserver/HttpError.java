/*
 * @(#)HttpError.java	1.4 07/01/02
 *
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package sun.net.httpserver;

/**
 * A Http error
 */
class HttpError extends RuntimeException {
    public HttpError (String msg) {
	super (msg);
    }
}
