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

interface Command1 {
	public abstract void execute();
}

class Fan {
	public void startRotate() {
		System.out.println("Fan is rotating");
	}
	public void stopRotate() {
		System.out.println("Fan is not rotating");
	}
}
class Light {
	public void turnOn( ) {
		System.out.println("Light is on ");
	}
	public void turnOff( ) {
		System.out.println("Light is off");
	}
}

class Switch {
	private Command1 UpCommand, DownCommand;
	
	public Switch( Command1 Up, Command1 Down) {
		UpCommand = Up; // concrete Command registers itself with the invoker 
		DownCommand = Down;
	}
	
	void flipUp( ) { // invoker calls back concrete Command, which executes the Command on the receiver 
		UpCommand . execute ( ) ;                           
	}
	
	void flipDown( ) {
		DownCommand . execute ( );
	}
}
class LightOnCommand implements Command1 {
	
	private Light myLight;
	
	public LightOnCommand ( Light L) {
		myLight  =  L;
	}
	public void execute( ) {
		myLight . turnOn( );
	}
}
class LightOffCommand implements Command1 {
	private Light myLight;
	public LightOffCommand ( Light L) {
		myLight  =  L;
	}
	public void execute( ) {
		myLight . turnOff( );
	}
}
class FanOnCommand implements Command1 {
	private Fan myFan;
	public FanOnCommand ( Fan F) {
		myFan  =  F;
	}
	public void execute( ) {
		myFan.startRotate( );
	}
}

class FanOffCommand implements Command1 {
	private Fan myFan;
	public FanOffCommand(Fan F) {
		myFan = F;
	}
	public void execute( ) {
		myFan.stopRotate( );
	}
}

public class CommandTest {
	public static void main(String[] args) {
		Light  testLight = new Light( );
		LightOnCommand testLOC = new LightOnCommand(testLight);
		LightOffCommand testLFC = new LightOffCommand(testLight);
		Switch testSwitch = new Switch( testLOC,testLFC);       
		testSwitch.flipUp( );
		testSwitch.flipDown( );
		Fan testFan = new Fan( );
		FanOnCommand foc = new FanOnCommand(testFan);
		FanOffCommand ffc = new FanOffCommand(testFan);
		Switch ts = new Switch( foc,ffc);
		ts.flipUp( );
		ts.flipDown( ); 
	}
}               

