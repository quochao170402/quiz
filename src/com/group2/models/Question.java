package com.group2.models;

import java.util.Objects;

public class Question {
    private Integer id;
    private Integer categoryId;
    private String content, answerA, answerB, answerC, answerD;
    private Integer correctAnswer;
    private static Integer idAutoIncrement = 1;

    public Question() {
        this.id = idAutoIncrement++;
    }

    public Question(Integer categoryId, String content, String answerA, String answerB, String answerC, String answerD,
            Integer correctAnswer) {
        this.id = idAutoIncrement++;
        this.categoryId = categoryId;
        this.content = content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public Question(Integer id, Integer categoryId, String content, String answerA, String answerB, String answerC,
            String answerD, Integer correctAnswer) {
        this.id = id;
        this.categoryId = categoryId;
        this.content = content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public static int getIdAutoIncrement() {
        return idAutoIncrement;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerA() {
        return this.answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return this.answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return this.answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return this.answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public Integer getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, answerA, answerB, answerC, answerD, correctAnswer);
    }

    public String getCorrectAnswer(int correctAnswer) {
        if (correctAnswer == 1)
            return "A";
        else if (correctAnswer == 2)
            return "B";
        else if (correctAnswer == 3)
            return "C";
        else
            return "D";
    }

    @Override
    public String toString() {
        return "ID:" + this.id + "\tContent: " + this.content + "\tAnswer A: " + this.answerA + "\tAnswer B: "
                + this.answerB + "\tAnswer C: " + this.answerC + "\tAnswer D: " + this.answerD + "\tCorrect anwser: "
                + getCorrectAnswer(this.correctAnswer);
    }

    public String display() {
        return getContent() + "\n" +
                "1. " + getAnswerA() + "\n" +
                "2. " + getAnswerB() + "\n" +
                "3. " + getAnswerC() + "\n" +
                "4. " + getAnswerD() + "\n";
    }

}
