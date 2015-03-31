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

public class Servant {

	public static void main(String[] args) {

	}

	// Method, which will move Movable implementing class to position where
	public void moveTo(Movable serviced, Position where) {
		// Do some other stuff to ensure it moves smoothly and nicely, this is
		// the place to offer the functionality
		serviced.setPosition(where);
	}

	// Method, which will move Movable implementing class by dx and dy
	public void moveBy(Movable serviced, int dx, int dy) {
		// this is the place to offer the functionality
		dx += serviced.getPosition().xPosition;
		dy += serviced.getPosition().yPosition;
		serviced.setPosition(new Position(dx, dy));
	}
}
//Interface specifying what serviced classes needs to implement, to be
//serviced by servant.
interface Movable {
	public void setPosition(Position p);

	public Position getPosition();
}

//One of geometric classes
class Triangle implements Movable {
	// Position of the geometric object on some canvas
	private Position p;

	// Method, which sets position of geometric object
	public void setPosition(Position p) {
		this.p = p;
	}

	// Method, which returns position of geometric object
	public Position getPosition() {
		return this.p;
	}
}

//One of geometric classes
class Ellipse implements Movable {
	// Position of the geometric object on some canvas
	private Position p;

	// Method, which sets position of geometric object
	public void setPosition(Position p) {
		this.p = p;
	}

	// Method, which returns position of geometric object
	public Position getPosition() {
		return this.p;
	}
}

//One of geometric classes
class Rectangle implements Movable {
	// Position of the geometric object on some canvas
	private Position p;

	// Method, which sets position of geometric object
	public void setPosition(Position p) {
		this.p = p;
	}

	// Method, which returns position of geometric object
	public Position getPosition() {
		return this.p;
	}
}

//Just a very simple container class for position.
class Position {
	public int xPosition;
	public int yPosition;

	public Position(int dx, int dy) {
		xPosition = dx;
		yPosition = dy;
	}
}