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
import java.util.Map.Entry;



class Fruit {
	private String typeName;
	private static Map<String, Fruit> types = new HashMap<String, Fruit>();

	// using a private constructor to force use of the factory method.
	private Fruit(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Lazy Factory method, gets the Fruit instance associated with a certain
	 * type. Instantiates new ones as needed.
	 * 
	 * @param type
	 *            Any string that describes a fruit type, e.g. "apple"
	 * @return The Fruit instance associated with that type.
	 */
	public static Fruit getFruitByTypeName(String type) {
		Fruit fruit;

		if (!types.containsKey(type)) {
			
			// Lazy initialization
			fruit = new Fruit(type);
			types.put(type, fruit);
			
		} else {
			// Okay, it's available currently
			fruit = types.get(type);
		}

		return fruit;
	}

	// Same above but used double-checked locking pattern
	// for using in highly concurrent environments
	public static Fruit getFruitByTypeNameHighConcurrentVersion(String type) {
		Fruit fruit;

		if (!types.containsKey(type)) {
			synchronized (types) {
				// Check again, after having acquired the lock to make sure
				// the instance was not created meanwhile by another thread
				if (!types.containsKey(type)) {
					// Lazy initialization
					types.put(type, new Fruit(type));
				}
			}
		}

		fruit = types.get(type);
		return fruit;
	}

	public static void showAll() {
		if (types.size() > 0) {
			System.out.println("Number of instances made = " + types.size());

			for (Entry<String, Fruit> entry : types.entrySet()) {
				System.out.println(entry.getKey());
			}
			System.out.println();
		}
	}
}


public class LazyInitialization {
	public static void main(String[] args) {
		Fruit.getFruitByTypeName("Banana");
		Fruit.showAll();

		Fruit.getFruitByTypeName("Apple");
		Fruit.showAll();

		// returns pre-existing instance from first
		// time Fruit with "Banana" was created
		Fruit.getFruitByTypeName("Banana");
		Fruit.showAll();
	}
}