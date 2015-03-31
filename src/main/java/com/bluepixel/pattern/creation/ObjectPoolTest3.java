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


import java.util.Enumeration;
import java.util.Hashtable;


abstract class ObjectPool3<T> { 

	private long expirationTime;   
	private Hashtable<T, Long> busy;
	private Hashtable<T, Long> available;
	private CleanUpThread cleaner;

	abstract T create();
	abstract boolean validate(T obj);
	abstract void expire(T obj);

	ObjectPool3(){
		expirationTime = 1000 * 3;
		busy 		= new Hashtable<T, Long>();         
		available 	= new Hashtable<T, Long>();

		cleaner = new CleanUpThread( this, expirationTime );
		cleaner.start();
	}

	synchronized void checkIn(T obj){
		System.out.println("checkIn");
		busy.remove(obj);
		available.put(obj, new Long(System.currentTimeMillis()));
	}

	synchronized Object checkOut(){
		System.out.println("checkOut");
		long now = System.currentTimeMillis();

		T obj;		

		if(available.size() > 0){
			Enumeration<T> e = available.keys();
			while(e.hasMoreElements()) {
				obj = e.nextElement();

				long startTime = available.get(obj).longValue();
				long usageTime = now - startTime;
				boolean isExpired = usageTime > expirationTime;
				if(isExpired){
					available.remove(obj);
					expire(obj);
					obj = null;
				} else {
					if(validate(obj)){
						available.remove(obj);
						busy.put(obj, new Long(now));
						return obj;
					} else {
						available.remove(obj);
						expire(obj);
						obj = null;
					}
				}
			}
		}
		obj = create();
		busy.put(obj, new Long(now));
		return obj;
	}	
	
    synchronized void cleanUp() {
    	System.out.println("cleanUp");
        T obj;
        long now = System.currentTimeMillis();
        
        Enumeration<T> e = available.keys();
        while(e.hasMoreElements()) {        
        	obj = e.nextElement();        
            long startTime = available.get(obj).longValue();
			long usageTime = now - startTime;
			boolean isExpired = usageTime > expirationTime;
			if(isExpired){                     
            	available.remove( obj );
                expire( obj );
                obj = null;
            }
        }
        System.gc();
    }
}

class CleanUpThread extends Thread {

	@SuppressWarnings("rawtypes")
	private ObjectPool3 pool;
	private long sleepTime;

	@SuppressWarnings("rawtypes")
	CleanUpThread( ObjectPool3 pool, long sleepTime ) {
		this.pool = pool;
		this.sleepTime = sleepTime;
	}

	public void run() {
		while( true ) {
			try {
				sleep( sleepTime );
			} catch( InterruptedException e ) {
				// ignore it
			}         
			pool.cleanUp();
		}
	}
}

class DownloadThreadPool extends ObjectPool3<WebClient>{

	public DownloadThreadPool(int threads){
		super();
	}

	@Override
	WebClient create() {
		WebClient client = new WebClient();
		return client;
	}

	@Override
	boolean validate(WebClient obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void expire(WebClient obj) {
		// TODO Auto-generated method stub
	}

	public WebClient borrowClient() { 
		WebClient client = (WebClient) super.checkOut();
		return client;
	}

	public void returnClient(WebClient client) {	
		super.checkIn(client);
	}
}

class WebClient {	
	
	interface WebClientListener {
		public void onComplete();
	}
	public WebClient(){

	}	
	WebClientListener listener;
	public void run(WebClientListener listener){
		this.listener = listener;
		long sleepingTime = 5 + (int) (Math.random() * ((20 - 5) + 1)); 
		try {
			Thread.sleep(sleepingTime * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listener.onComplete();
	}
}

class ObjectPoolTest3 {
	public static void main(String args[]) {
		final DownloadThreadPool pool = new DownloadThreadPool(4);
		
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					final WebClient client = pool.borrowClient();
					client.run(new WebClient.WebClientListener() {
						
						@Override
						public void onComplete() {
							pool.returnClient(client);
						}
					});
				}
			}).start();
		}
	}
}