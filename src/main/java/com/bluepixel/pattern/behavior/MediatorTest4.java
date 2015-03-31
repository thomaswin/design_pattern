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
import java.util.concurrent.BrokenBarrierException;


interface Broker_Mediator {
	public void offer(Company company, int price);
	void getDeal(Company company, int price);
	void register(Company company);
}


abstract class Company {
	private Broker_Mediator mediator;

	public Company(Broker_Mediator mediator) {
		this.mediator = mediator;
	}
	public Broker_Mediator getMediator() { 
		return mediator; 
	}

	public void offer(int price) {
		mediator.offer(this, price);
	}

	public void register() {
		mediator.register(this);
	}
	public void getDeal(int price) {
		mediator.getDeal(this, price);
	}

	public abstract void receivePayment(int price);
	public abstract void receiveBidInfo(int price);	
}

class OverseaCompany extends Company{

	public OverseaCompany(Broker_Mediator mediator) {
		super(mediator);
	}

	@Override
	public void receivePayment(int price) {
		System.out.println(getClass().getSimpleName() + " received payment " + price);
	}

	@Override
	public void receiveBidInfo(int price) {
		System.out.println(getClass().getSimpleName() + " received bidding " + price);
		getMediator().getDeal(this, price -1);
	}
}

class LocalCompany extends Company {

	public LocalCompany(Broker_Mediator mediator) {
		super(mediator);
	}

	@Override
	public void receivePayment(int price) {
		System.out.println(getClass().getSimpleName() + " received payment " + price);
	}

	@Override
	public void receiveBidInfo(int price) {
		System.out.println(getClass().getSimpleName() + " received bidding " + price);
		getMediator().getDeal(this, price -1);
	}
}

class TradingHouse implements Broker_Mediator {

	private ArrayList<Company> companies;

	public TradingHouse() {
		companies = new ArrayList<Company>();
	}

	@Override
	public void register(Company company) {
		companies.add(company);
	}

	@Override
	public void getDeal(Company company, int price) {
		for (int i = 0; i < companies.size(); i++){
			if (company.equals(companies.get(i))) {
				companies.get(i).receivePayment(price);
			}
		}
	}

	@Override
	public void offer(Company company, int price) {
		for (int i = 0; i < companies.size(); i++){
			if (company.equals(companies.get(i))) 
				continue;
			companies.get(i).receiveBidInfo(price);
		}
	}



}

public class MediatorTest4 {

	public static void main(String[] args) {
		TradingHouse mediator = new TradingHouse();
		LocalCompany localCompany = new LocalCompany(mediator);
		OverseaCompany overseaCompany = new OverseaCompany(mediator);

		localCompany.register();
		overseaCompany.register();
		localCompany.offer(10);
	}
}
















