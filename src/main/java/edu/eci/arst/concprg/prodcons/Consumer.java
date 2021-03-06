/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{
    
    private Queue<Integer> queue;
    
    
    public Consumer(Queue<Integer> queue){
        this.queue=queue;        
    }
    
    @Override
    public void run() {
        while (true) {
            if (queue.size() > 0) {
            	int elem=0;
            	synchronized(queue) {
	                elem=queue.poll();
	                queue.notifyAll();}
            	System.out.println("Consumer consumes "+elem); 
            }
            else {
            	synchronized(queue) {
            		try {
            			Thread.sleep(3000);
                    	queue.wait();
                    } catch(InterruptedException e) {
                    	e.printStackTrace();
                    }
            	}
            }
              
    }
    }
}

