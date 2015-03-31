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

interface Window1 {
	public void draw();
	public String getDescription();
}


class SimpleWindow implements Window1 {
	public void draw(){		
	}
	public String getDescription() {
		return "simple window";
	}
}


abstract class WindowDecorator implements Window1 {
	protected Window1 decoratedwindow;
	
	public WindowDecorator (Window1 decoratedWindow) {
		this.decoratedwindow = decoratedWindow;
	}
	public void draw() {
		decoratedwindow.draw();						// delegation
	}
	public String getDescription() {
		return decoratedwindow.getDescription();	// delegation
	}
}

class VerticalScrollDecorator extends WindowDecorator {
	public VerticalScrollDecorator (Window1 decoratedWindow) {
		super(decoratedWindow);
	}
	
	@Override
	public void draw() {
		decoratedwindow.draw();
		drawVerticalScrollBar();
	}
	
	private void drawVerticalScrollBar() {
		// draw vertical scrollbar
	}
	
	@Override
	public String getDescription() {
		return decoratedwindow.getDescription() + ", including vertical scrollbars";
	}
}

class HorizontalScrollBarDecorator extends WindowDecorator {
    public HorizontalScrollBarDecorator (Window1 decoratedWindow) {
        super(decoratedWindow);
    }
 
    @Override
    public void draw() {
    	decoratedwindow.draw();
        drawHorizontalScrollBar();
    }
 
    private void drawHorizontalScrollBar() {
        // draw the horizontal scrollbar
    }
 
    @Override
    public String getDescription() {
        return decoratedwindow.getDescription() + ", including horizontal scrollbars";
    }
}

public class DecoratorExample1 {
	public static void main(String[] args) {
        // create a decorated Window with horizontal and vertical scrollbars
        Window1 decoratedWindow = new HorizontalScrollBarDecorator (
                new VerticalScrollDecorator(new SimpleWindow()));
 
        // print the Window's description
        System.out.println(decoratedWindow.getDescription());
    }
}















