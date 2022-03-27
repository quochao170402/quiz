package com.group2.dsa.hashtable;

import com.group2.dsa.linkedlist.DoublyLinkedList;

public interface HashTableADT<K, V> extends Iterable<K> {
    // O(1)
    int size();

    // O(1)
    boolean isEmpty();

    // O(1)
    int hashFunction(int hashedKey);

    // O(n) : n = size of table
    void clear();

    // 
    boolean has(K key);

    V insert(K key, V value);

    V get(K key);

    V remove(K key);

    DoublyLinkedList<Node<K,V>> values();
}
