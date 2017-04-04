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
 */
public class LinkedListStack<T> implements Stack<T> {
    
    private Node<T> top;
    private int size;
    
    public LinkedListStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(T value) {
        
        Node<T> node = new Node(value);
        node.setLink(top);
        top = node;
        size ++;
        
    }
    
    @Override
    public T pop() throws Exception {
      
        if(size == 0)
            throw new Exception("The stack is empty");
        size --;
        
	T val = top.getValue();
        top = top.getLink();
        return val;
        
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }
    
    public String toString() {
        
        String s = "top[";
        
        Node<T> temp = top;
        while(temp != null) {
            s = s + temp.getValue();
            temp = temp.getLink();
            if(temp != null)
                s = s + ",";
        }
        s = s + "]bottom";
        
        return s;
    }
    
}
