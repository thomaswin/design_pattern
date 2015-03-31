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

import java.io.BufferedReader;
import java.io.InputStreamReader;

class PurchaseRequest {
	private int number;
	private double amount;
	private String purpose;

	public PurchaseRequest(int number, double amount, String purpose) {
		this.number = number;
		this.amount = amount;
		this.purpose = purpose;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amt)  {
		amount = amt;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String reason) {
		purpose = reason;
	}

	public int getNumber(){
		return number;
	}
	public void setNumber(int num) {
		number = num;
	}   
}

abstract class PurchasePower {
	protected final double base = 500;
	protected PurchasePower successor;

	abstract public void processRequest(PurchaseRequest request);
	
	public void setSuccessor(PurchasePower successor) {
		this.successor = successor;
	}	
}


class ManagerPPower extends PurchasePower {
	private final double ALLOWABLE = 10 * base;

	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			System.out.println("Manager will approve $" + request.getAmount());
		} else if (successor != null) {
			successor.processRequest(request);
		}
	}
}

class DirectorPPower extends PurchasePower {
	private final double ALLOWABLE = 20 * base;

	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			System.out.println("Director will approve $" + request.getAmount());
		} else if (successor != null) {
			successor.processRequest(request);
		}
	}
}


class VicePresidentPPower extends PurchasePower {
	private final double ALLOWABLE = 40 * base;

	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			System.out.println("Vice President will approve $" + request.getAmount());
		} else if (successor != null) {
			successor.processRequest(request);
		}
	}
}

class PresidentPPower extends PurchasePower {
	private final double ALLOWABLE = 60 * base;

	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			System.out.println("President will approve $" + request.getAmount());
		} else {
			System.out.println( "Your request for $" + request.getAmount() + " needs a board meeting!");
		}
	}
}

public class ChainResponsibilityExample1 {
	public static void main(String[] args) {
		ManagerPPower manager       = new ManagerPPower();
		DirectorPPower director     = new DirectorPPower();
		VicePresidentPPower vp      = new VicePresidentPPower();
		PresidentPPower president   = new PresidentPPower();
		manager.setSuccessor(director);
		director.setSuccessor(vp);
		vp.setSuccessor(president);

		// Press Ctrl+C to end.
		try {
			while (true) {
				System.out.println("Enter the amount to check who should approve your expenditure.");
				System.out.print(">");
				double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
				manager.processRequest(new PurchaseRequest(0, d, "General"));
			}
		} catch(Exception e) {
			System.exit(1);
		}  
	}
}
