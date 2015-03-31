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

public class Specification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

interface ISpecification {
	boolean IsSatisfiedBy(Object candidate);
	ISpecification And(ISpecification other);
	ISpecification Or(ISpecification other);
	ISpecification Not();
}

abstract class CompositeSpecification implements ISpecification {
	public abstract boolean IsSatisfiedBy(Object candidate);

	public ISpecification And(ISpecification other) {
		return new AndSpecification(this, other);
	}

	public ISpecification Or(ISpecification other) {
		return new OrSpecification(this, other);
	}

	public ISpecification Not() {
		return new NotSpecification(this);
	}
}

class AndSpecification extends CompositeSpecification {
	private ISpecification One;
	private ISpecification Other;

	public AndSpecification(ISpecification x, ISpecification y) {
		One = x;
		Other = y;
	}

	public boolean IsSatisfiedBy(Object candidate) {
		return One.IsSatisfiedBy(candidate) && Other.IsSatisfiedBy(candidate);
	}
}

class OrSpecification extends CompositeSpecification {
	private ISpecification One;
	private ISpecification Other;

	public OrSpecification(ISpecification x, ISpecification y) {
		One = x;
		Other = y;
	}

	public boolean IsSatisfiedBy(Object candidate) {
		return One.IsSatisfiedBy(candidate) || Other.IsSatisfiedBy(candidate);
	}
}

class NotSpecification extends CompositeSpecification {
	private ISpecification Wrapped;

	public NotSpecification(ISpecification x) {
		Wrapped = x;
	}

	public boolean IsSatisfiedBy(Object candidate) {
		return !Wrapped.IsSatisfiedBy(candidate);
	}
}

