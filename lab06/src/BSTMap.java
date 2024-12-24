import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;
    public BSTMap() {
        root = null;
        size = 0;
    }
    public BSTMap(K key, V value) {
        root = new Node(key, value);
        size = 1;
    }

    private class Node {
        K item;
        Node left;
        Node right;
        V value;
        public Node(K key, V value) {
            right = null;
            left = null;
            item = key;
            this.value = value;
        }

    }

    private Node putHelper(K key, V value, Node S) {
        if (S == null) {
            size += 1;
            return new Node(key, value);
        }
        if (S.item.compareTo(key) > 0) {
            S.left = putHelper(key, value, S.left);
        } else if (S.item.compareTo(key) < 0) {
            S.right = putHelper(key, value, S.right);
        } else {
            S.value = value;
        }
        return S;
    }

    @Override
    public void put(K key, V value) {
        this.root = putHelper(key, value, root);
    }

    @Override
    public V get(K key) {
        return getHelper(key, this.root);
    }
    private V getHelper(K key, Node S) {
        if (S == null) return null;
        if (S.item.compareTo(key) == 0) return S.value;
        else if (S.item.compareTo(key) > 0 && S.left != null) return getHelper(key, S.left);
        else if (S.item.compareTo(key) < 0 && S.right != null) return getHelper(key, S.right);
        else return null;
    }

    private boolean searchHelper(K key, Node S) {
        if (S == null) return false;
        if (S.item.compareTo(key) == 0) return true;
        else if (S.item.compareTo(key) > 0 && S.left != null) return searchHelper(key, S.left);
        else if (S.item.compareTo(key) < 0 && S.right != null) return searchHelper(key, S.right);
        else return false;
    }

    @Override
    public boolean containsKey(K key) {
        return searchHelper(key, this.root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
