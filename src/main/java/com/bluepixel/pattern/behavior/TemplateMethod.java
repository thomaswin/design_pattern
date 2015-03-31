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

public class TemplateMethod {
	public static void main(String[] args) {
		Game game1 = new Monopoly();
		game1.playOneGame(20);
	}
}
abstract class Game {

	protected int playersCount;
	abstract void initializeGame();
	abstract void makePlay(int player);
	abstract boolean endOfGame();
	abstract void printWinner();

	/* A template method : */
	public final void playOneGame(int playersCount) {
		this.playersCount = playersCount;
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			makePlay(j);
			j = (j + 1) % playersCount;
		}
		printWinner();
	}
}

//Now we can extend this class in order 
//to implement actual games:

class Monopoly extends Game {

	/* Implementation of necessary concrete methods */
	void initializeGame() {
		// Initialize players
		// Initialize money
	}
	void makePlay(int player) {
		// Process one turn of player
	}
	boolean endOfGame() {
		return false;
		// Return true if game is over 
		// according to Monopoly rules
	}
	void printWinner() {
		// Display who won
	}
	/* Specific declarations for the Monopoly game. */

	// ...
}

class Chess extends Game {

	/* Implementation of necessary concrete methods */
	void initializeGame() {
		// Initialize players
		// Put the pieces on the board
	}
	void makePlay(int player) {
		// Process a turn for the player
	}
	boolean endOfGame() {
		return false;
		// Return true if in Checkmate or 
		// Stalemate has been reached
	}
	void printWinner() {
		// Display the winning player
	}
	/* Specific declarations for the chess game. */

	// ...
}




