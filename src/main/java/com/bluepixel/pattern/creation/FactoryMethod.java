/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Win Tun Lin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.bluepixel.pattern.creation;
/*
	- define interface of creating a object. let subclass decide which class to instantiate.
	- let a class defer instantiation to subclasses (dependency injection)
*/

interface Trace {
	// turn on and off debugging
	public void setDebug(boolean debug);
	// write out a debug message
	public void debug(String message);
	// write out an error message
	public void error(String message);
}

class FileTrace implements Trace {

	private java.io.PrintWriter pw;
	private boolean debug;
	public FileTrace() throws java.io.IOException {
		// a real FileTrace would need to obtain the filename somewhere
		// for the example I'll hardcode it
		pw = new java.io.PrintWriter( new java.io.FileWriter( "c:\trace.log" ) );
	}
	public void setDebug( boolean debug ) {
		this.debug = debug;
	}
	public void debug( String message ) {
		if( debug ) {  // only print if debug is true
			pw.println( "DEBUG: " + message );
			pw.flush();
		}
	}
	public void error( String message ) {
		// always print out errors
		pw.println( "ERROR: " + message );
		pw.flush();
	}
}
class SystemTrace implements Trace {
	private boolean debug;
	public void setDebug( boolean debug ) {
		this.debug = debug;
	}
	public void debug( String message ) {
		if( debug ) {  // only print if debug is true
			System.out.println( "DEBUG: " + message );
		}
	}
	public void error( String message ) {
		// always print out errors
		System.out.println( "ERROR: " + message );
	}
}


class TraceFactory {
	public static Trace getTrace() {
		try {
			return new FileTrace();
		} catch ( java.io.IOException ex ) {
			Trace t = new SystemTrace();
			t.error( "could not instantiate FileTrace: " + ex.getMessage() );
			return t;
		}
	}
}

public class FactoryMethod {
	public static void main(String[] args) {
		
		TraceFactory.getTrace().debug("debug_message");
	}
}


