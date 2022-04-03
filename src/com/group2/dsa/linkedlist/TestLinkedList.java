package com.group2.dsa.linkedlist;

public class TestLinkedList {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list1 = new DoublyLinkedListImpl<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        DoublyLinkedList<Integer> list2 = new DoublyLinkedListImpl<>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        list2.add(40);
        list2.add(50);
        DoublyLinkedList<Integer> list3 = new DoublyLinkedListImpl<>();
        list3.add(100);
        list3.add(200);
        list3.add(300);
        list3.add(400);
        list3.add(500);
        list1.addAll(list2);
        list3.addAll(list1);

        System.out.println(list3);
    }
}
