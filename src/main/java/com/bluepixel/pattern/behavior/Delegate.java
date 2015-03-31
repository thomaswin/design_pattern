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

// Delegation is simply passing a duty off to someone/something else
class RealPrinter { // the "delegate"
	void print() { 
		System.out.println("something"); 
	}
}

class Printer { // the "delegator"
	RealPrinter p = new RealPrinter(); // create the delegate 
	void print() { 
		p.print(); // delegation
	} 
}

public class Delegate {
	public static void main(String[] args) {
		Printer printer = new Printer();
		printer.print();
	}
}

interface IWorker {
	void doWork(IProcessor processor);
}

interface IProcessor {
	public void doProcess(PublicDO publicDo);
}

class PublicDO implements IProcessor {

	@Override
	public void doProcess(PublicDO publicDo) {
		// TODO Auto-generated method stub

	}
}

class Worker implements IWorker {

	private IProcessor delegate;

	void doWork(PublicDO data) {
		// PrivateDO pdo = convert(data);
		// delegate.doProcess(pdo);
	}
	//...

	@Override
	public void doWork(IProcessor processor) {
		// TODO Auto-generated method stub
	}
}


