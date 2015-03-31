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


interface Mediator2 {
	public void send(String message, Colleague colleague);
}

abstract class Colleague {
	private Mediator2 mediator;

	public Colleague(Mediator2 mediator){
		this.mediator = mediator;
	}
	public void send (String message) {
		System.out.println(this.getClass().getSimpleName() + " send " +  message);
		mediator.send(message, this);
	}
	public Mediator2 getMediator(){
		return mediator;
	}
	public abstract void receive(String message);
}

class MobileColleague extends Colleague{

	public MobileColleague(Mediator2 mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receive(String message) {
		System.out.println("MObile received : " + message);

	}
}
class ConcreteColleague extends Colleague{

	public ConcreteColleague(Mediator2 mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receive(String message) {
		System.out.println("Concretecolleage received : " + message);

	}
}

class ApplicationMediator implements Mediator2 {

	private ArrayList<Colleague> colleagues;

	public ApplicationMediator() {
		colleagues = new ArrayList<Colleague>();
	}
	public void addColleague (Colleague colleague) {
		colleagues.add(colleague);
	}

	@Override
	public void send(String message, Colleague orginator) {
		// TODO Auto-generated method stub
		for (Colleague colleague: colleagues) {
			if(colleague != orginator) {
				colleague.receive(message);
			}
		}
	}	
}

public class MediatorTest2 {

	public static void main(String[] args) {
		ApplicationMediator mediator = new ApplicationMediator();
		ConcreteColleague desktop = new ConcreteColleague(mediator);
		MobileColleague mobile = new MobileColleague(mediator);

		mediator.addColleague(desktop);
		mediator.addColleague(mobile);

		desktop.send("Hello World");
		mobile.send("Hello"); 
	}
}

