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

abstract class Window {

	protected WindowAPI windowAPI;

	protected Window (WindowAPI api) {
		this.windowAPI = api;
	}
	public abstract void show();
	public abstract void hide();
	public abstract void delete();	
	public abstract void locate(int x, int y);
	public abstract void resize(int width, int height);
}

class MyWindow extends Window {

	protected MyWindow(int trasparency, WindowAPI api) {
		super(api);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
	}

	@Override
	public void locate(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}	
}

interface WindowAPI {
	public void show();
	public void move(int x, int y);
	public void resize(int width, int height);
}

class StealthAPI implements WindowAPI {

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
}

class NormalAPI implements WindowAPI {

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}
}

public class BridgeExample2 {
	public static void main(String[] args) {
		
		Window [] windows = new Window [] {
				new MyWindow(123, new StealthAPI()),
				new MyWindow(456, new NormalAPI()),
		};
		
		for (Window window : windows) {
			window.show();
			window.hide();
			window.resize(200, 100);
		}
	}
}




















