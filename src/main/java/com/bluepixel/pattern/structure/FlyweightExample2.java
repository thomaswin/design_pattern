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
 * Flyweight interface
 */
interface Soldier {
	public void moveSoldier(int prevLocX, int prevLocY, int newLocX, int newLocY);
}

/*
 * Flyweight implementation
 */
class SoldierImpl implements Soldier {

	private Object soldierGraphicalRepresentation;

	@Override
	public void moveSoldier(int prevLocX, int prevLocY, int newLocX, int newLocY) {
		// TODO Auto-generated method stub	
		// delete soldier representation from previous location 
		// then render soldier representation in new location 	
	}
}

/*
 * Flyweight factory
 */
class SoldierFactory {
	private static Soldier SOLDIER;

	public static Soldier getSoldier() {
		if(SOLDIER == null){
			SOLDIER = new SoldierImpl();
		}
		return SOLDIER;
	}
}

/*
 * Heavyweight 
 */
class SoldierClient {
	
	private Soldier soldier = SoldierFactory.getSoldier();

	private int currentLocationX = 0;	
	private int currentLocationY=0;

	public void moveSoldier(int newLocationX, int newLocationY){
		soldier.moveSoldier(currentLocationX,currentLocationY, newLocationX, newLocationY);
		currentLocationX = newLocationX;
		currentLocationY = newLocationY;
	}
}


public class FlyweightExample2 {


	public static void main(String[] args) {
		// start war 

		// draw war terrain 

		// create 5 soldiers:
		SoldierClient warSoldiers [] ={
				new SoldierClient(),
				new SoldierClient(),
				new SoldierClient(),
				new SoldierClient(),
				new SoldierClient()
		};

		// move each soldier to his location 
		// take user input to move each soldier 
		warSoldiers[0].moveSoldier(17, 2112);

		// 	take user input to move each soldier 
		warSoldiers[1].moveSoldier(137, 112);

		// note that there is only one SoldierImp ( flyweight Imp)
		// for all the 5 soldiers 
		// Soldier Client size is small due to the small state it maintains
		// SoliderImp size might be large or might be small 
		// however we saved memory costs of creating 5 Soldier representations
		
		// there is only one soilder implementation. 
		// each time soldier is move, delete drawing in last location
		// use image to draw on the canvas in new location
		// instead of using each soldier image, use graphical representation to draw on canvas 
		
	}
}




