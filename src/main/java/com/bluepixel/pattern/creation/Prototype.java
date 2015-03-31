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

/**
 * Prototype class
 */
abstract class Prototype implements Cloneable {
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract void setX(int x);

	public abstract void printX();

	public abstract int getX();
}

/**
 * Implementation of prototype class
 */
class PrototypeImpl extends Prototype {
	int x;

	public PrototypeImpl(int x) {
		this.x = x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void printX() {
		System.out.println("Value :" + x);
	}

	public int getX() {
		return x;
	}
}

/**
 * Client code
 */
public class Prototype_design{
	public static void main(String args[]) throws CloneNotSupportedException {
		Prototype prototype = new PrototypeImpl(1000);

		for (int i = 1; i < 10; i++) {
			Prototype tempotype = (Prototype) prototype.clone();

			// Usage of values in prototype to derive a new value.
			tempotype.setX( tempotype.getX() * i);
			tempotype.printX();
		}
	}
}