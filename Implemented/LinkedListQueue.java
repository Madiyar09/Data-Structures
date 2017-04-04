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
 */
public class LinkedListQueue<T> implements Queue<T> {
    
    private Node<T> front;
    private Node<T> back;
    
    private int size;
    
    public LinkedListQueue() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public void enqueue(T value) {
        
        Node<T> node = new Node(value);
        if(size != 0)
            back.setLink(node);
        else
            front = node;
        back = node;
        size ++;
        
    }

    @Override
    public T dequeue() throws Exception {
        
        if(size == 0)
            throw new Exception("Queue is empty");
        T value = front.getValue();
        if(size == 1) {
            front = null;
            back = null;
        }
        else
            front = front.getLink();
        
        size --;
        
        return value;
        
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }
    
    public String toString() {
        
        String s = "front[";
        Node<T> temp = front;
        while(temp != null) {
            s = s + temp.getValue();
            temp = temp.getLink();
            if(temp != null)
                s = s + ",";
        }
        s = s + "]back";
        return s;
        
    }
    
}
