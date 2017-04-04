/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

/**
 *
 * @author Madiyar Aitzhanov
 */
public class Node<T> {
    
    private T value;
    private Node<T> link;
    
    /**
     * Construct a node with the given value, and a null link
     * @param val to set on the node
     */
    public Node(T val) {
        value = val;
        link = null;
    }
    
    /**
     * Returns the node's value
     * @return value of node
     */
    public T getValue() {
        return value;
    }
    
    /**
     * Sets the node's value to the given value
     * @param val to set on the node
     */
    public void setValue(T val) {
        value = val;
    }
    
    /**
     * Returns the link of the node
     * @return link of the node
     */
    public Node<T> getLink() {
        return link;
    }
    
    /**
     * Sets node's link to the given node
     * @param link to set on the node
     */
    public void setLink(Node<T> link) {
        this.link = link;
    }
    
    /**
     * 
     * @return a string representation of the node
     */
    public String toString() {
        return value.toString();
    }
    
}
