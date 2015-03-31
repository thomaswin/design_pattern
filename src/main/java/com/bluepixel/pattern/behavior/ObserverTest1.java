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
import java.util.Iterator;
import java.util.List;

interface MeterListener {
	public void addObserver(SystemObserver o);
	public void removeObserver(SystemObserver o);
	public String getState();
	public void setState(String state);
}

class FuelMeter implements MeterListener {

	private  List<SystemObserver>  observers = new ArrayList<SystemObserver>();
	private String state = "";

	@Override
	public void addObserver(SystemObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(SystemObserver o) {
		observers.remove(o);
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
		notifyObservers();
	}
	public synchronized void notifyObservers() {
        Iterator<SystemObserver> i = observers.iterator();
        while (i.hasNext()) {
        	SystemObserver o = (SystemObserver) i.next();
            o.update(this);
        }
    }
}

interface SystemObserver {
	public void update(MeterListener meter);
}

class AutonomousSystem implements SystemObserver {
	String state = "";
	@Override
	public void update(MeterListener meter) {
		state =  meter.getState();
        System.out.println("Update received from Subject, state changed to : " + state);
	}
}

public class ObserverTest1 {

	public static void main(String[] args) {
		MeterListener o = new FuelMeter();
		SystemObserver s = new AutonomousSystem();
		o.addObserver(s);
		o.setState("new state");
	}
}
