package com.group2.game;

import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;

import java.util.Random;
import java.util.UUID;

public class Demo {

    public static final DoublyLinkedList<Category> categorys = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Question> questions = new DoublyLinkedListImpl<>();
    public static final DoublyLinkedList<Player> players = new DoublyLinkedListImpl<>();

    public static void main(String[] args) {
        initData();
        System.out.println(categorys);
        System.out.println(questions);
        System.out.println(players);
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
