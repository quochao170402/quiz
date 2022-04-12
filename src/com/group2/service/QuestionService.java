package com.group2.service;

import com.group2.dsa.hashtable.HashTableADT;
import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.models.Question;

public interface QuestionService {
    void save(HashTableADT<Integer, Question> questions, Question question);

    void update(HashTableADT<Integer, Question> questions, Question question);

    void deleteById(HashTableADT<Integer, Question> questions, Integer id);

    Question findById(HashTableADT<Integer, Question> questions, Integer id);

    DoublyLinkedList<Question> findAll(HashTableADT<Integer, Question> questions);

    DoublyLinkedList<Question> findAllByCategory(HashTableADT<Integer, Question> questions, Integer id);

}
