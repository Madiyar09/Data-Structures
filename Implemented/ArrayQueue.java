/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Queue;

/**
 *
 * @author Madiyar Aitzhanov
 * @param <T>
 */
public class ArrayQueue<T> implements Queue<T> {

    private T[] values;
    private int size;
    private int front;
    private int back;
    private int intMax;
    
    public ArrayQueue() {
        intMax = 5;
        values = (T[])new Object[intMax];
        size = 0;
        front = 0;
        back = 0;
    }
    
    @Override
    public void enqueue(T value) {
        
        if(size == intMax) {
            T a[] = (T[])new Object[intMax * 2];
            int pos = 0;
            for(int i = front; i < intMax; i ++)
                a[pos ++] = values[i];
            for(int i = 0; i < back; i ++)
                a[pos ++] = values[i];
            values = a;
            intMax *= 2;
            front = 0;
            back = pos;
        }
        values[back] = value;
        back = (back + 1) % intMax;
        size ++;
        
    }

    @Override
    public T dequeue() throws Exception {
    
        if(size == 0)
            throw new Exception("No elements in the queue");
            
        T res = values[front];
        values[front] = null;
        front = (front + 1) % intMax;
        size --;
        return res;
        
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = 0;
        back = 0;
        size = 0;
    }
    
    @Override
    public String toString() {
        String s = "front[";
        int pos = front;
        
        for(int i = 0; i < size; i ++) {
            s = s + values[pos];
            if(i != size - 1)
                s = s + ",";
            pos = (pos + 1) % intMax;
        }
        s = s + "]back";
        return s;
    }
    
}
