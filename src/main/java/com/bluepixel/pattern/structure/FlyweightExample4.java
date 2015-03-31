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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;

/* Creating a thread for each ColorBox is a much more straight- forward approach, 
 * but it doesn�t scale when dozens of ColorBoxes are created. Sharing a �pool� of threads 
 * across the collection of ColorBoxes requires more thought to set-up, but does not saturate 
 * �system resources� like the former approach does.
 * 
 * In the implementation below, each ColorBox �wraps� itself with a Thread object. 
 * The Thread object provides all the �threading functionality magic� and simply calls ColorBox�s run() method 
 * when it is promoted from the �ready� state to the �running� state. When each Thread/ColorBox is swapped into the CPU,
 * it causes the ColorBox part of itself to change its color and then graciously gives up 
 * the CPU [by calling sleep()] so that other Threads may run.
 * 
 * In the ThreadPool implementation, after the ColorBoxes are set-up, the ThreadPool creates and 
 * starts 8 HandlerThreads. When a HandlerThread is swapped into the CPU, it gets a random ColorBox 
 * object from ThreadPool�s private Vector, tells the ColorBox to change its color, and graciously 
 * returns to the �asleep� state.
 * 
 * �You can typically make your threaded applications run FASTER by inserting calls to sleep() 
 * (with reasonably long durations).� This definitely contri-butes to the perception that Threads 
 * are a �black art�. Not enough calls: monopolization of the CPU. Not enough duration: time expiration
 * interrupt events interrupt the running thread before it can finish useful work.*/


class ColorBox extends Canvas implements Runnable {

	private static final long serialVersionUID = -7030214066502266552L;
	private int    pause;
	private Color  curColor = getColor();

	private static Color[]  colors = { 
		Color.black, 
		Color.blue,
		Color.cyan,
		Color.darkGray,
		Color.gray,
		Color.green,
		Color.lightGray,
		Color.red,
		Color.magenta,
		Color.orange, 
		Color.pink,
		Color.white, 
		Color.yellow };

	public ColorBox( int p ) {
		pause = p;
		new Thread( this ).start();
	}
	private static Color getColor() {
		int random = (int)(Math.random() * 1000) % colors.length;
		return colors[random];
	}

	public void run() {
		while (true) {
			curColor = getColor();
			repaint();
			try { 
				Thread.sleep( pause );
			}catch( InterruptedException e ) {

			}
		}  
	}
	
	public void paint( Graphics g ) {
		g.setColor( curColor );
		g.fillRect( 0, 0, getWidth(), getHeight() );
	} 
}

public class FlyweightExample4 {
	public static void main(String[] args) {
		int size = 8, pause = 10;
		if (args.length > 0) 
			size  = Integer.parseInt( args[0] );
		if (args.length > 1) 
			pause = Integer.parseInt( args[1] );
		Frame f = new Frame( "ColorBoxes - 1 thread per ColorBox" );
		f.setLayout( new GridLayout( size, size ) );
		for (int i=0; i < size*size; i++) 
			f.add( new ColorBox( pause ) );
		f.setSize( 500, 400 );
		f.setVisible( true );
	}
}




