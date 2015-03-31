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

import java.util.HashMap;
import java.util.Map;

/* 
 * http://www.blackwasp.co.uk/Multiton.aspx
 * similar to singleton which allow only one instance of a class
 * multiton expends on singleton concept to manage a map of named instances as key-value pairs
 * rather than have a single instance per application, pattern use single instance per key.
 * 
 * e.g. 
 */
public class Multiton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	private static final Map<Object, FooMultiton> instances = new HashMap<Object, FooMultiton>();

	public static FooMultiton getInstance(Object key) {
		synchronized (instances) {
			// Our "per key" singleton
			FooMultiton instance = instances.get(key);

			if (instance == null) {
				// Lazily create instance
				instance = new FooMultiton();
				// Add it to map   
				instances.put(key, instance);
			}
			return instance;
		}
	}
	// other fields and methods ...
}


class FooMultiton {
	private static final Map<Object, FooMultiton> instances = new HashMap<Object, FooMultiton>();

	FooMultiton() /* also acceptable: protected, {default} */ {
		/* no explicit implementation */
	}

	public static FooMultiton getInstance(Object key) {
		// Our "per key" singleton
		FooMultiton instance = instances.get(key);

		// if the instance has never been created ...
		if (instance == null) {
			synchronized (instances) {

				// Check again, after having acquired the lock to make sure 
				// the instance was not created meanwhile by another thread
				instance = instances.get(key);

				if (instance == null) {
					// Lazily create instance
					instance = new FooMultiton();

					// Add it to map   
					instances.put(key, instance);
				}
			}
		}
		return instance;
	}
	// other fields and methods ...
}

