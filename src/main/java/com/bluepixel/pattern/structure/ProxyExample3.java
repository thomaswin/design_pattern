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

interface Bank {
	public boolean applyForLoan();
	public boolean isFunAvailable();
	public int widthDraw();
	public int deposit();
}

class ProxyBank implements Bank {

	@Override
	public boolean applyForLoan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFunAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int widthDraw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deposit() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
class InterBank implements Bank {

	@Override
	public boolean applyForLoan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFunAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int widthDraw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deposit() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
class CentralBank implements Bank {

	@Override
	public boolean applyForLoan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFunAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int widthDraw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deposit() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
public class ProxyExample3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bank bank1 = new ProxyBank();
		Bank bank2 = new ProxyBank();

	}

}
