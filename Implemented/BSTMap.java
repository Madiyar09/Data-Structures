/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.KeyValuePair;
import csci152.adt.Map;

/**
 * @param <K>
 * @param <V>
 * @author Madiyar Aitzhanov
 */
public class BSTMap<K extends Comparable, V> implements Map<K, V> {
 
	private TreeNode<KeyValuePair<K,V>> root;
	private int size;
	
	public BSTMap() {
            root = null;
            size = 0;
	}
	
	@Override
	public void define(K key, V value) {
            TreeNode<KeyValuePair<K,V>> newNode = new TreeNode(new KeyValuePair(key, value));
            if(size == 0) {
                root = newNode;
                size ++;
                return;
            }
            TreeNode<KeyValuePair<K,V>> node = root;
            while(true) {
                if(node.getValue().getKey().compareTo(key) < 0) {
                    if(node.getRight() == null) {
                        node.setRight(newNode);
                        size ++;
                        break;
                    }
                    node = node.getRight();
                } else if(node.getValue().getKey().compareTo(key) > 0) {
                    if(node.getLeft() == null) {
                        node.setLeft(newNode);
                        size ++;
                        break;
                    }
                    node = node.getLeft();
                } else {
                    node.setValue(newNode.getValue());
                    break;
                }
            }
	}
 
	@Override
	public V getValue(K key) {
            TreeNode<KeyValuePair<K,V>> node = root;
            while(node != null) {
                if(node.getValue().getKey().compareTo(key) < 0) {
                    node = node.getRight();
                } else if(node.getValue().getKey().compareTo(key) > 0) {
                    node = node.getLeft();
                } else
                    return node.getValue().getValue();
            }
            return null;
	}
 
	@Override
	public V remove(K key) {
            TreeNode<KeyValuePair<K,V>> node = root;
            TreeNode<KeyValuePair<K,V>> prev = null;
            int side = -1;

            while(node != null) {
                if(node.getValue().getKey().compareTo(key) == 0) {
                    V returnValue = node.getValue().getValue();
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
                        else 
                            root = node.getRight();
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
                        removeHelper(node);
                    return returnValue;
                }
                if(node.getValue().getKey().compareTo(key) < 0) {
                    prev = node;
                    node = node.getRight();
                    side = 1;
                } else if(node.getValue().getKey().compareTo(key) > 0) {
                    prev = node;
                    node = node.getLeft();
                    side = 2;
                }
            }
            return null;
	}
        
        void removeHelper(TreeNode<KeyValuePair<K,V>> next) {
            TreeNode<KeyValuePair<K,V>> temp = next.getRight();
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
	public KeyValuePair<K, V> removeAny() throws Exception {
            if(size == 0)
                throw new Exception("Map is empty");
            KeyValuePair<K, V> res = root.getValue();
            remove(res.getKey());
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
    
    private String toStringHelper(TreeNode<KeyValuePair<K, V>> node) {
        if(node == null)
            return "";
        return toStringHelper(node.getLeft()) +
                node.getValue() + " " +
                toStringHelper(node.getRight());
    }
}
