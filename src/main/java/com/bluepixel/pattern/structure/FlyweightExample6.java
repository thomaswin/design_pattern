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



/*
class Gazillion {
	private static int num = 0;
	private int        row, col;
	public Gazillion( int maxPerRow ) {
		row = num / maxPerRow;
		col = num % maxPerRow;
		num++;
	}
	void report() {
		System.out.print( " " + row + col );
	}  }

public class FlyweightDemo {
	public static final int ROWS = 6, COLS = 10;

	public static void main( String[] args ) {
		Gazillion[][] matrix = new Gazillion[ROWS][COLS];
		for (int i=0; i < ROWS; i++)
			for (int j=0; j < COLS; j++)
				matrix[i][j] = new Gazillion( COLS );
		for (int i=0; i < ROWS; i++) {
			for (int j=0; j < COLS; j++)
				matrix[i][j].report();
			System.out.println();
		}  
	}
}*/

class Gazillion {

	private int row;
	public Gazillion( int theRow ) {
		row = theRow;
		System.out.println( "ctor: " + row );
	}
	void report( int theCol ) {
		System.out.print( " " + row + theCol );
	} 
}

class Factory {
	private Gazillion[] pool;
	public Factory( int maxRows ) {
		pool = new Gazillion[maxRows];
	}
	public Gazillion getFlyweight( int theRow ) {
		if (pool[theRow] == null)
			pool[theRow] = new Gazillion( theRow );
		return pool[theRow];
	} 
}


public class FlyweightExample6 {

	public static final int ROWS = 6, COLS = 10;
	public static void main(String[] args) {
		Factory theFactory = new Factory( ROWS );
		for (int i=0; i < ROWS; i++) {
			for (int j=0; j < COLS; j++)
				theFactory.getFlyweight(i).report( j );
			System.out.println();
		} 
	}
}

/*
 * Resuse row 
 */




