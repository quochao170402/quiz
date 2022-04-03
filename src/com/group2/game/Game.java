package com.group2.game;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import com.group2.dsa.hashtable.HashTableADT;
import com.group2.dsa.hashtable.HashTableImpl;
import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;
import com.group2.dsa.linkedlist.Node;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;

public class Game {
    private HashTableADT<Integer, Question> questions = new HashTableImpl<>(97);
    private HashTableADT<Integer, Category> categories = new HashTableImpl<>(7);
    private HashTableADT<Integer, Player> players = new HashTableImpl<>();
    private Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.initData();
        DoublyLinkedList<Question> linkedList = game.randomQuestion(5);
        game.play();
        System.out.println(game.players.values());
        game.scan.close();
    }

    private void initData() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Category category = new Category(UUID.randomUUID().toString());
            categories.insert(category.getId(), category);
        }

        for (int i = 0; i < 200; i++) {
            Question question = new Question(random.nextInt(10), UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                    random.nextInt(4) + 1);
            questions.insert(question.getId(), question);
        }

        // for (int i = 0; i < 300; i++) {
        // Player player = new Player(UUID.randomUUID().toString(), random.nextInt(10) *
        // 10,
        // Long.parseLong(String.valueOf(random.nextInt(100000))));
        // players.insert(player.getId(), player);
        // }
    }

    public DoublyLinkedList<Question> randomQuestion(int size) {
        int sizeOfTable = questions.size();
        DoublyLinkedList<Question> linkedList = new DoublyLinkedListImpl<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            linkedList.add(questions.get(random.nextInt(sizeOfTable)));
        }
        return linkedList;
    }

    public boolean isNumber(String string) {
        if (string == null || string.isEmpty())
            return false;
        if (string.matches("\\d+")) {
            if (Integer.parseInt(string) > 0 && Integer.parseInt(string) <= 4)
                return true;
        }
        return false;
    }

    public void play() {
        DoublyLinkedList<Question> listQuestion = randomQuestion(5);
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        int score = 0;
        long start = System.currentTimeMillis();

        Node<Question> node = listQuestion.getHead();
        while (node != null) {
            Question question = node.getData();
            System.out.println(question.display());
            String answer = null;
            do {
                System.out.print("Your answser: ");
                answer = scan.nextLine();
                if (!isNumber(answer)) {
                    System.out.println("Your answer wrong! answer must be number.");
                }
            } while (!isNumber(answer));
            if (question.getCorrectAnswer() == Integer.parseInt(answer)) {
                score += 10;
            }
            node = node.getNext();
        }
        long end = System.currentTimeMillis();
        long time = end - start;

        Player player = new Player(name, score, time);
        players.insert(player.getId(), player);
        System.out.println(player);
    }
}
