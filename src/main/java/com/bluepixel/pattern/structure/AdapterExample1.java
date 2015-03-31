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



interface Camera{	
	void takePicture();
	void takePicture(int delay);
}

class DSLR_Camera implements Camera {	
	private Olympus_Camera camera = new Olympus_Camera();
	@Override
	public void takePicture() {
		camera.takePicture();
	}
	@Override
	public void takePicture(int delay) {
		camera.takePicture(delay);
	}
}

class Analog_Camera implements Camera {
	private Canon_Camera camera = new Canon_Camera();
	@Override
	public void takePicture() {
		camera.takePicture();
	}
	@Override
	public void takePicture(int delay) {
		camera.takePicture();
	}	
}

//************** legacy system ******************** 
class Olympus_Camera {
	public void takePicture(int delay) {
		System.out.println("Olympus picture with delay : " + delay);
	}
	public void takePicture() {
		System.out.println("Olympus picture");
	}
}

class Canon_Camera {	
	public void takePicture() {
		System.out.println("Canon picture");
	}
}


public class AdapterExample1 {
	public static void main(String[] args) {
		Camera[] cameras = {new DSLR_Camera(), new Analog_Camera() };
		for (Camera camera : cameras) {
			camera.takePicture();
			camera.takePicture(10);
		}
	}
}






