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
import java.util.HashMap;
import java.util.Map;

/*
 * useful when there is need for multiple objects sharing some common information
 * sometimes need to generate a very large number of small class instances to represent
 * entire syste. memory consuming. 
 * reduce this problem and avoid overhead of large numbers of very similar class object
 * when. require large num of obj
 * storage cost high and difficult to maintain 
 * app doesn't depend on object identitiy.
 * reduction of number of obj to handle
 * reduction in memory and storage device if objects are persisted
 */
//Flyweight object
interface CoffeeOrder {
	public void serveCoffee(CoffeeOrderContext context);
}

//ConcreteFlyweight object that creates ConcreteFlyweight 
class CoffeeFlavor implements CoffeeOrder {
	private String flavor;		// insistrinc state

	public CoffeeFlavor(String newFlavor) {		
		flavor = newFlavor;
	}

	public String getFlavor() {
		return this.flavor;
	}

	public void serveCoffee(CoffeeOrderContext context) {  // extrinsic state
		System.out.println("Serving Coffee flavor " + flavor + " to table number " + context.getTable());
	}
}

//FlyweightFactory object
class CoffeeFlavorFactory {
	private Map<String, CoffeeFlavor> flavors = new HashMap<String, CoffeeFlavor>();

	public CoffeeFlavor getCoffeeFlavor(String flavorName) {
		CoffeeFlavor flavor = flavors.get(flavorName);
		if (flavor == null) {
			flavor = new CoffeeFlavor(flavorName);
			flavors.put(flavorName, flavor);
		}
		return flavor;
	}

	public int getTotalCoffeeFlavorsMade() {
		return flavors.size();
	}
}

// unshared concrete flyweight
class CoffeeOrderContext {
	private int tableNumber;

	public CoffeeOrderContext(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getTable() {
		return this.tableNumber;
	}
}


public class Flyweight {
	/** The flavors ordered. */
	private static CoffeeFlavor[] flavors = new CoffeeFlavor[100];
	
	/** The tables for the orders. */
	private static CoffeeOrderContext[] tables = new CoffeeOrderContext[100];	
	private static CoffeeFlavorFactory flavorFactory;
	private static int ordersMade = 0;

	public static void takeOrders(String flavorIn, int table) {
		flavors[ordersMade] = flavorFactory.getCoffeeFlavor(flavorIn);
		tables[ordersMade] 	= new CoffeeOrderContext(table);
		ordersMade++;
	}

	public static void main(String[] args) {
		flavorFactory = new CoffeeFlavorFactory();

		takeOrders("Cappuccino", 2);
		takeOrders("Cappuccino", 2);
		takeOrders("Frappe", 1);
		takeOrders("Frappe", 1);
		takeOrders("Xpresso", 1);
		takeOrders("Frappe", 897);
		takeOrders("Cappuccino", 97);
		takeOrders("Cappuccino", 97);
		takeOrders("Frappe", 3);
		takeOrders("Xpresso", 3);
		takeOrders("Cappuccino", 3);
		takeOrders("Xpresso", 96);
		takeOrders("Frappe", 552);
		takeOrders("Cappuccino", 121);
		takeOrders("Xpresso", 121);

		for (int i = 0; i < ordersMade; ++i) {
			flavors[i].serveCoffee(tables[i]);
		}
		
		System.out.println(" ");
		System.out.println("total CoffeeFlavor objects made: " +  flavorFactory.getTotalCoffeeFlavorsMade());
	}
}




