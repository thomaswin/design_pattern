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

package com.bluepixel.pattern.creation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StandardClass implements Comparable<StandardClass>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private int serial_no;
	private int data;
	private String data1;
	private String originalJson;
	
	public int getSerial_no() { return serial_no; }

	public void setSerial_no(int serial_no) { this.serial_no = serial_no; }

	public int getData() { return data; }

	public void setData(int data) { this.data = data; }

	public String getData1() { return data1; }

	public void setData1(String data1) { this.data1 = data1; }
	
	public StandardClass() {
		super();
	}

	public StandardClass(int serial_no, int data, String data1) {
		super();
		this.serial_no = serial_no;
		this.data = data;
		this.data1 = data1;
	}
	
	public StandardClass(String jsonStandardInfo) {
		originalJson = jsonStandardInfo;		
	}

	@Override
	protected StandardClass clone(){		
		StandardClass obj = new StandardClass();
		obj.setSerial_no(this.serial_no);
		obj.setData(this.data);
		obj.setData1(this.data1);
		return obj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		result = prime * result + ((data1 == null) ? 0 : data1.hashCode());
		result = prime * result + serial_no;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardClass other = (StandardClass) obj;
		if (data != other.data)
			return false;
		if (data1 == null) {
			if (other.data1 != null)
				return false;
		} else if (!data1.equals(other.data1))
			return false;
		if (serial_no != other.serial_no)
			return false;
		return true;
	}

	@Override
	public int compareTo(StandardClass aThat) {
		//ASCENDING ORDER
		
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;		

		if (this.data < aThat.data) 
			return BEFORE;
		else if(this.data > aThat.data){
			return AFTER;
		} else 
			return EQUAL;
	}

	@Override
	public String toString() {
		return "StandardClass [serial_no=" + serial_no + ", data=" + data
				+ ", data1=" + data1 + "]";
	}

	public static void main (String args[]) {


		StandardClass myClass = new StandardClass();
		myClass.setSerial_no(123456);
		myClass.setData(4444);
		myClass.setData1("This is class test");

		StandardClass class1 = myClass.clone();
		class1.setData(3333);
		System.out.println(myClass.equals(class1));		
		
		System.out.println(myClass.hashCode());
		System.out.println(class1.hashCode());
		System.out.println(myClass.compareTo(class1));
		
		List<StandardClass> myData = new ArrayList<StandardClass>();
		myData.add(myClass);
		myData.add(class1);
		Collections.sort(myData);
		System.out.println(myData);
		
		Collections.sort(myData, new Comparator<StandardClass>(){
			@Override
			public int compare(StandardClass arg0, StandardClass arg1) {
				final int BEFORE = -1;
				final int EQUAL = 0;
				final int AFTER = 1;
				if (arg0.data > arg1.data) 
					return BEFORE;
				else if(arg0.data < arg1.data){
					return AFTER;
				} else 
					return EQUAL;
				
			}
		});
		
		System.out.println(myData);
		ObjectInputStream ois = null;
		try{

			String fileName = "serialized_data";
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(myClass);
			oos.writeObject(class1);
			oos.close();

			FileInputStream fin = new FileInputStream(fileName);
			ois = new ObjectInputStream(fin);
			Object obj = null;


			while ((obj = ois.readObject()) != null) {
				if (obj instanceof StandardClass) {
					System.out.println(((StandardClass)obj).toString());
				}
			}
			ois.close();

		}catch(Exception ex){

		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}


