package com.group2.game;

import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;
import com.group2.dsa.linkedlist.Node;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;

import java.util.Random;
import java.util.UUID;

public class Demo {
    
    public static final DoublyLinkedList<Category> categorys = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Question> questions = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Player> players = new DoublyLinkedListImpl<>();
    public static Node<Player> getMiddle(Node<Player> headPlayer) {
        
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
    public static Node<Player> getMiddle2(Node<Player> headPlayer) {
        
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
    public static Node<Player> sortedMerge(Node<Player> left, Node<Player> right) {

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
            result.setNext(sortedMerge(left.getNext(), right));
            
        }
        else if (temp1.getScore() == temp2.getScore()) {
            if(temp1.getTime() < temp2.getTime()) {
                result = left;
                result.setNext(sortedMerge(left.getNext(), right));
            }
            else {
                result = right;
                result.setNext(sortedMerge(left, right.getNext()));
            }
        }
        else {
            result = right;
            result.setNext(sortedMerge(left, right.getNext()));
        }


        return result;
    }

    public static Node<Player> mergeSort(Node<Player> headNode) {
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


        Node<Player> sortedlist = sortedMerge(left, right);
        return sortedlist;
    }
    
    
    public static void showCharts() {
        DoublyLinkedList<Player> listPlayer = players;
        Node<Player> headNode = listPlayer.getHead();
        Node<Player> newHeadNode = mergeSort(headNode);
        while(newHeadNode != null) {
            System.out.print(newHeadNode.getData() + "\n");
            newHeadNode = newHeadNode.getNext();
        }
    }
    public static void main(String[] args) {
        initData();
        System.out.println(categorys);
        System.out.println(questions);
        System.out.println(players);
        showCharts();
    }

    private static void initData() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            categorys.add(new Category(UUID.randomUUID().toString()));
            questions.add(new Question(random.nextInt(10), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                    random.nextInt(4) + 1));
            players.add(new Player(UUID.randomUUID().toString(), random.nextInt(10) * 10,
                    Long.parseLong(String.valueOf(random.nextInt(100000)))));
        }
    }
}
