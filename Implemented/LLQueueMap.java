/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.KeyValuePair;
import csci152.adt.Map;

/**
 *
 * @author Madiyar Aitzhanov
 */
public class LLQueueMap<K, V> implements Map<K, V> {

    private LinkedListQueue<KeyValuePair<K, V>> pairs;
    
    public LLQueueMap() {
        pairs = new LinkedListQueue();
    }
    
    @Override
    public void define(K key, V value) {
        KeyValuePair <K, V> newx = new KeyValuePair(key, value);
        boolean added = false;
        
        for(int i = 0; i < pairs.getSize(); i ++) {
            try {
                KeyValuePair<K, V> temp = pairs.dequeue();
                if(temp.getKey() == key) {
                    added = true; pairs.enqueue(newx);
                }
                else
                    pairs.enqueue(temp);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        if(!added)
            pairs.enqueue(newx);
    }

    @Override
    public V getValue(K key) {
        
        for(int i = 0; i < pairs.getSize(); i ++) {
            try {
                KeyValuePair<K, V> temp = pairs.dequeue();
                pairs.enqueue(temp);
                if(temp.getKey() == key) 
                    return temp.getValue();
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return null;
    }

    @Override
    public V remove(K key) {
        
        for(int i = 0; i < pairs.getSize(); i ++) {
            try {
                KeyValuePair<K, V> temp = pairs.dequeue();
                if(temp.getKey() == key) 
                    return temp.getValue();
                pairs.enqueue(temp);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if(pairs.getSize() == 0)
            throw new Exception("Map is empty");
        return pairs.dequeue();
    }

    @Override
    public int getSize() {
        return pairs.getSize();
    }

    @Override
    public void clear() {
        pairs = new LinkedListQueue();
    }
    
    @Override
    public String toString() {
        String s = new String();
        for(int i = 0; i < pairs.getSize(); i ++) {
            try {
                KeyValuePair<K, V> temp = pairs.dequeue();
                s = s + temp.toString() + " ";
                pairs.enqueue(temp);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return s;
    }
    
}
