/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Stack;

/**
 *
 * @author Madiyar Aitzhanov
 * @param <T>
 */
public class ArrayStack<T> implements Stack<T> {

    private T[] values;
    private int size;
    private int maxInt;
    
    public ArrayStack() {
        maxInt = 10;
        values = (T[])new Object[maxInt];
        size = 0;
    }
    
    @Override
    public void push(T value) {
        if(size == maxInt) {
            T a[] = (T[])new Object[size * 2];
            for(int i = 0; i < size; i ++)
                a[i] = values[i];
            values = a;
            maxInt *= 2;
        }
        values[size] = value;
        size ++;
    }

    @Override
    public T pop() throws Exception {
        if(size == 0) {
            throw new Exception("The stack is empty");
        }
        size --;
        T val = values[size];
        values[size] = null;
        return val;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }
    
    @Override
    public String toString() {
        String s = "bottom[";
        for(int i = 0; i < size; i ++) {
            if(i == size - 1)
                s = s + values[i];
            else
                s = s + values[i] + ",";
        }
        s = s + "]top";
        return s;
    }
    
}
