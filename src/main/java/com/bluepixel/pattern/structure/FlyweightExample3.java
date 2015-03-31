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


import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class FlyweightFactory {

	private static Map<String, Button> ht = new TreeMap<String, Button>();
	private static int sharedButtons = 0;
	private static ButtonListener bl = new ButtonListener();

	public static Button makeButton( String num ) {
		Button btn;
		if (ht.containsKey( num )) {
			// 1. Identify intrinsic state (Button label)
			// 2. Return an existing object   [The same Button cannot be added
			//    multiple times to a container, and, Buttons cannot be cloned.
			//    So - this is only simulating the sharing that the Flyweight
			//    pattern provides.]
			String actionCommand = ht.get(num).getLabel(); 
			btn = new Button(actionCommand);			
			sharedButtons++;
		} else {
			// 2. Return a new object
			btn = new Button( num );
			ht.put( num, btn );
		}
		btn.addActionListener(bl);
		return btn;
	}
	
	public static void report() {
		System.out.print( "new Buttons - " + ht.size() + ", \"shared\" Buttons - " + sharedButtons + ", " );
		for (Iterator<String> it = ht.keySet().iterator(); it.hasNext(); )
			System.out.print( it.next() + " " );
		System.out.println();
	}
}

class ButtonListener implements ActionListener {

	public void actionPerformed( ActionEvent e) {

		Button btn = (Button) e.getSource();
		Component[] btns = btn.getParent().getComponents();
		int i = 0;
		for ( ; i < btns.length; i++)
			if (btn == btns[i]) 
				break;
		// 4. A third party must compute the extrinsic state (x and y)
		//    (the Button label is intrinsic state)
		System.out.println( btns.length + " " + i + " label-" + e.getActionCommand() + "  x-" + i/10   + "  y-" + i%10 );
		// 1. Identify extrinsic state (Button location)
	}
}                                           



public class FlyweightExample3 {

	public static final int ROWS = 20, COLS = 20;
	public static void main(String[] args) {
		java.util.Random rn = new java.util.Random();
		Frame frame = new Frame( "Flyweight Demo" );
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent e ) {
				System.exit( 0 );
			}
		});

		// 1. Identify shareable and non-shareable state
		// shareable - Button label, non-shareable - Button location
		frame.setLayout( new GridLayout( ROWS, COLS ) );
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++)
				// 3. The client must use the Factory to request objects
				frame.add( FlyweightFactory.makeButton( Integer.toString( rn.nextInt(15) ) ) );
		frame.pack();
		frame.setVisible( true );
		FlyweightFactory.report();
	}
}




