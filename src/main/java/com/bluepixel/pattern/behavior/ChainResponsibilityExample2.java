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

class Request {	
	private int m_value;
	private String m_description;

	public Request(String description, int value)
	{
		m_description = description;
		m_value = value;
	}

	public int getValue()
	{
		return m_value;
	}

	public String getDescription()
	{
		return m_description;
	}          
}

abstract class Handler
{
	protected Handler m_successor;
	public void setSuccessor(Handler successor)
	{
		m_successor = successor;
	}

	public abstract void handleRequest(Request request);
}

class ConcreteHandlerOne extends Handler
{
	public void handleRequest(Request request)
	{
		if (request.getValue() < 0)
		{           //if request is eligible handle it
			System.out.println("Negative values are handled by ConcreteHandlerOne:");
			System.out.println("\tConcreteHandlerOne.HandleRequest : " + request.getDescription()
					+ request.getValue());
		}
		else
		{
			m_successor.handleRequest(request);
		}
	}
}

class ConcreteHandlerThree extends Handler
{
	public void handleRequest(Request request)
	{
		if (request.getValue() >= 0)
		{           //if request is eligible handle it
			System.out.println("Zero values are handled by ConcreteHandlerThree:");
			System.out.println("\tConcreteHandlerThree.HandleRequest : " + request.getDescription()
					+ request.getValue());
		}
		else
		{
			m_successor.handleRequest(request);
		}
	}
}

class ConcreteHandlerTwo extends Handler
{
	public void handleRequest(Request request)
	{
		if (request.getValue() > 0)
		{           //if request is eligible handle it
			System.out.println("Positive values are handled by ConcreteHandlerTwo:");
			System.out.println("\tConcreteHandlerTwo.HandleRequest : " + request.getDescription()
					+ request.getValue());
		}
		else
		{
			m_successor.handleRequest(request);
		}
	}
}
public class ChainResponsibilityExample2 {
	public static void main(String[] args) {
		// Setup Chain of Responsibility
		Handler h1 = new ConcreteHandlerOne();
		Handler h2 = new ConcreteHandlerTwo();
		Handler h3 = new ConcreteHandlerThree();
		h1.setSuccessor(h2);
		h2.setSuccessor(h3);

		// Send requests to the chain
		h1.handleRequest(new Request("Negative Value ", -1));
		h1.handleRequest(new Request("Negative Value ",  0));
		h1.handleRequest(new Request("Negative Value ",  1));
		h1.handleRequest(new Request("Negative Value ",  2));
		h1.handleRequest(new Request("Negative Value ", -5));	    
	}
}
