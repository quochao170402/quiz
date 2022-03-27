package com.group2.models;

import java.util.Objects;

public class Question {
    private Integer id;
    private Integer categoryId;
    private String content, answerA, answerB, answerC, answerD, correctAnswer;
    private static Integer idAutoIncrement = 0;

    public Question() {
        this.id = idAutoIncrement++;
    }

    public Question(Integer categoryId, String content, String answerA, String answerB, String answerC, String answerD,
            String correctAnswer) {
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
            String answerD, String correctAnswer) {
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

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
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

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", content='" + getContent() + "'" +
                ", answerA='" + getAnswerA() + "'" +
                ", answerB='" + getAnswerB() + "'" +
                ", answerC='" + getAnswerC() + "'" +
                ", answerD='" + getAnswerD() + "'" +
                ", correctAnswer='" + getCorrectAnswer() + "'" +
                "}";
    }

}
