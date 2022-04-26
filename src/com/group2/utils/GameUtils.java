package com.group2.utils;

import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;
import com.group2.dsa.linkedlist.Node;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;

public class GameUtils{
    public static final DoublyLinkedList<Category> categorys = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Question> questions = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Player> players = new DoublyLinkedListImpl<>();

    // O(n)
    public  Node<Player> getMiddle(Node<Player> headPlayer) {
        
        if(headPlayer == null) {
            return headPlayer;
        }

        Node<Player> slow = headPlayer;
        Node<Player> fast = headPlayer.getNext();
        
        while(fast.getNext() != null){
            fast = fast.getNext();
            if (fast.getNext() != null) {
                fast = fast.getNext();
                slow = slow.getNext();
            }
        }

        return slow;
    }
    
    public  Node<Player> mergeSort(Node<Player> left, Node<Player> right) {

        Node<Player> result = null;
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        Player temp1 = left.getData();
        Player temp2 = right.getData();
    
        if(temp1.getScore() > temp2.getScore()) {
            
            result = left;
            result.setNext(mergeSort(left.getNext(), right));
            result.getNext().setPrev(result);;

            
        }
        else if (temp1.getScore() == temp2.getScore()) {
            if(temp1.getTime() < temp2.getTime()) {
                result = left;
                result.setNext(mergeSort(left.getNext(), right));
                result.getNext().setPrev(result);;

            }
            else {
                result = right;
                result.setNext(mergeSort(left, right.getNext()));
                result.getNext().setPrev(result);;

            }
        }
        else {
            result = right;
            result.setNext(mergeSort(left, right.getNext()));
            result.getNext().setPrev(result);;

        }


        return result;
    }

    public  Node<Player> mergeSort(Node<Player> headNode) {
        // if have 0 or 1 player in list -> return
        if(headNode == null ||headNode.getNext() == null) {
            return headNode;
        }


        Node<Player> middle = getMiddle(headNode);

        Node<Player> nextofmiddle = middle.getNext();
 
        // set the next of middle node to null
        middle.setNext(null);

        Node<Player> left =  mergeSort(headNode);
        Node<Player> right = mergeSort(nextofmiddle);


        Node<Player> sortedlist = mergeSort(left, right);
        return sortedlist;
    }
    
    
    public  void showCharts() {
        DoublyLinkedList<Player> listPlayer = players;
        Node<Player> headNode = listPlayer.getHead();
        Node<Player> newHeadNode = mergeSort(headNode);
        while(newHeadNode != null) {
            System.out.print(newHeadNode.getData() + "\n");
            newHeadNode = newHeadNode.getNext();
        }
    }
}