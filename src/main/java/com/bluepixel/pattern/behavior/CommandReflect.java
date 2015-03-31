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

package com.bluepixel.pattern.behavior;

import java.lang.reflect.*;

class Command {
	private Object   receiver;               // the "encapsulated" object
	private Method   action;                 // the "pre-registered" request
	private Object[] args;                   // the "pre-registered" arg list
	public Command( Object obj, String methodName, Object[] arguments ) {
		receiver = obj;
		args = arguments;
		Class<? extends Object> cls = obj.getClass();           // get the object's "Class"
		Class[] argTypes = new Class[args.length];
		for (int i=0; i < args.length; i++)   // get the "Class" for each
			argTypes[i] = args[i].getClass();  //    supplied argument
		// get the "Method" data structure with the correct name and signature
		try {      action = cls.getMethod( methodName, argTypes );      }
		catch( NoSuchMethodException e ) { System.out.println( e ); }
	}
	public Object execute() {
		// in C++, you do something like --- return receiver->action( args ); 
		try {     return action.invoke( receiver, args );     }
		catch( IllegalAccessException e    ) { System.out.println( e ); }
		catch( InvocationTargetException e ) { System.out.println( e ); }
		return null;
	}  
}

public class CommandReflect {
	private int state;
	public CommandReflect( int in ) {
		state = in;
	}
	public int addOne( Integer one ) {
		return state + one.intValue();
	}
	public int addTwo( Integer one, Integer two ) {
		return state + one.intValue() + two.intValue();
	}	

	public static void main( String[] args ) {
		CommandReflect[] objs = { new CommandReflect(1), new CommandReflect(2) };
		System.out.print( "Normal call results: " );
		System.out.print( objs[0].addOne( new Integer(3) ) + " " );
		System.out.print( objs[1].addTwo( new Integer(4),
				new Integer(5) ) + " " );
		Command[] cmds = {
				new Command( objs[0], "addOne", new Integer[] { new Integer(3) } ),
				new Command( objs[1], "addTwo", new Integer[] { new Integer(4),
						new Integer(5) } ) };
		System.out.print( "\nReflection results:  " );
		for (int i=0; i < cmds.length; i++)
			System.out.print( cmds[i].execute() + " " );
		System.out.println();
	}  }