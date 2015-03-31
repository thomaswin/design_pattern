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


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MediatorTest extends JFrame implements ActionListener {

	/**
	 	Mediator - defines the interface for communication between Colleague objects
		ConcreteMediator - implements the Mediator interface and coordinates communication between Colleague objects. It is aware of all the Colleagues and their purpose with regards to inter communication.
		ConcreteColleague - communicates with other Colleagues through its Mediator 
	 */
	private static final long serialVersionUID = 1L;
	IMediator med = new Mediator();

	MediatorTest() {
		JPanel p = new JPanel();
		p.add(new BtnView(this, med));
		p.add(new BtnBook(this, med));
		p.add(new BtnSearch(this, med));
		getContentPane().add(new LblDisplay(med), "North");
		getContentPane().add(p, "South");
		setSize(400, 200);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		Command2 comd = (Command2) ae.getSource();
		comd.execute();
	}

	public static void main(String[] args) {
		new MediatorTest();
	}
}

//Colleague interface
interface Command2 {
	void execute();
}

//Abstract Mediator
interface IMediator {
	void book();
	void view();
	void search();
	void registerView(BtnView v);
	void registerSearch(BtnSearch s);
	void registerBook(BtnBook b);
	void registerDisplay(LblDisplay d);
}

//Concrete mediator
class Mediator implements IMediator {

	BtnView btnView;
	BtnSearch btnSearch;
	BtnBook btnBook;
	LblDisplay show;

	//....
	public void registerView(BtnView v) {
		btnView = v;
	}

	public void registerSearch(BtnSearch s) {
		btnSearch = s;
	}

	public void registerBook(BtnBook b) {
		btnBook = b;
	}

	public void registerDisplay(LblDisplay d) {
		show = d;
	}

	public void book() {
		btnBook.setEnabled(false);
		btnView.setEnabled(true);
		btnSearch.setEnabled(true);
		show.setText("booking...");
	}

	public void view() {
		btnView.setEnabled(false);
		btnSearch.setEnabled(true);
		btnBook.setEnabled(true);
		show.setText("viewing...");
	}

	public void search() {
		btnSearch.setEnabled(false);
		btnView.setEnabled(true);
		btnBook.setEnabled(true);
		show.setText("searching...");
	}

}

//A concrete colleague
class BtnView extends JButton implements Command2 {

	IMediator med;

	BtnView(ActionListener al, IMediator m) {
		super("View");
		addActionListener(al);
		med = m;
		med.registerView(this);
	}

	public void execute() {
		med.view();
	}

}

//A concrete colleague
class BtnSearch extends JButton implements Command2 {

	IMediator med;

	BtnSearch(ActionListener al, IMediator m) {
		super("Search");
		addActionListener(al);
		med = m;
		med.registerSearch(this);
	}

	public void execute() {
		med.search();
	}

}

//A concrete colleague
class BtnBook extends JButton implements Command2 {

	IMediator med;

	BtnBook(ActionListener al, IMediator m) {
		super("Book");
		addActionListener(al);
		med = m;
		med.registerBook(this);
	}

	public void execute() {
		med.book();
	}

}

class LblDisplay extends JLabel {

	IMediator med;

	LblDisplay(IMediator m) {
		super("Just start...");
		med = m;
		med.registerDisplay(this);
		setFont(new Font("Arial", Font.BOLD, 24));
	}

}
