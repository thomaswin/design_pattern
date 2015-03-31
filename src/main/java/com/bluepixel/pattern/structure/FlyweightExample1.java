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

interface DeliverOrder {
	public void deliver(DeliverPackageContext context);
}

class DeliverPackage implements DeliverOrder {
	private String newPackageName;
	
	public DeliverPackage(String newPackageName){
		this.newPackageName = newPackageName;
	}
	@Override
	public void deliver(DeliverPackageContext context) {
		System.out.println(newPackageName + " ->> deliver to " + context.getAddress());		
	}	
}

class DeliverPackageFactory {
	private Map<String, DeliverPackage> packages = new HashMap<String, DeliverPackage>();
	
	public DeliverPackage getDeliverPackage(String packageName) {
		DeliverPackage deliverPackage = packages.get(packageName);
		if (deliverPackage == null) {
			deliverPackage  = new DeliverPackage(packageName);
			packages.put(packageName, deliverPackage);
		}
		return deliverPackage;
	}
	
	public int getTotalDeliverPackageMade() {
		return packages.size();
	}
}

class DeliverPackageContext{
	private String address;

	public DeliverPackageContext(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}
}

public class FlyweightExample1 {
	private static DeliverPackage[] packges = new DeliverPackage[100];
	private static DeliverPackageContext[] addresses = new DeliverPackageContext[100];
	private static int ordersMade = 0;
	private static DeliverPackageFactory packageFactory;
	
	public static void arrangeDelivery(String packageName, String address) {
		packges[ordersMade] = packageFactory.getDeliverPackage(packageName);
		addresses[ordersMade] = new DeliverPackageContext(address);
		ordersMade++;
	}
	
	public static void main(String[] args) {
		packageFactory = new DeliverPackageFactory();
		arrangeDelivery("PC2011", "Brian and family : Orchard Road");
		arrangeDelivery("IPOD",   "Tun and bro : Boonlay Road");
		arrangeDelivery("IPOD",   "Lin and bro : Boonlay Road");

		for (int i = 0; i < ordersMade; ++i) {
			packges[i].deliver(addresses[i]);
		}
		
		System.out.println(" ");
		System.out.println("total Package objects made: " +  packageFactory.getTotalDeliverPackageMade());
	}
}




