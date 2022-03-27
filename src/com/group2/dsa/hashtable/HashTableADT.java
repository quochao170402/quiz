package com.group2.dsa.hashtable;

import com.group2.dsa.linkedlist.DoublyLinkedList;

public interface HashTableADT<K, V> extends Iterable<K> {
    // O(1)
    int size();

    // O(1)
    boolean isEmpty();

    // O(1)
    int hashFunction(int hashedKey);

    // O(n)
    void clear();

    // O(n)
    boolean has(K key);

    // O(n)
    V insert(K key, V value);

    // O(n)
    V get(K key);

    // O(n)
    V remove(K key);

    // O(n)
    DoublyLinkedList<V> values();
}
