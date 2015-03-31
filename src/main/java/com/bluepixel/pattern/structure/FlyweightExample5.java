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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;


/* The ColorBox class has now become a Flyweight: 
 * the color changing and painting capability remains intrinsic, and the threaded behavior has been made extrinsic.
 * The ThreadPool class plays the role of the Factory. As ColorBox objects are created,
 * they register themselves with the ThreadPool object. The latter launches 8 �handler� threads.
 * When each thread is swapped into the CPU, it selects a random Flyweight from the ThreadPool�s cache, 
 * and asks the object to changeColor().*/
class ColorBox2 extends Canvas {

	private static final long serialVersionUID = 8146535734600316413L;
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

	public ColorBox2( ThreadPool2 tp ) {
		tp.register( this );
	}
	private static Color getColor() {
		int random = (int)(Math.random() * 1000) % colors.length;
		return colors[random];
	}

	public void changeColor() {
		curColor = getColor();
		repaint();
	}
	public void paint( Graphics g ) {
		g.setColor( curColor );
		g.fillRect( 0, 0, getWidth(), getHeight() );
	} 
}

class ThreadPool2 {

	private final int NUM_THREADS = 8;
	private Vector<ColorBox2> cboxes = new Vector<ColorBox2>();
	private int pause;

	class HandlerThread extends Thread {
		public void run() {
			while (true) {
				int random = (int)(Math.random()*1000) % cboxes.size();
				cboxes.elementAt(random).changeColor();
				try { 
					Thread.sleep( pause ); 
				} catch( InterruptedException e ) { 

				}
			} 
		}
	}

	public ThreadPool2( int p ) {
		pause = p;
	}
	public void register( ColorBox2 r ) {
		cboxes.addElement( r );
	}

	public void start() {
		for (int i=0; i < NUM_THREADS; i++)
			new HandlerThread().start();
	}  
}

public class FlyweightExample5 {

	public static void main(String[] args) {

		int size = 8, pause = 10;
		if (args.length > 0)			
			size  = Integer.parseInt( args[0] );
		if (args.length > 1)
			pause = Integer.parseInt( args[1] );

		ThreadPool2 tp = new ThreadPool2( pause );

		Frame f = new Frame( "ColorBoxes2 - 8 shared HandlerThreads" );
		f.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent e ) {
				System.exit( 0 );
			}
		});
		f.setLayout( new GridLayout( size, size ) );
		for (int i=0; i < size*size; i++) 
			f.add( new ColorBox2( tp ) );
		f.setSize( 500, 400 );
		f.setVisible( true );
		tp.start();
	}
}




