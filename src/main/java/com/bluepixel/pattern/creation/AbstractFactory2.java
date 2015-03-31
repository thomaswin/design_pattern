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

package com.bluepixel.pattern.creation;

public class AbstractFactory2 {

	public static void main(String[] args) {
		new CarManufacturer(createLocationSpecificFactory());
	}
	public static CarFactory createLocationSpecificFactory(){
		String location = "japan";
		if(location.equals("japan")){
			return new TOYOTA_Factory();
		} else {
			return new HONDA_Factory();
		}
	}
}

interface CarFactory {
	public Engine createEngine();
}

class TOYOTA_Factory implements CarFactory{

	@Override
	public Engine createEngine() {
		System.out.println("TOYOTA_Factory : create engine");
		return new DieselEngine();
	}
	
}
class HONDA_Factory implements CarFactory{

	@Override
	public Engine createEngine() {
		System.out.println("HONDA_Factory : create engine");
		return new HybridEngine();
	}
	
}

interface Engine {
	public void start();

	public void test();
}

class HybridEngine implements Engine{
	@Override
	public void start() {
		System.out.println("HybridEngine : start");
		
	}

	@Override
	public void test() {
		System.out.println("HybridEngine : test");
		
	}
}

class DieselEngine implements Engine{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("DieselEngine : start");
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("DieselEngine : test");
	}
}

class CarManufacturer {
	public CarManufacturer(CarFactory factory){
		Engine engine = factory.createEngine();
		engine.start();
		engine.test();
	}	
}













