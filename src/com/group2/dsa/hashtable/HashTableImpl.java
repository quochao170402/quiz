package com.group2.dsa.hashtable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;

public class HashTableImpl<K, V> implements HashTableADT<K, V> {
    // Default size of table
    private static final int DEFAULT_CAPACITY = 10;
    // Size of hash table
    private int size = 0;
    // Size of table
    private int capacity;

    private DoublyLinkedList<Node<K, V>>[] table;

    public HashTableImpl() {
        this(DEFAULT_CAPACITY);
    }

    public HashTableImpl(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity can not negative");

        this.capacity = capacity;
        table = new DoublyLinkedListImpl[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0; 
    }

    // 0xFFFFFFFFFL is a bit operator to change a negative number to positive number
    @Override
    public int hashFunction(int hashedKey) {
        return (int) ((hashedKey & 0xFFFFFFFFFL) % capacity);
    }

    // Clear all doubly linked list
    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i].clear();
        }
        size = 0;

        //O(n)
    }

    // Check table constain key
    @Override
    public boolean has(K key) {
        int index = hashFunction(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index]; 

        if (linkedList == null || linkedList.isEmpty())
            return false;

        for (Node<K, V> node : linkedList) {
            if (node.getKey().equals(key))
                return true;
        }

        return false;
    } // O(n)

    @Override
    public V insert(K key, V value) {
        int index = hashFunction(key.hashCode()); 
        DoublyLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null) {
            // Key does not exist in the table => bucket of the table at index is null
            table[index] = linkedList = new DoublyLinkedListImpl<>();
            return addNodeToBucket(key, value, linkedList);

        } else if (linkedList.isEmpty()) {
            // Key existed in the table but bucket is empty
            return addNodeToBucket(key, value, linkedList);

        } else {
            // Key existed and bucket at index have nodes
            for (Node<K, V> node : linkedList) {

                // Node existed in bucket to return existed node
                if (node.getKey().equals(key)) {
                    V oldValue = node.getValue();
                    node.setValue(value);
                    return oldValue;
                }
            }
            // Add node to buckect
            return addNodeToBucket(key, value, linkedList);
        }
    }

    // Add new node to a bucket in table (Bucket is a linkedlist at index by
    // hashFunction returned)
    private V addNodeToBucket(K key, V value, DoublyLinkedList<Node<K, V>> linkedList) {
        linkedList.add(new Node<>(key, value));
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        int index = hashFunction(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null || linkedList.isEmpty())
            return null;

        for (Node<K, V> node : linkedList) {
            if (node.getKey().equals(key))
                return node.getValue();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashFunction(key.hashCode());
        DoublyLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null || linkedList.isEmpty())
            return null;

        for (Node<K, V> node : linkedList) {
            if (node.getKey().equals(key)) {
                linkedList.remove(node);
                --size;
                return node.getValue();
            }
        }
        
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        final int elementCount = size();

        return new Iterator<K>() {
            int index = 0;
            Iterator<Node<K, V>> bucketIterator = table[0] == null ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                if (elementCount != size())
                    throw new ConcurrentModificationException("Table bi doi mat tieu luonnnnnn!");

                if (bucketIterator == null || !bucketIterator.hasNext()) {
                    while (++index < capacity) {
                        if (table[index] != null || !table[index].isEmpty()) {
                            bucketIterator = table[index].iterator();
                            break;
                        }
                    }
                }
                return index < capacity;
            }

            @Override
            public K next() {
                return bucketIterator.next().getKey();
            }
        };
    }

    @Override
    public DoublyLinkedList<V> values() {
        DoublyLinkedList<V> allValues = new DoublyLinkedListImpl<>();

        for (int i = 0; i < table.length; i++) {
            DoublyLinkedList<Node<K, V>> linkedList = table[i];

            if (linkedList != null && !linkedList.isEmpty()) {
                for (Node<K, V> node : linkedList) {
                    allValues.add(node.getValue());
                }
                // allValues.addAll(linkedList);
            }
        }
        return allValues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hash Table :{\n");
        for (int i = 0; i < table.length; i++) {
            sb.append("Table[" + i + "]:\t");
            DoublyLinkedList<Node<K, V>> linkedList = table[i];
            if (linkedList == null || linkedList.isEmpty()) {
                sb.append("\n");
                continue;
            }

            for (Node<K, V> node : linkedList) {
                sb.append(node.toString()).append(", ");
            }
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

}
