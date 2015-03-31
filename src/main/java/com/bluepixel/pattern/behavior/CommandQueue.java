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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class CommandQueue {

	interface Command { 
		void execute(); 
	}

	static class DomesticEngineer implements Command {
		public void execute() {
			System.out.println( "take out the trash" );
		}  
	}

	static class Politician implements Command {
		public void execute() {
			System.out.println( "take money from the rich, take votes from the poor" );
		}
	}

	static class Programmer implements Command {
		public void execute() {
			System.out.println( "sell the bugs, charge extra for the fixes" );
		}
	}

	public static List<Command> produceRequests() {
		List<Command> queue = new ArrayList<Command>();
		queue.add( new DomesticEngineer() );
		queue.add( new Politician() );
		queue.add( new Programmer() );
		return queue;
	}

	public static void workOffRequests( List<Command> queue ) {
		/*
		for (Iterator<Command> it = (Iterator<Command>)queue.iterator(); ((java.util.Iterator) it).hasNext(); )
			((Command)((java.util.Iterator) it).next()).execute();*/
		for(Command command : queue) {
			command.execute();
		}
	}

	public static void main(String[] args) {
		List<Command> queue = produceRequests();
		workOffRequests( queue );
	}
}
