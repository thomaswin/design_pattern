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


class Message {
	
	private int messageId;
	private String description;
	private String receiverName;
	
	public Message(int messageId, String description, String receiverName) {
		super();
		this.messageId = messageId;
		this.description = description;
		this.receiverName = receiverName;
	}

	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", description="
				+ description + "]";
	}
}

abstract class Receiver {
	
	protected Receiver next;
	
	public Receiver setNext(Receiver receiver) {
		next = receiver;
		return receiver;
	}	
	abstract public void processMessage(Message msg);
}

class SMS_Receiver extends Receiver {

	private String className = getClass().getName();
	@Override
	public void processMessage(Message msg) {
		if(isNeedToHandle(msg)){
			System.out.println(getClass().getSimpleName() + " handle message");
			System.out.println("content : " + msg.getDescription());
		} else {
			this.next.processMessage(msg);
		}
	}
	
	private boolean isNeedToHandle(Message msg) {
		String receiverName = msg.getReceiverName();
		if(receiverName.equals(className)) {
			return true;
		}
		return false;
	}
}

class CameraReceiver extends Receiver {

	private String className = getClass().getName();
	@Override
	public void processMessage(Message msg) {
		if(isNeedToHandle(msg)){
			System.out.println(getClass().getSimpleName() + " handle message");
			System.out.println("content : " + msg.getDescription());
		} else {
			this.next.processMessage(msg);
		}
	}
	
	private boolean isNeedToHandle(Message msg) {
		String receiverName = msg.getReceiverName();
		if(receiverName.equals(className)) {
			return true;
		}
		return false;
	}
}

class LocationReceiver extends Receiver {

	private String className = getClass().getName();
	@Override
	public void processMessage(Message msg) {
		if(isNeedToHandle(msg)){
			System.out.println(getClass().getSimpleName() + " handle message");
			System.out.println("content : " + msg.getDescription());
		} else {
			this.next.processMessage(msg);
		}
	}
	
	private boolean isNeedToHandle(Message msg) {
		String receiverName = msg.getReceiverName();
		if(receiverName.equals(className)) {
			return true;
		}
		return false;
	}
}

public class ChainResponsibilityExample4{
	public static void main(String[] args) {
		Receiver h1 = new SMS_Receiver();
		Receiver h2 = new CameraReceiver();
		Receiver h3 = new LocationReceiver();
		h1.setNext(h2);
		h2.setNext(h3);
		
		Message msg1 = new Message(1, "This is test message 1", LocationReceiver.class.getName());
		Message msg2 = new Message(2, "This is test message 2", CameraReceiver.class.getName());
		Message msg3 = new Message(3, "This is test message 3", SMS_Receiver.class.getName());
		h1.processMessage(msg1);
		h1.processMessage(msg2);
		h1.processMessage(msg3);
	} 
}


