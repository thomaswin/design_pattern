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

/**
 *  Memento interface to CalculatorOperator (Caretaker)
 */
interface PreviousCalculationToCareTaker {
	// no operations permitted for the caretaker
}

/**
 * Memento Interface to Originator
 * 
 * This interface allows the originator to restore its state
 */
interface PreviousCalculationToOriginator {
	public int getFirstNumber();
	public int getSecondNumber();	
}

/**
 * Memento Object Implementation
 * 
 * Note that this object implements both interfaces to Originator and CareTaker
 */
class PreviousCalculationImp implements PreviousCalculationToCareTaker, PreviousCalculationToOriginator {

	private int firstNumber;
	private int secondNumber;

	public PreviousCalculationImp(int firstNumber, int secondNumber) {
		this.firstNumber =  firstNumber;
		this.secondNumber = secondNumber;
	}

	@Override
	public int getFirstNumber() {
		return firstNumber;
	}

	@Override
	public int getSecondNumber() {
		return secondNumber;
	}
}

/**
 * Originator Interface
 */
interface Calculator {

	// Create Memento 
	public PreviousCalculationToCareTaker backupLastCalculation();

	// setMemento
	public void restorePreviousCalculation(PreviousCalculationToCareTaker memento);

	// Actual Services Provided by the originator 
	public int getCalculationResult();
	public void setFirstNumber(int firstNumber);
	public void setSecondNumber(int secondNumber);
}

/**
 * Originator Implementation
 */
class CalculatorImp implements Calculator {

	private int firstNumber;
	private int secondNumber;

	@Override
	public PreviousCalculationToCareTaker backupLastCalculation() {
		return new PreviousCalculationImp(firstNumber,secondNumber);
	}

	@Override
	public int getCalculationResult() {
		return firstNumber + secondNumber;
	}

	@Override
	public void restorePreviousCalculation(PreviousCalculationToCareTaker memento) {
		this.firstNumber = ((PreviousCalculationToOriginator)memento).getFirstNumber();
		this.secondNumber = ((PreviousCalculationToOriginator)memento).getSecondNumber();
	}

	@Override
	public void setFirstNumber(int firstNumber) {  this.firstNumber =  firstNumber; }

	@Override
	public void setSecondNumber(int secondNumber) { this.secondNumber = secondNumber; }
}

//http://www.oodesign.com/memento-pattern-calculator-example-java-sourcecode.html
public class MementoTest3 {
	public static void main(String[] args) {

		// program starts 
		Calculator calculator = new CalculatorImp();

		// assume user enters two numbers
		calculator.setFirstNumber(10);
		calculator.setSecondNumber(100);

		// find result
		System.out.println(calculator.getCalculationResult());

		// Store result of this calculation in case of error
		PreviousCalculationToCareTaker memento = calculator.backupLastCalculation();

		// user enters a number
		calculator.setFirstNumber(17);

		// user enters a wrong second number and calculates result
		calculator.setSecondNumber(-290);

		// calculate result
		System.out.println(calculator.getCalculationResult());

		// user hits CTRL + Z to undo last operation and see last result
		calculator.restorePreviousCalculation(memento);

		// result restored 
		System.out.println(calculator.getCalculationResult());	
	}
}




















