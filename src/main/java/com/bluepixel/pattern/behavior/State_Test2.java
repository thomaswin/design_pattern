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

import java.io.IOException;
import java.io.InputStreamReader;

class Chain {
	private SpeedState current;

	public Chain()                  { current = new Off(); }
	public void setState( SpeedState s ) { current = s; }
	public void pull()              { current.pull( this ); }
}

abstract class SpeedState {
	public void pull( Chain wrapper ) {
		wrapper.setState( new Off() );
		System.out.println( "   turning off" );
	}   
}

class Off extends SpeedState {
	public void pull( Chain wrapper ) {
		wrapper.setState( new Low() );
		System.out.println( "   low speed" );
	}   }

class Low extends SpeedState {
	public void pull( Chain wrapper ) {
		wrapper.setState( new Medium() );
		System.out.println( "   medium speed" );
	}   }

class Medium extends SpeedState {
	public void pull( Chain wrapper ) {
		wrapper.setState( new High() );
		System.out.println( "   high speed" );
	}   
}

class High extends SpeedState { 

}

public class State_Test2 {
	public static void main( String[] args ) throws IOException {
		InputStreamReader is = new InputStreamReader( System.in );
		int ch;
		Chain chain = new Chain();
		while (true) {
			System.out.print( "Press 'Enter'" );
			ch = is.read();    ch = is.read();
			chain.pull();
		}   }   }
