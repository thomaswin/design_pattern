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

/*

- encapsulate a group of individual factory that a command theme.
- client don't care which concerete object it get from each of these internal factory
- possible to interchange concerte implementation without changing codethat use them even at runtime.
- unnecessary complexity and extraa initial writing of code. extra work pay off in second implementation of factory

*/

interface GUIFactory {
	public Button createButton();
	
	
}

class WinFactory implements GUIFactory {
	public Button createButton() {
		return new WinButton();
	}
}
class OSXFactory implements GUIFactory {
	public Button createButton() {
		return new OSXButton();
	}
}

interface Button {
	public void paint();
}

class WinButton implements Button {
	public void paint() {
		System.out.println("I'm a WinButton");
	}
}
class OSXButton implements Button {
	public void paint() {
		System.out.println("I'm an OSXButton");
	}
}

class Application {
	public Application(GUIFactory factory) {
		Button button = factory.createButton();
		button.paint();
	}
}

public class AbstractFactory {
	public static void main(String[] args) {
		new Application(createOsSpecificFactory());
	}

	public static GUIFactory createOsSpecificFactory() {
		int sys = readFromConfigFile("OS_TYPE");
		if (sys == 0) {
			return new WinFactory();
		} else {
			return new OSXFactory();
		}
	}

	private static int readFromConfigFile(String type) {
		return 1;
	}
}
