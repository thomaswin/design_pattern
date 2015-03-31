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

import java.io.IOException;
import java.io.InputStreamReader;





interface Thermometer {
	int getTemperature();
}

class RadiatorThermometer implements Thermometer {

	int min = 80;
	int max = 120;
	@Override
	public int getTemperature() {
		return min + (int) (Math.random() * ((max - min) + 1));
	}	
}

class Radiator {

	interface StateChangeListener {
		public void onChangeState(RadiatorState state);
	}

	private RadiatorState myState;
	private Thermometer thermometer;

	private StateChangeListener listener = new StateChangeListener() {		
		@Override
		public void onChangeState(RadiatorState state) {
			// TODO Auto-generated method stub
			if (state instanceof OverHeat) {
				((OverHeat)state).turnCoolingON();
			} else if (state instanceof Normal) {
				((Normal)state).turnCoolingOFF();
			}
		}
	};

	public Radiator() {
		setState(new Normal());
	}

	public void setThermometer(Thermometer thermometer){
		this.thermometer = thermometer;
	}
	public void setState(RadiatorState newState) {
		this.myState = newState;
		this.myState.setChangeListener(listener);
	}

	public void checkTemperature(int warningTemp) {
		myState.checkTemperature(this, warningTemp);
	}
	public int getTemperature(){
		return thermometer.getTemperature();
	}	

	
}

interface RadiatorState {

	void setChangeListener(Radiator.StateChangeListener listener);
	void checkTemperature(Radiator radiator, int warningTemperature);
}

class Normal implements RadiatorState {
	Radiator.StateChangeListener listener_ = null;
	@Override
	public void checkTemperature(Radiator radiator, int warningTemperature) {
		// TODO Auto-generated method stub
		int temperature = radiator.getTemperature();
		System.out.println("\ntemp : " + temperature);
		if (temperature > warningTemperature) {
			OverHeat overheat = new OverHeat();
			radiator.setState(overheat);
			listener_.onChangeState(overheat);
			System.out.println("State change : Overheat ");
		} else {
			System.out.println("State : normal ");
		}
	}

	@Override
	public void setChangeListener(Radiator.StateChangeListener listener) {
		// TODO Auto-generated method stub
		listener_ = listener;
	}	
	
	public void turnCoolingOFF() {
		System.out.println("Cooling OFF" );
	}
}

class OverHeat implements RadiatorState {
	Radiator.StateChangeListener listener_ = null;
	@Override
	public void checkTemperature(Radiator radiator, int warningTemperature) {
		// TODO Auto-generated method stub
		int temperature = radiator.getTemperature();
		System.out.println("\ntemp : " + temperature);

		if (temperature < warningTemperature) {
			
			Normal normal = new Normal();
			radiator.setState(normal);
			listener_.onChangeState(normal);
			System.out.println("State change : normal " );
		} else {
			System.out.println("State : Overheat");
		}
	}

	@Override
	public void setChangeListener(Radiator.StateChangeListener listener) {
		// TODO Auto-generated method stub
		listener_ = listener;
	}
	
	public void turnCoolingON() {
		System.out.println("Cooling ON" );
	}
}

public class State_Example3 {
	public static void main( String[] args ) throws IOException {
		Radiator radiator = new Radiator();
		radiator.setThermometer(new RadiatorThermometer());
		for(int i = 0; i < 200; i++ ){
			try {
				Thread.sleep(1000);
				radiator.checkTemperature(100);				
			} catch (InterruptedException e) {
			}
		}
	}
}
