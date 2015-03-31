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

class MessagePayload {
	int messageType;
	Payload payload;
	
	private class Payload {
		public String message;
		public Payload() {
			message = "empty";
		}
	}
}

abstract class MessageReceiveHandler{ 
	public static int NORMAL = 1;
	public static int PAID	 = 2;
	
	protected MessageReceiveHandler next;
	protected int mask;
	
	public MessageReceiveHandler setNext(MessageReceiveHandler handler) {
		next = handler;
		return handler;
	}
	
	public void process(MessagePayload message) {
		if( message.messageType == mask) {
			processMessage(message);
		}
		if (next != null) {
			next.process(message);
		}
	}
	abstract public void processMessage(MessagePayload message);
}

class SecurityHandler extends MessageReceiveHandler {

	public SecurityHandler(int mask) {
		this.mask = mask;
	}
	
	@Override
	public void processMessage(MessagePayload message) {
		// TODO Auto-generated method stub		
	}	
}

class NotificationHandler extends MessageReceiveHandler {
	public NotificationHandler(int mask) {
		this.mask = mask;
	}
	
	@Override
	public void processMessage(MessagePayload message) {
		// TODO Auto-generated method stub
		
	}	
}
public class ChainResponsibilityExample6 {
	public static void main(String[] args) {
		MessageReceiveHandler handler1 = new SecurityHandler(MessageReceiveHandler.NORMAL);
		MessageReceiveHandler handler2 = new SecurityHandler(MessageReceiveHandler.NORMAL);
		handler1.setNext(handler2);
		handler1.process(new MessagePayload());
	}
}
