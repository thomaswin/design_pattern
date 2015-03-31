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

import java.util.ArrayList;
import java.util.List;

public class MementoTest1 {
	
	public static void main(String[] args) {
		List<Originator.Memento> savedStates = new ArrayList<Originator.Memento>();

		Originator originator = new Originator();
		originator.set("SpeedState");
		originator.set("State2");
		savedStates.add(originator.saveToMemento());
		originator.set("State3");
		// We can request multiple mementos, and choose which one to roll back to.
		savedStates.add(originator.saveToMemento());
		originator.set("State4");

		originator.restoreFromMemento(savedStates.get(1));   
	}
	
}

class Originator {
	private String state;
	// The class could also contain additional data that is not part of the
	// state saved in the memento.

	public void set(String state) {
		System.out.println("Originator: Setting state to " + state);
		this.state = state;
	}

	public Memento saveToMemento() {
		System.out.println("Originator: Saving to Memento.");
		return new Memento(state);
	}

	public void restoreFromMemento(Memento memento) {
		state = memento.getSavedState();
		System.out.println("Originator: State after restoring from Memento: " + state);
	}

	public static class Memento {
		private final String state;

		public Memento(String stateToSave) {
			state = stateToSave;
		}

		public String getSavedState() {
			return state;
		}
	}
}












