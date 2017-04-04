/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Deque;

/**
 *
 * @author Madiyar Aitzhanov
 */
public class LinkedListDeque<T> implements Deque<T> {

    private DoublyLinkedNode<T> front;
    private DoublyLinkedNode<T> back;
    
    private int size;
    
    public LinkedListDeque() {
        front = null;
        back = null;
        size = 0;
    }
    
    @Override
    public void pushToFront(T value) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode(value);
        
        if(size == 0) {
            back = node;
        }
        else {
            front.setPrevious(node);
            node.setNext(front);
        }
        
        front = node;
        size ++;
    }

    @Override
    public void pushToBack(T value) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode(value);
        
        if(size == 0) {
            front = node;
        }
        else {
            back.setNext(node);
            node.setPrevious(back);
        }
        
        back = node;
        size ++;
    }

    @Override
    public T popFromFront() throws Exception {
        T res;
        
        if(size == 0) {
            throw new Exception("Deque is empty");
        }
        else if(size == 1) {
            res = front.getValue();
            front = null;
            back = null;
        }
        else {
            res = front.getValue();
            front = front.getNext();
            front.setPrevious(null);
        }
        
        size --;
        return res;
    }

    @Override
    public T popFromBack() throws Exception {
        T res;
        
        if(size == 0) {
            throw new Exception("Deque is empty");
        }
        else if(size == 1) {
            res = back.getValue();
            front = null;
            back = null;
        }
        else {
            res = back.getValue();
            back = back.getPrevious();
            back.setNext(null);
        }
        
        size --;
        return res;
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
        DoublyLinkedNode<T> temp = front;
        while(temp != null) {
            s = s + temp.getValue();
            temp = temp.getNext();
            if(temp != null)
                s = s + ",";
        }
        s = s + "]back";
        return s;
        
    }
    
}
