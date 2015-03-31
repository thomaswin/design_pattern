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

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter {
	public static void main(String[] args) {
		String expression = "w x z - +";
		Evaluator sentence = new Evaluator(expression);
		Map<String,Expression> variables = new HashMap<String,Expression>();
		variables.put("w", new Number(5));
		variables.put("x", new Number(10));
		variables.put("z", new Number(42));
		int result = sentence.interpret(variables);
		System.out.println(result);
	}
}

interface Expression {
	public int interpret(Map<String, Expression> variables);
}

class Number implements Expression {
	private int number;
	public Number(int number)       { this.number = number; }
	public int interpret(Map<String,Expression> variables)  { return number; }
}

class Plus implements Expression {
	Expression leftOperand;
	Expression rightOperand;
	public Plus(Expression left, Expression right) { 
		leftOperand = left; 
		rightOperand = right;
	}

	public int interpret(Map<String,Expression> variables)  { 
		return leftOperand.interpret(variables) + rightOperand.interpret(variables);
	}
}

class Minus implements Expression {
	Expression leftOperand;
	Expression rightOperand;
	public Minus(Expression left, Expression right) { 
		leftOperand = left; 
		rightOperand = right;
	}

	public int interpret(Map<String,Expression> variables)  { 
		return leftOperand.interpret(variables) - rightOperand.interpret(variables);
	}
}

class Variable implements Expression {
	private String name;
	public Variable(String name)       { this.name = name; }
	public int interpret(Map<String,Expression> variables)  { 
		if(null==variables.get(name)) return 0; //Either return new Number(0).
		return variables.get(name).interpret(variables); 
	}
}

class Evaluator implements Expression {
	private Expression syntaxTree;

	public Evaluator(String expression) {
		Stack<Expression> expressionStack = new Stack<Expression>();
		for (String token : expression.split(" ")) {
			if  (token.equals("+")) {
				Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
				expressionStack.push( subExpression );
			}
			else if (token.equals("-")) {
				// it's necessary remove first the right operand from the stack
				Expression right = expressionStack.pop();
				// ..and after the left one
				Expression left = expressionStack.pop();
				Expression subExpression = new Minus(left, right);
				expressionStack.push( subExpression );
			}
			else                        
				expressionStack.push( new Variable(token) );
		}
		syntaxTree = expressionStack.pop();
	}

	public int interpret(Map<String,Expression> context) {
		return syntaxTree.interpret(context);
	}
}
