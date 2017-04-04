/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.HashTableSet;

/**
 * @param <T>
 * @author Madiyar Aitzhanov
 */
public class LLQHashTableSet<T> implements HashTableSet<T> {

    private LinkedListQueue<T>[] buckets;
    private int size;
    
    public LLQHashTableSet(int numBuckets) {
        buckets = new LinkedListQueue[numBuckets];
        size = 0;
    }
    
    @Override
    public void add(T value) {
        if(contains(value))
            return;
        int id = Math.abs(value.hashCode()) % buckets.length;
        if(buckets[id] == null)
            buckets[id] = new LinkedListQueue();
        buckets[id].enqueue(value);
        size ++;
    }

    @Override
    public boolean contains(T value) {
        int id = Math.abs(value.hashCode()) % buckets.length;
        if(buckets[id] == null)
            return false;
        int sz = buckets[id].getSize();
        for(int i = 0; i < sz; i ++) {
            try {
                T val = buckets[id].dequeue();
                buckets[id].enqueue(val);
                if(val == value)
                    return true;
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean remove(T value) {
        int id = Math.abs(value.hashCode()) % buckets.length;
        if(buckets[id] == null)
            return false;
        int sz = buckets[id].getSize();
        for(int i = 0; i < sz; i ++) {
            try {
                T val = buckets[id].dequeue();
                if(val == value) {
                    size --;
                    return true;
                }
                buckets[id].enqueue(val);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public T removeAny() throws Exception {
        if(size == 0)
            throw new Exception ("Set is empty.");
        T val = (T) new Object();
        for(int id = 0; id < buckets.length; id ++) {
            if(buckets[id] != null && buckets[id].getSize() > 0) {
                try {
                    val = buckets[id].dequeue();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
        }
        size --;
        return val;
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
        int cnt = 0;
        for(int id = 0; id < buckets.length; id ++) {
            if(buckets[id] != null) {
                for(int i = 0; i < buckets[id].getSize(); i ++) {
                    T val = (T) new Object();
                    try {
                        val = buckets[id].dequeue();
                        buckets[id].enqueue(val);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    if(cnt + 1 != size)
                        s += val + ",";
                    else
                        s += val;
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
