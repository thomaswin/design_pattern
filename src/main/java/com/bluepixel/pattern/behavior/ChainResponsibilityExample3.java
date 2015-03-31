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

import java.io.BufferedReader;
import java.io.InputStreamReader;


abstract class Operator {

	public static int MACHINE = 1;
	public static int QUALITY = 2;
	public static int PAINTING = 3;
	protected int mask;

	protected Operator next;

	public Operator setNext(Operator operator) {
		next = operator;
		return operator;
	}
	public void work(String msg, int priority ) {
		if(priority == mask) {
			processWork(msg);
		} 
		if(next != null) {
			next.work(msg, priority);
		}
	}
	abstract public void processWork(String msg);
}

class MachineOperator extends Operator {

	public MachineOperator(int mask) {
		this.mask = mask;
	}
	@Override
	public void processWork(String msg) {
		System.out.println("machine operator done : " + msg);
	}
}

class PaintingOperator extends Operator {

	public PaintingOperator(int mask) {
		this.mask = mask;
	}
	@Override
	public void processWork(String msg) {
		System.out.println("painting operator done : " + msg);
	}
}

class QualityOperator extends Operator {

	public QualityOperator(int mask) {
		this.mask = mask;
	}
	@Override
	public void processWork(String msg) {
		System.out.println("Quality operator done : " + msg);
	}
}
class SuperVisor extends Operator {

	public SuperVisor(int mask) {
		this.mask = mask;
	}
	public void work(String msg, int priority ) {
		if(priority == Operator.MACHINE || priority == Operator.PAINTING || priority == Operator.QUALITY) {
			processWork(msg);
		} 
		if(next != null) {
			next.work(msg, priority);
		}
	}
	
	@Override
	public void processWork(String msg) {
		System.out.println("supervisor approved : " + msg);
	}
}


public class ChainResponsibilityExample3 {
	public static void main(String[] args) {
		Operator operator1, operator2;
		
		operator1 = operator2 = new MachineOperator(Operator.MACHINE);
		operator2 = operator2.setNext(new PaintingOperator(Operator.PAINTING));
		operator2 = operator2.setNext(new QualityOperator(Operator.QUALITY));
		operator2 = operator2.setNext(new SuperVisor(0));
		
		operator1.work("fabricating part xyx ", Operator.MACHINE);
		operator1.work("Painting part xyx ", Operator.PAINTING);
		operator1.work("Qaulity inspection part xyx ", Operator.QUALITY);
		
	}
}
