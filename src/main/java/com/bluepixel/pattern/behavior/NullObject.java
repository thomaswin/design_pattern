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

public class NullObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IAnimal dog = new Dog();
		dog.MakeSound(); // outputs "Woof!"

		/* Instead of using C# null, use a NullAnimal instance.
		 * This example is simplistic but conveys the idea that if a NullAnimal instance is used then the program
		 * will never experience a .NET System.NullReferenceException at runtime, unlike if C# null was used.
		 */
		IAnimal unknown = new NullAnimal();  //<< replaces: IAnimal unknown = null;
		unknown.MakeSound(); 				// outputs 

	}

}

interface IAnimal {
	void MakeSound();
}

//Dog is a real animal.
class Dog implements IAnimal {

	public void MakeSound() {
		System.out.println("woof");
	}
}

class NullAnimal implements IAnimal {
	public void MakeSound() {
		System.out.println("nothing");		
	}
}

