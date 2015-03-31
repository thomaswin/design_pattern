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

class ElectronicSystem {

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void check() {
		// TODO Auto-generated method stub
		
	}
	
}

class BreakSystem {

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void releaseBreak() {
		// TODO Auto-generated method stub
		
	}

	public void turnOnAntiSkidding() {
		// TODO Auto-generated method stub
		
	}
	
}

class Car {
	private ElectronicSystem electronicSystem;
	private BreakSystem breakSystem;
	
	public void startCar(){
		electronicSystem.start();
		electronicSystem.check();
	}
	public void accelerate(){
		breakSystem.init();
		breakSystem.releaseBreak();
		breakSystem.turnOnAntiSkidding();
	}
}

public class FacadeExample1 {
    public static void main(String[] args) {

    }
}