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

abstract class Security {
	protected SecurityAPI securityAPI;
	protected Security (SecurityAPI api) {
		this.securityAPI = api;
	}
	
	public abstract void encrypt();
	public abstract void decrypt();
	public abstract void setSecurityLevel(int level);
}

class SecurityManager extends Security {

	protected SecurityManager(SecurityAPI api) {
		super(api);
	}


	@Override
	public void encrypt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrypt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSecurityLevel(int level) {
		// TODO Auto-generated method stub
		
	}	
}

interface SecurityAPI {
	public void encrypt();
	public void decrypt();
}

class AES_SecurityAPI implements SecurityAPI {

	@Override
	public void encrypt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrypt() {
		// TODO Auto-generated method stub
		
	}
}

class DES_SecurityAPI implements SecurityAPI {

	@Override
	public void encrypt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrypt() {
		// TODO Auto-generated method stub
		
	}
}

public class BridgeExample3 {
	public static void main(String[] args) {
		Security security = new SecurityManager(new AES_SecurityAPI());
		security.encrypt();
	}
}




















