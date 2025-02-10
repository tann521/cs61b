package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = Math.floorMod(hash, bucketSize);
        if (getNode(key, index) != null && getNode(key, index).key.equals(key) && !getNode(key, index).value.equals(value)) {
            getNode(key, index).value = value;
            return;
        } else if (getNode(key, index) != null && getNode(key, index).key.equals(key) && getNode(key, index).value.equals(value)) return;
        buckets[index].add(new Node(key, value));
        count += 1;
        this.resize();
    }

    private void resize() {
        if ((double) this.count / this.bucketSize >= this.loadFactor) {
            Collection<Node>[] newBucket = createTable(2 * bucketSize);
            for (int i = 0; i < bucketSize; i++) {
                for (Node node : buckets[i]) {
                    int index = Math.floorMod(node.key.hashCode(), 2 * bucketSize);
                    newBucket[index].add(new Node(node.key, node.value));
                }
            }
            bucketSize *= 2;
            buckets = newBucket;
        }
    }

    private Node getNode(K key, int index) {
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) return node;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index =Math.floorMod(key.hashCode(), bucketSize);
        for (Node node: buckets[index]) {
            if (node.key.equals(key)) return node.value;
        } return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index =Math.floorMod(key.hashCode(), bucketSize);
        for (Node node: buckets[index]) {
            if (node.key.equals(key)) return true;
        } return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        buckets = createTable(bucketSize);
        count = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new  UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        count -= 1;
        int index = Math.floorMod(key.hashCode() , bucketSize);
        for (Node node: buckets[index]) {
            if (node.key.equals(key)) {
                V value = node.value;
                buckets[index].remove(node);
                return value;
            }
        } return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    private int initialCapacity;
    private double loadFactor;
    public int count = 0;
    private int bucketSize;


    /** Constructors */
    public MyHashMap() {
        initialCapacity = 16;
        loadFactor = 0.75;
        bucketSize = 16;
        buckets = createTable(initialCapacity);
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = 0.75;
        bucketSize = initialCapacity;
        buckets = createTable(initialCapacity);
    }


    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.initialCapacity = initialCapacity;
        bucketSize = initialCapacity;
        buckets = createTable(initialCapacity);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
