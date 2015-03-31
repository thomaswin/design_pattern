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

import java.util.ArrayList;


/*
 * - mediator define interface for communicating between colleague objects.
 * - concrete mediator implements mediator interface and coordinate communication between colleague objects. 
 * - concrete colleague communicate with other colleagues through mediator
 * - without it, all the colleagues would know about each other and leading to high coupling.
 * - by having all colleagues communicate through one central point , can decouple system while maining control on object's interactions.
 * - observers based variation of mediator pattern is used in JSM implementation. allowing 
 * - application to subscribe and publish data to other application.
 */
class ProducerConsumerMediator {
	boolean slotFull = false;
	int number = 0;
	public synchronized void storeMessage(int num) {
		while (slotFull) {
			try {
				wait();
			} catch (InterruptedException e){}
		}
		slotFull = true;
		number = num;
		notifyAll();
	}
	public synchronized int retrieveMessage() {
		while(!slotFull){
			try {
				wait();
			} catch (InterruptedException e){}
		}
		slotFull = false;
		notifyAll();
		return number;
	}
}

class Producer extends Thread { 
	private ProducerConsumerMediator med;
	private int id;
	private static int num = 1;

	public Producer (ProducerConsumerMediator med) {
		this.med = med;
		id = num++;
	}
	public void run() {
		int num;
		while (true) {
			med.storeMessage( num = (int)(Math.random()*100) );
			System.out.print( "p" + id + "-" + num + "  " );
		}
	}
}

class Consumer extends Thread {
	private ProducerConsumerMediator med;
	private int    id;
	private static int num = 1;
	public Consumer( ProducerConsumerMediator m ) {
		med = m;
		id = num++;
	}
	public void run() {
		while (true) {
			System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
		}
	}
}
public class MediatorTest3 {

	public static void main(String[] args) {
		ProducerConsumerMediator mb = new ProducerConsumerMediator();
		    new Producer( mb ).start();
		    new Producer( mb ).start();
		    new Consumer( mb ).start();
		    new Consumer( mb ).start();
		    new Consumer( mb ).start();
		    new Consumer( mb ).start();
	}
}

