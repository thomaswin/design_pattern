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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

abstract class ObjectPool { 

	private long expirationTime;   
	private Hashtable<Object, Long> locked, unlocked;

	abstract Object create();
	abstract boolean validate( Object o );

	abstract void expire(Object o);

	synchronized Object checkOut(){
		long now = System.currentTimeMillis();
		Object o;        
		if( unlocked.size() > 0 ) {

			Enumeration<Object> e = unlocked.keys();  
			while(e.hasMoreElements()) {

				o = e.nextElement();           

				if((now - ((Long)unlocked.get(o)).longValue()) > expirationTime) {

					// object has expired
					unlocked.remove( o );
					expire( o );
					o = null;
				} else {
					if( validate(o)) {
						unlocked.remove(o);
						locked.put(o, new Long(now));                
						return(o);
					} else {
						// object failed validation
						unlocked.remove( o );
						expire( o );
						o = null;
					}
				}
			}
		}        
		// no objects available, create a new one
		o = create();        
		locked.put( o, new Long( now ) ); 
		return( o );
	}
	synchronized void checkIn( Object o ){
		locked.remove( o );
		unlocked.put( o, new Long( System.currentTimeMillis() ) );
	}

	ObjectPool() {
		expirationTime = 30000; // 30 seconds
		locked = new Hashtable<Object, Long>();         
		unlocked = new Hashtable<Object, Long>();
	}
}

class JDBCConnectionPool extends ObjectPool {

	private String dsn, usr, pwd;
	public JDBCConnectionPool( String driver, String dsn, String usr, String pwd) {

		try {
			Class.forName( driver ).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dsn = dsn;
		this.usr = usr;
		this.pwd = pwd;
	}

	public Connection borrowConnection() {
		return( ( Connection ) super.checkOut() );
	}
	public void returnConnection( Connection c ) {
		super.checkIn( c );
	}
	@Override
	Object create() {
		try {

			return( DriverManager.getConnection( dsn, usr, pwd ) );
		} catch( SQLException e ) {

			e.printStackTrace();
			return( null );
		}
	}
	@Override
	boolean validate(Object o) {
		try {

			return( ! ( ( Connection ) o ).isClosed() );
		} catch(SQLException e) {
			e.printStackTrace();
			return( false );
		}
	}
	@Override
	void expire(Object o) {
		try {
			( ( Connection ) o ).close();
		} catch( SQLException e) {
			e.printStackTrace();
		}
	}
}
