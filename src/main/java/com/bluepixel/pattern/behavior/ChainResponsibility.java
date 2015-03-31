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

abstract class Logger {
	
    public static int ERR       = 3;
    public static int NOTICE    = 5;
    public static int DEBUG     = 7;
    protected int mask;
 
    // The next element in the chain of responsibility
    protected Logger next;
 
    abstract protected void writeMessage(String msg);
    
    public Logger setNext(Logger log) {
        next = log;
        return log;
    }
 
    public void message(String msg, int priority) {
        if (priority <= mask) {
            writeMessage(msg);
        }
        if (next != null) {
            next.message(msg, priority);
        }
    }
    
}


class StdoutLogger extends Logger {
    public StdoutLogger(int mask) { 
        this.mask = mask;
    }
 
    protected void writeMessage(String msg) {
        System.out.println("Writing to stdout: " + msg);
    }
}


 
class EmailLogger extends Logger {
    public EmailLogger(int mask) {
        this.mask = mask;
    }
 
    protected void writeMessage(String msg) {
        System.out.println("Sending via email: " + msg);
    }
}
 

class StderrLogger extends Logger {
    public StderrLogger(int mask) {
        this.mask = mask;
    }
 
    protected void writeMessage(String msg) {
        System.err.println("Sending to stderr: " + msg);
    }
}


public class ChainResponsibility {
    public static void main(String[] args) {
        // Build the chain of responsibility
        Logger logger, logger1;
        logger1 = logger = new StdoutLogger(Logger.DEBUG);
        logger1 = logger1.setNext(new EmailLogger(Logger.NOTICE));
        logger1 = logger1.setNext(new StderrLogger(Logger.ERR));
 
        // Handled by StdoutLogger
        logger.message("Entering function y.", Logger.DEBUG);
 
        // Handled by StdoutLogger and EmailLogger
        logger.message("Step1 completed.", Logger.NOTICE);
 
        // Handled by all three loggers
        logger.message("An error has occurred.", Logger.ERR);
    }
}
