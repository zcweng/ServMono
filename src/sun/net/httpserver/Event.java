/*
 * @(#)Event.java	1.4 07/01/02
 *
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package sun.net.httpserver;


class Event {

    ExchangeImpl exchange;

    protected Event (ExchangeImpl t) {
	this.exchange = t;
    }
}

class WriteFinishedEvent extends Event {
    WriteFinishedEvent (ExchangeImpl t) {
	super (t);
    }
}
