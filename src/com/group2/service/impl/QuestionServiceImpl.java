package com.group2.service.impl;

import com.group2.dsa.hashtable.HashTableADT;
import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.models.Question;
import com.group2.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

    @Override
    public void save(HashTableADT<Integer, Question> questions, Question question) {
        questions.insert(question.getId(), question);

    }

    @Override
    public void update(HashTableADT<Integer, Question> questions, Question question) {
        if (!questions.has(question.getId())) {
            System.out.println("Not found question with id " + question.getId());
            return;
        }
        questions.insert(question.getId(), question);

    }

    @Override
    public void deleteById(HashTableADT<Integer, Question> questions, Integer id) {
        if(questions.has(id)){
            System.out.println("Not found question with id "+id);
            return;
        }

        questions.remove(id);
    }

    @Override
    public Question findById(HashTableADT<Integer, Question> questions, Integer id) {
        return questions.get(id);
    }

    @Override
    public DoublyLinkedList<Question> findAll(HashTableADT<Integer, Question> questions) {
        return questions.values();
    }

    @Override
    public DoublyLinkedList<Question> findAllByCategory(HashTableADT<Integer, Question> questions, Integer id) {
        
        return null;
    }

}
