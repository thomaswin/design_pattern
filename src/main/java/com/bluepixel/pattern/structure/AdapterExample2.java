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

interface IControlBoard {
	void start();
	void stop();
	String checkStatus();
}

class MainControlSystem implements IControlBoard{

	private MotorControlSystem motorControlSystem;
	private EngineFuelControlSystem engineFuelControlSystem;
	private SpeedMonitorSystem speedMonitorSystem;
	@Override
	public void start() {
		motorControlSystem = new MotorControlSystem();
		engineFuelControlSystem = new EngineFuelControlSystem();
		speedMonitorSystem = new SpeedMonitorSystem();

		motorControlSystem.setup();
		engineFuelControlSystem.setup();
		speedMonitorSystem.setup();

		motorControlSystem.start();
		engineFuelControlSystem.start();
		speedMonitorSystem.start();
	}

	@Override
	public void stop() {
		motorControlSystem.stop();
		engineFuelControlSystem.stop();
		speedMonitorSystem.stop();

	}

	@Override
	public String checkStatus() {
		StringBuilder str = new StringBuilder();
		str.append(motorControlSystem.checkStatus() + "\n");
		str.append(engineFuelControlSystem.checkStatus() + "\n");
		str.append(speedMonitorSystem.checkStatus() + "\n");
		return str.toString();
	}
}

class MotorControlSystem {
	public void setup(){
		System.out.println("MotorControlSystem : setup");
	}
	public void start(){
		System.out.println("MotorControlSystem : start");
	}
	public void stop(){
		System.out.println("MotorControlSystem : stop");
	}
	public String checkStatus() {
		return "MotorControlSystem : 2000rev/s";
	}
}

class EngineFuelControlSystem {
	public void setup(){
		System.out.println("EngineFuelControlSystem : setup");
	}
	public void start(){
		System.out.println("EngineFuelControlSystem : start");
	}
	public void stop(){
		System.out.println("EngineFuelControlSystem : stop");
	}
	public String checkStatus() {
		return "EngineFuelControlSystem : 50 gallon remaining";
	}
}

class SpeedMonitorSystem {
	public void setup(){
		System.out.println("SpeedMonitorSystem : setup");
	}
	public void start(){
		System.out.println("SpeedMonitorSystem : start");
	}
	public void stop(){
		System.out.println("SpeedMonitorSystem : stop");
	}
	public String checkStatus() {
		return "SpeedMonitorSystem : 125km/s";
	}
}

public class AdapterExample2 {
	public static void main(String[] args) {
		IControlBoard controlBoard = new MainControlSystem();
		controlBoard.start();
		controlBoard.stop();
		String status = controlBoard.checkStatus();
		System.out.println(status);
	}
}






