/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Queue;
import csci152.adt.Set;

/**
 * @param <T>
 * @author Madiyar Aitzhanov
 */
public class LLQueueSet<T> implements Set<T> {

    Queue <T> queue = new LinkedListQueue();
    
    @Override
    public void add(T value) {
        
        for(int i = 0; i < queue.getSize(); i ++) {
            try {
                T x = queue.dequeue();
                queue.enqueue(x);
                
                if(x.equals(value))
                    return;
                
            } catch(Exception ex) {
                //won't happen
            }
        }
        
        queue.enqueue(value);
    }

    @Override
    public boolean contains(T value) {
        
        for(int i = 0; i < queue.getSize(); i ++) {
            try {
                T x = queue.dequeue();
                queue.enqueue(x);
                
                if(x.equals(value))
                    return true;
                
            } catch(Exception ex) {
                //won't happen
            }
        }
        
        return false;
    }

    @Override
    public boolean remove(T value) {
        
        for(int i = 0; i < queue.getSize(); i ++) {
            try {
                T x = queue.dequeue();
                if(x.equals(value))
                    return true;
                queue.enqueue(x);
            } catch(Exception ex) {
                //won't happen
            }
        }
        
        return false;
    }

    @Override
    public T removeAny() throws Exception {
        
        if(queue.getSize() == 0)
            throw new Exception("Set is empty");
        
        T x = queue.dequeue();
        return x;
        
    }

    @Override
    public int getSize() {
        return queue.getSize();
    }

    @Override
    public void clear() {
        queue.clear();
    }
    
    public String toString() {
        return queue.toString();
    }
    
}
