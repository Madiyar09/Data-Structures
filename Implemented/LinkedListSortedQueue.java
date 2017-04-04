/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.SortedQueue;

/**
 *
 * @author Madiyar Aitzhanov
 */
public class LinkedListSortedQueue<T extends Comparable> 
                                    implements SortedQueue<T> {

    private Node<T> front;
    
    private int size;
    
    public LinkedListSortedQueue() {
        front = null;
        size = 0;
    }

    @Override
    public void insert(T value) {
        
        Node<T> node = new Node(value);
        
        if(size == 0)
            front = node;
        else {
            //node goes before front
            if(value.compareTo(front.getValue()) <= 0) {
                node.setLink(front);
                front = node;
            } 
            else {
                //node goes somewhere in the middle
                Node<T> cur = front;
                while(cur != null) {
                    if(cur.getLink() == null) {
                        cur.setLink(node);
                        break;
                    }
                    if(value.compareTo(cur.getLink().getValue()) <= 0) {
                        node.setLink(cur.getLink());
                        cur.setLink(node);
                        break;
                    }
                    cur = cur.getLink();
                }
            }
        }
        
        size ++;
       
    }

    @Override
    public T dequeue() throws Exception {
        
        if(size == 0)
            throw new Exception("Queue is empty");
        
        T value = front.getValue();
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
