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

/*
  
 - abstract step of construction of object so that 
   implementation of these steps can construct different representation of object.
   - build product in accordance with composite pattern
   - seperate construction of complex object from its representation.
   - same construction process can create different representations 
   - focus constructing a complex object step by step.
   - abstract factory emphasize a family of product object. 
   - builder return the product as final step.
   - FActory method (less complex more customizable) -> abstract factory -> prototype -> builder (more flexible, more complex)
  
  
 */
class Pizza { // product
	private String dough;
	private String sauce;
	private String topping;
	private String str;

	private Pizza() {}

	public static Builder createBuilder() {
		return new Builder();
	}

	public String getDough() {
		return dough;
	}

	public String getSauce() {
		return sauce;
	}

	public String getTopping() {
		return topping;
	}

	@Override
	public String toString() {
		if (str == null)
			str = "Dough:" + dough + " Topping:" + topping + " Sauce:" + sauce;
		return str;
	}

	/*
	 * Builder
	 */

	public static class Builder {
		private final Pizza obj = new Pizza();
		private boolean done;

		private Builder() {}

		public Pizza build() {
			done = true;
			return obj;
		}

		public Builder setDough(String dough) {
			check();
			obj.dough = dough;
			return this;
		}

		public Builder setSauce(String sauce) {
			check();
			obj.sauce = sauce;
			return this;
		}

		public Builder setTopping(String topping) {
			check();
			obj.topping = topping;
			return this;
		}

		private void check() {
			if (done)
				throw new IllegalArgumentException("Do use other builder to create new instance");
		}
	}
}

public class Builder {
	public static void main(String[] args) {
		Pizza hawaiianPizza = Pizza.createBuilder()
				.setDough("cross")
				.setTopping("ham+pineapple")
				.setSauce("mild")
				.build();

		System.out.println("Hawaiian Pizza: " + hawaiianPizza);
	}
}
