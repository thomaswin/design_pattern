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

interface Subject {
    public void addObserver(Observer1 o);
    public void removeObserver(Observer1 o);
    public String getState();
    public void setState(String state);
}

interface Observer1 {
    public void update(Subject o);
}

class ObserverImpl implements Observer1 {
    private String state = "";

    public void update(Subject o) {
        state = o.getState();
        System.out.println("Update received from Subject, state changed to : " + state);
    }
}

class SubjectImpl implements Subject {
    private List observers = new ArrayList();

    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(Observer1 o) {
        observers.add(o);
    }

    public void removeObserver(Observer1 o) {
        observers.remove(o);
    }

    @SuppressWarnings("rawtypes")
	public void notifyObservers() {
        Iterator i = observers.iterator();
        while (i.hasNext()) {
            Observer1 o = (Observer1) i.next();
            o.update(this);
        }
    }
}

public class Observer {

    public static void main(String[] args) {
        Observer1 o = new ObserverImpl();
        Subject s = new SubjectImpl();
        s.addObserver(o);
        s.setState("New State");
    }
}
// }