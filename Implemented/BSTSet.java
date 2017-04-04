/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Set;

/**
 *
 * @author Madiyar Aitzhanov
 * @param <T>
 */
public class BSTSet<T extends Comparable> implements Set<T> {

    private TreeNode<T> root;
    private int size;
    
    public BSTSet() {
        root = null;
        size = 0;
    }
    
    @Override
    public void add(T value) {
        TreeNode<T> newNode = new TreeNode(value);
        if(size == 0) {
            root = newNode;
            size ++;
            return;
        }
        TreeNode<T> node = root;
        while(true) {
            if(node.getValue().compareTo(value) < 0) {
                if(node.getRight() == null) {
                    node.setRight(newNode);
                    size ++;
                    break;
                }
                node = node.getRight();
            } else if(node.getValue().compareTo(value) > 0) {
                if(node.getLeft() == null) {
                    node.setLeft(newNode);
                    size ++;
                    break;
                }
                node = node.getLeft();
            } else
                break;
        }
    }

    @Override
    public boolean contains(T value) {
        TreeNode<T> node = root;
        while(node != null) {
            if(node.getValue().compareTo(value) < 0) {
                node = node.getRight();
            } else if(node.getValue().compareTo(value) > 0) {
                node = node.getLeft();
            } else
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(T value) {
        TreeNode<T> node = root;
        TreeNode<T> prev = null;
        int side = -1;
        
        while(node != null) {
            if(node.getValue().compareTo(value) == 0) {
                size --;
                if(node.getLeft() == null && node.getRight() == null) {
                    if(side == 1)
                        prev.setRight(null);
                    else if(side == 2)
                        prev.setLeft(null);
                    else
                        root = null;
                }
                else if(node.getRight() != null && node.getLeft() == null) {
                    if(side == 1)
                        prev.setRight(node.getRight());
                    else if(side == 2)
                        prev.setLeft(node.getRight());
                    else {
                        root = node.getRight();
                    }
                }
                else if(node.getRight() == null && node.getLeft() != null) {
                    if(side == 1)
                        prev.setRight(node.getLeft());
                    else if(side == 2)
                        prev.setLeft(node.getLeft());
                    else {
                        root = node.getLeft();
                    }
                }
                else 
                    removeHelper(node, side);
                return true;
            }
            if(node.getValue().compareTo(value) < 0) {
                prev = node;
                node = node.getRight();
                side = 1;
            } else if(node.getValue().compareTo(value) > 0) {
                prev = node;
                node = node.getLeft();
                side = 2;
            }
        }
        return false;
    }
    
    void removeHelper(TreeNode<T> next, int side) {
        TreeNode<T> temp = next.getRight();
        if(temp.getLeft() == null) {
            next.setValue(temp.getValue());
            next.setRight(temp.getRight());
            return;
        }
        while(temp.getLeft().getLeft() != null)
            temp = temp.getLeft();
        next.setValue(temp.getLeft().getValue());
        temp.setLeft(temp.getLeft().getRight());
    }

    @Override
    public T removeAny() throws Exception {
        if(size == 0)
            throw new Exception("Set is empty");
        T res = root.getValue();
        remove(res);
        return res;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    
    @Override
    public String toString() {
        return toStringHelper(root);
    }
    
    private String toStringHelper(TreeNode<T> node) {
        if(node == null)
            return "";
        return toStringHelper(node.getLeft()) +
                node.getValue() + " " +
                toStringHelper(node.getRight());
    }

}
