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

import java.util.List;
import java.util.ArrayList;

/** "Component" */
interface Graphic {

	//Prints the graphic.
	public void print();
}

/** "Composite" */
class CompositeGraphic implements Graphic {

	//Collection of child graphics.
	private List<Graphic> mChildGraphics = new ArrayList<Graphic>();

	//Prints the graphic.
	public void print() {
		for (Graphic graphic : mChildGraphics) {
			graphic.print();
		}
	}

	//Adds the graphic to the composition.
	public void add(Graphic graphic) {
		mChildGraphics.add(graphic);
	}

	//Removes the graphic from the composition.
	public void remove(Graphic graphic) {
		mChildGraphics.remove(graphic);
	}
}

/** "Leaf" */
class Ellipse implements Graphic {

	//Prints the graphic.
	public void print() {
		System.out.println("Ellipse");
	}
}

/** Client */
public class Composite {

	public static void main(String[] args) {
		//Initialize four ellipses
		Ellipse ellipse1 = new Ellipse();
		Ellipse ellipse2 = new Ellipse();
		Ellipse ellipse3 = new Ellipse();
		Ellipse ellipse4 = new Ellipse();

		//Initialize three composite graphics
		CompositeGraphic graphic = new CompositeGraphic();
		CompositeGraphic graphic1 = new CompositeGraphic();
		CompositeGraphic graphic2 = new CompositeGraphic();

		//Composes the graphics
		graphic1.add(ellipse1);
		graphic1.add(ellipse2);
		graphic1.add(ellipse3);

		graphic2.add(ellipse4);

		graphic.add(graphic1);
		graphic.add(graphic2);

		//Prints the complete graphic (four times the string "Ellipse").
		graphic.print();
	}
}