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

package com.bluepixel.pattern.behavior;//package behavioral_design;
//
////http://java.dzone.com/articles/design-patterns-visitor
//public class Visitor_2 {
//	public static void main(String[] args) {
//		
//	}
//}
//
//interface Visitable {
//	public void accept(Visitor2 visitor);
//}
//
//class Book implements Visitable { 
//   private double price; 
//   private double weight;
//
//   //accept the visitor
//   public void accept(Visitor2 vistor) {
//      visitor.visit(this);
//   }
//
//   public double getPrice()
//   {
//      return price;
//   }
//
//   public double getWeight() {
//      return weight;
//   }
//} 
//interface Visitor2 {
//   public void visit(Book book);
//   //visit other concrete items 
//   public void visit(CD cd);
//   public void visit(DVD dvd);   
//}