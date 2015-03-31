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

package com.bluepixel.pattern.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

interface SocketInterface {
	String readLine();
	void  writeLine(String str);
	void  dispose();
}

class SocketProxy implements SocketInterface {
	// 1. Create a "wrapper" for a remote,
	// or expensive, or sensitive target
	private Socket      socket;
	private BufferedReader in;
	private PrintWriter   out;

	public SocketProxy( String host, int port, boolean wait ) {
		try {
			if (wait) {
				// 2. Encapsulate the complexity/overhead of the target in the wrapper
				ServerSocket server = new ServerSocket( port );
				socket = server.accept();
			} else
				socket = new Socket( host, port );
			in  = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter( socket.getOutputStream(), true );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
	public String readLine() {
		String str = null;
		try {
			str = in.readLine();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return str;
	}
	public void writeLine( String str ) {
		// 4. The wrapper delegates to the target
		out.println( str );
	}
	public void dispose() {
		try {
			socket.close();
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
class Read {

	public static String aString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
public class ProxyExample4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SocketInterface socket = new SocketProxy( "127.0.0.1", 8189,
				args[0].equals("first") ? true : false );
		String  str = null;
		boolean skip = true;
		while (true) {
			if (args[0].equals("second")  &&  skip) {
				skip = ! skip;
			}
			else {
				str = socket.readLine();
				System.out.println( "Receive - " + str );  // java ProxyDemo first
				if (str.equals("quit")) break;             // Receive - 123 456
			}                                            // Send ---- 234 567
			System.out.print( "Send ---- " );            // Receive - 345 678
			str = Read.aString();                        //
			socket.writeLine( str );                     // java ProxyDemo second
			if (str.equals("quit")) break;               // Send ---- 123 456
		}                                              // Receive - 234 567
		socket.dispose();                              // Send ---- 345 678
	}
}
