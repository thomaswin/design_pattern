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

/* 	
	- decouple abstration from implementation so that two can vary independently.
	- use encapsulation, aggregation and inheritance to seperate responsibility into different class
	- change can be made easily with minimal prior knowledge about the problem.
	- useful when both class as well as what it does very often.
	- GUI framework use bridge pattern to seperate abstrations from platform specific implementation
	- framework seperate window abstration from window implemention
	- decoupling interface and implementation. implementation is not bound permanently.
	- implementation of abstration can be configured and even switched at run-time.
*/
abstract class Engine {
	
	protected TuningAPI tuningAPI;
	
	protected Engine(TuningAPI tuningAPI){
		this.tuningAPI = tuningAPI;
	}
	public abstract void increasePower();
	public abstract void reduceDragForce(int ratio); 
}

class MercedeEngine extends Engine {

	private int horsepower, fuelLoad;
	protected MercedeEngine(int horsepower, int fuelLoad, TuningAPI tuningAPI) {
		super(tuningAPI);
		this.horsepower = horsepower;
		this.fuelLoad = fuelLoad;
	}

	@Override
	public void increasePower() {
		tuningAPI.adjustRevolutionTiming(128, 20, 512);
	}

	@Override
	public void reduceDragForce(int ratio) {
		tuningAPI.tuneCaburater();
	}
}

interface TuningAPI {	
	public void adjustRevolutionTiming(int speed, int utilization, int power);		
	public void tuneCaburater();
}

class NormalTuningAPI implements TuningAPI {
	@Override
	public void adjustRevolutionTiming(int speed, int utilization, int power) {
		System.out.println("normal revolution tuning done");		
	}

	@Override
	public void tuneCaburater() {
		System.out.println("normal carburator tuning done");		
	}	
}

class AdvancedTuningAPI implements TuningAPI {
	@Override
	public void adjustRevolutionTiming(int speed, int utilization, int power) {
		System.out.println("advanced revolution tuning done");
	}

	@Override
	public void tuneCaburater() {
		System.out.println("advanced carburator tuning done");
	}	
}

public class BridgeExample1 {
	public static void main(String[] args) {
		Engine[] engines = new Engine []{
				new MercedeEngine(111, 222, new NormalTuningAPI()),
				new MercedeEngine(444, 555, new AdvancedTuningAPI()),
		};
		
		for (Engine engine : engines){
			engine.increasePower();
			engine.reduceDragForce(20);
		}
	}
}




















