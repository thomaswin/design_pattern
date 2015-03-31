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



public class Visitor {
	public static void main(String[] args) {
		CarElement car = new Car();
		car.accept(new CarElementPrintVisitor());
		car.accept(new CarElementDoVisitor());
		
	}
}

interface CarElementVisitor {
	void visit(Wheel wheel);
	void visit(Engine engine);
	void visit(Body body);
	void visit(Car car);
}

interface CarElement {
	void accept(CarElementVisitor visitor); // CarElements have to provide accept().
}

class Wheel implements CarElement {

	private String name;

	public Wheel(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void accept(CarElementVisitor visitor) {
		/*
		 * Wheel.accept(CarElementVisitor) overrides CarElement.accept(CarElementVisitor), so the call 
		 * to accept is bound at run time. This can be considered the first dispatch.
		 * The decision to call CarElementVisitor.visit(Wheel) however, rather than the other 'visit'
		 * methods in CarElementVisitor, can be made during compile time since 'this' is known at compile 
		 * time to be a Wheel. Moreover, each subclass of CarElementVisitor overrides the visit(Wheel),
		 * which is another decision that is made at run time. This can be considered the second dispatch.
		 */ 
		visitor.visit(this);
	}
}

class Engine implements CarElement {
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
}

class Body implements CarElement {
	public void accept(CarElementVisitor visitor) {
		visitor.visit(this);
	}
}

class Car implements CarElement {
	CarElement[] elements;

	public Car() {
		//create new Array of elements
		this.elements = new CarElement[] { 
				new Wheel("front left"), 
				new Wheel("front right"), 
				new Wheel("back left") , 
				new Wheel("back right"), 
				new Body(), 
				new Engine() };
	}

	public void accept(CarElementVisitor visitor) {     
		for(CarElement elem : elements) {
			elem.accept(visitor);
		}
		visitor.visit(this);    
	}
}

class RacingCar {
	CarElement[] element;
	
}



class CarElementPrintVisitor implements CarElementVisitor {
	public void visit(Wheel wheel) {      
		System.out.println("Visiting " + wheel.getName() + " wheel");
	}

	public void visit(Engine engine) {
		System.out.println("Visiting engine");
	}

	public void visit(Body body) {
		System.out.println("Visiting body");
	}

	public void visit(Car car) {      
		System.out.println("Visiting car");
	}
}

class CarElementDoVisitor implements CarElementVisitor {
	public void visit(Wheel wheel) {
		System.out.println("Kicking my " + wheel.getName() + " wheel");
	}

	public void visit(Engine engine) {
		System.out.println("Starting my engine");
	}

	public void visit(Body body) {
		System.out.println("Moving my body");
	}

	public void visit(Car car) {
		System.out.println("Starting my car");
	}
}



