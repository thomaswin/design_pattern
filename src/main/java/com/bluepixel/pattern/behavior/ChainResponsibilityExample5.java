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


class ResultMessage {
	int type;
	String messageId;
	String descriptioin;
	public ResultMessage(String messageId, String description, int type) {
		this.messageId = messageId;
		this.descriptioin = description;
		this.type = type;
	}
	@Override
	public String toString() {
		return "ResultMessage [type=" + type + ", messageId=" + messageId
				+ ", descriptioin=" + descriptioin + "]";
	}
	
	
	
}

abstract class MessageHandler{ 
	public static int NORMAL = 1;
	public static int PAID	 = 2;
	
	protected MessageHandler next;
	protected int mask;
	
	public MessageHandler setNext(MessageHandler handler) {
		next = handler;
		return handler;
	}
	
	public void work(ResultMessage message) {
		if( message.type == mask) {
			processMessage(message);
		}
		if (next != null) {
			next.work(message);
		}
	}
	abstract public void processMessage(ResultMessage message);
}

class NormalHandler extends MessageHandler{
	
	public NormalHandler(int mask) {
		this.mask = mask;
	}
	@Override
	public void processMessage(ResultMessage message) {
		System.out.println("NormalHandler process : " + message);
	}	
}

class PaidHandler extends MessageHandler {
	
	public PaidHandler(int mask) {
		this.mask = mask;
	}
	
	@Override
	public void processMessage(ResultMessage message) {
		System.out.println("PaidHandler process : " + message);
	}
}

public class ChainResponsibilityExample5 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageHandler handler1 = new NormalHandler(MessageHandler.NORMAL);
		MessageHandler handler2 = new PaidHandler(MessageHandler.PAID);
		handler1.setNext(handler2);

		handler1.work(new ResultMessage("123" , "hello world", MessageHandler.NORMAL));
	}

}
