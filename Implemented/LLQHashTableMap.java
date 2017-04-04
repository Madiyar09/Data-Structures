/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.KeyValuePair;
import csci152.adt.HashTableMap;

/**
 * @param <K>
 * @param <V>
 * @author Madiyar Aitzhanov
 */
public class LLQHashTableMap<K, V> implements HashTableMap<K, V> {

    private LinkedListQueue<KeyValuePair<K, V>>[] buckets;
    private int size;
    
    public LLQHashTableMap(int numBuckets) {
        buckets = new LinkedListQueue[numBuckets];
        size = 0;
    }
    
    @Override
    public void define(K key, V value) {
        remove(key);
        int id = Math.abs(key.hashCode()) % buckets.length;
        if(buckets[id] == null)
            buckets[id] = new LinkedListQueue();
        buckets[id].enqueue(new KeyValuePair(key, value));
        size ++;
    }

    @Override
    public V getValue(K key) {
        int id = Math.abs(key.hashCode()) % buckets.length;
        if(buckets[id] == null)
            return null;
        int sz = buckets[id].getSize();
        for(int i = 0; i < sz; i ++) {
            try {
                KeyValuePair<K, V> obj = buckets[id].dequeue();
                buckets[id].enqueue(obj);
                if(obj.getKey().equals(key))
                    return obj.getValue();
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int id = Math.abs(key.hashCode()) % buckets.length;
        if(buckets[id] == null) 
            return null;
        int sz = buckets[id].getSize();
        for(int i = 0; i < sz; i ++) {
            try {
                KeyValuePair<K, V> obj = buckets[id].dequeue();
                if(obj.getKey().equals(key)) {
                    size --;
                    return obj.getValue();
                }
                buckets[id].enqueue(obj);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if(size == 0)
            throw new Exception ("Map is empty");
        for(int id = 0; id < buckets.length; id ++) {
            if(buckets[id] != null && buckets[id].getSize() > 0) {
                try {
                    KeyValuePair<K, V> obj = buckets[id].dequeue();
                    size --;
                    return obj;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        int sz = buckets.length;
        buckets = new LinkedListQueue[sz];
        size = 0;
    }
    
    @Override
    public String toString() {
        String s = new String();
        for(int id = 0; id < buckets.length; id ++) {
            if(buckets[id] != null) {
                for(int i = 0; i < buckets[id].getSize(); i ++) {
                    try {
                        KeyValuePair<K, V> obj = buckets[id].dequeue();
                        buckets[id].enqueue(obj);
                        s += obj + " ";
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
        return s;
    }

    @Override
    public int getNumberOfBuckets() {
        return buckets.length;
    }

    @Override
    public int getBucketSize(int index) throws Exception {
        if(index < 0 || index >= buckets.length)
            throw new Exception("Incorrect index.");
        if(buckets[index] == null)
            return 0;
        return buckets[index].getSize();
    }

    @Override
    public double getLoadFactor() {
        return (double)size / (double)buckets.length;
    }

    @Override
    public double getBucketSizeStandardDev() {
        double ave = 0.0;
        for(int id = 0; id < buckets.length; id ++) 
            if(buckets[id] != null)
                ave += buckets[id].getSize();
        ave = ave / (double)buckets.length;
        double res = 0.0;
        for(int id = 0; id < buckets.length; id ++) 
            if(buckets[id] != null)
                res += (ave - buckets[id].getSize()) * (ave - buckets[id].getSize());
            else
                res += ave * ave;
        return Math.sqrt(res / buckets.length);
    }

    @Override
    public String bucketsToString() {
        String s = new String();
        for(int id = 0; id < buckets.length; id ++) {
            if(buckets[id] != null)
                s += buckets[id] + "\n";
        }
        return s;
    }
}

