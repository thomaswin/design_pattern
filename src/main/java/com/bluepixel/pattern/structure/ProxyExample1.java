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

interface Image__ {
	public void showImage();	
}

class ImageProxy implements Image__ {
	
	private String imageFilePath;
	private Image__ proxifiedImage;

	public ImageProxy(String imageFilePath) {
		this.imageFilePath= imageFilePath;	
	}

	@Override
	public void showImage() {		
		proxifiedImage = new HighResolutionImage(imageFilePath);
		proxifiedImage.showImage();
	}
}

class HighResolutionImage implements Image__ {
	public HighResolutionImage(String imageFilePath){
		loadImage(imageFilePath);
	}

	private void loadImage(String imageFilePath){
		// TODO load image from resouce location
	}

	@Override
	public void showImage(){
	}
}

public class ProxyExample1{
	public static void main(String[] args) {		
		Image__ highResolutionImage1 = new ImageProxy("sample/veryHighResPhoto1.jpeg");
		Image__ highResolutionImage2 = new ImageProxy("sample/veryHighResPhoto2.jpeg");
		Image__ highResolutionImage3 = new ImageProxy("sample/veryHighResPhoto3.jpeg");
		highResolutionImage1.showImage();

		Image__ highResolutionImageNoProxy1 = new HighResolutionImage("sample/veryHighResPhoto1.jpeg");
		Image__ highResolutionImageNoProxy2 = new HighResolutionImage("sample/veryHighResPhoto2.jpeg");
		Image__ highResolutionImageBoProxy3 = new HighResolutionImage("sample/veryHighResPhoto3.jpeg");
		highResolutionImageNoProxy2.showImage();		
	}
}
































