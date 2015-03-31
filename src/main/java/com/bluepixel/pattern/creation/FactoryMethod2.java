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

import java.io.InputStream;
import java.lang.Math;

/*
 * Descriptive names * 
 */
//***********************************************************************
class Complex {
	public static Complex fromCartesian(double real, double imaginary) {
		return new Complex(real, imaginary);
	}

	public static Complex fromPolar(double modulus, double angle) {
		return new Complex(modulus * Math.cos(angle), modulus * Math.sin(angle));
	}


	private Complex(double a, double b) {
		//...
	}
}

/*
 * Descriptive names * 
 */
//***********************************************************************

interface ImageReader {
	public DecodedImage getDecodedImage();
}

class DecodedImage{

}
class GifReader implements ImageReader {
	public GifReader(InputStream is) {
		// TODO Auto-generated constructor stub
	}

	public DecodedImage getDecodedImage() {
		// ...
		return decodedImage();
	}

	private DecodedImage decodedImage() {
		// TODO Auto-generated method stub
		return null;
	}
}

class JpegReader implements ImageReader {
	public JpegReader(InputStream is) {
		// TODO Auto-generated constructor stub
	}

	public DecodedImage getDecodedImage() {
		// ...
		return decodedImage();
	}

	private DecodedImage decodedImage() {
		// TODO Auto-generated method stub
		return null;
	}
}

class ImageReaderFactory {
	static final int GIF = 1;
	static final int JPEG = 2;
	public static ImageReader getImageReader(InputStream is) {
		int imageType = determineImageType(is);

		switch (imageType) {
		case ImageReaderFactory.GIF:
			return new GifReader(is);
		case ImageReaderFactory.JPEG:
			return new JpegReader(is);
			// etc.
		}
		return null;
	}

	private static int determineImageType(InputStream is) {
		// TODO Auto-generated method stub
		return 0;
	}
}

/*
 * Implementation * 
 */
//***********************************************************************

class MazeGame {
	public MazeGame() {
		Room room1 = makeRoom();
		Room room2 = makeRoom();
		room1.connect(room2);
		this.addRoom(room1);
		this.addRoom(room2);
	}

	private void addRoom(Room room1) {
	}

	protected Room makeRoom() {
		return new OrdinaryRoom();
	}
}

interface Room{
	void connect(Room room2);
}

class OrdinaryRoom implements Room{
	@Override
	public void connect(Room room2) {
		// TODO Auto-generated method stub		
	}
}

class MagicMazeGame extends MazeGame {
	@Override
	protected Room makeRoom() {
		return new MagicRoom();
	}
}
class MagicRoom implements Room{
	@Override
	public void connect(Room room2) {
		// TODO Auto-generated method stub		
	}
}


public class FactoryMethod2 {
	public static void main(String[] args) {
		Complex c = Complex.fromPolar(1, Math.PI);

	}
}