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



// component
interface Aircraft {
	public void equip();
	public String getDescription();
}

// concrete component
class Fighter implements Aircraft {
	String electronicSystem = "none";
	String weapon;
	
	@Override
	public void equip() {
		System.out.println("Fighter equipped");
	}

	@Override
	public String getDescription() {
		return ("Fighter aircraft");		
	}

	@Override
	public String toString() {
		return "Fighter [electronicSystem=" + electronicSystem + ", weapon=" + weapon + "]";
	}
}

// Decorator
abstract class AircraftDecorator implements Aircraft {
	protected Aircraft aircraft;

	public AircraftDecorator(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	public void equip(){
		aircraft.equip();
	}

	public String getDescription() {
		return aircraft.getDescription();	// delegation
	}
}

// concrete decorator
class ArmorDecorator extends AircraftDecorator {	

	public ArmorDecorator(Aircraft aircraft) {
		super(aircraft);		
	}
	
	@Override
	public void equip(){
		aircraft.equip();
		addWeapon();
	}

	private void addWeapon() {
		System.out.println("weapon added");
		if (aircraft instanceof Fighter) {
			((Fighter)aircraft).weapon = "side winder missile";
		}
	}
	
	@Override
	public String getDescription() {
		return aircraft.getDescription() + " weapon";
	}
}

//concrete decorator
class ElectronicDecorator extends AircraftDecorator {

	public ElectronicDecorator(Aircraft aircraft) {
		super(aircraft);
	}
	
	@Override
	public void equip(){
		aircraft.equip();
		addElectronicBoard();
	}

	private void addElectronicBoard() {
		System.out.println("Electronic board added");		
		((Fighter)aircraft).electronicSystem = "laser jampper";
	}
	
	@Override
	public String getDescription() {
		return aircraft.getDescription() + " including Electronic board";
	}
}

public class DecoratorExample2 {
	public static void main(String[] args) {
		Aircraft aircraft1 = new ArmorDecorator(new ElectronicDecorator(new Fighter()));
		System.out.println(aircraft1.getDescription());
	}
}















