package com.group2.utils;

import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;
import com.group2.dsa.linkedlist.Node;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;

public class Sorter {
    public static final DoublyLinkedList<Category> categorys = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Question> questions = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Player> players = new DoublyLinkedListImpl<>();

    // O(n)
    public Node<Player> getMiddle(Node<Player> headPlayer) {

        if (headPlayer == null) {
            return headPlayer;
        }

        Node<Player> slow = headPlayer;
        Node<Player> fast = headPlayer.getNext();

        while (fast.getNext() != null) {
            fast = fast.getNext();
            if (fast.getNext() != null) {
                fast = fast.getNext();
                slow = slow.getNext();
            }
        }

        return slow;
    }

    public Node<Player> merge(Node<Player> left, Node<Player> right) {

        Node<Player> result = null;
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        Player temp1 = left.getData();
        Player temp2 = right.getData();

        if (temp1.getScore() > temp2.getScore()) {

            result = left;
            result.setNext(merge(left.getNext(), right));
            result.getNext().setPrev(result);
            ;

        } else if (temp1.getScore() == temp2.getScore()) {
            if (temp1.getTime() < temp2.getTime()) {
                result = left;
                result.setNext(merge(left.getNext(), right));
                result.getNext().setPrev(result);
                ;
            } else {
                result = right;
                result.setNext(merge(left, right.getNext()));
                result.getNext().setPrev(result);
                ;
            }
        } else {
            result = right;
            result.setNext(merge(left, right.getNext()));
            result.getNext().setPrev(result);
            ;

        }

        return result;
    }

    public Node<Player> mergeSort(Node<Player> headNode) {
        // if have 0 or 1 player in list -> return
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }

        Node<Player> middle = getMiddle(headNode);

        Node<Player> nextofmiddle = middle.getNext();

        // set the next of middle node to null
        middle.setNext(null);

        Node<Player> left = mergeSort(headNode);
        Node<Player> right = mergeSort(nextofmiddle);

        Node<Player> sortedlist = merge(left, right);
        return sortedlist;
    }

    public void showCharts() {
        DoublyLinkedList<Player> listPlayer = players;
        Node<Player> headNode = listPlayer.getHead();
        Node<Player> newHeadNode = mergeSort(headNode);
        while (newHeadNode != null) {
            System.out.print(newHeadNode.getData() + "\n");
            newHeadNode = newHeadNode.getNext();
        }
    }

    public void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}