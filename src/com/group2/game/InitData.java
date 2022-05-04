package com.group2.game;

import com.group2.dsa.hashtable.HashTableADT;
import com.group2.dsa.hashtable.HashTableImpl;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;

public class InitData {
    private HashTableADT<Integer, Question> questions = new HashTableImpl<>(10);
    private HashTableADT<Integer, Category> categories = new HashTableImpl<>(10);
    private HashTableADT<Integer, Player> players = new HashTableImpl<>();



    public HashTableADT<Integer, Category> initCategories() {
        Category category1 = new Category("Xa hoi");
        Category category2 = new Category("Cong nghe");
        categories.insert(category1.getId(), category1);
        categories.insert(category2.getId(), category2);
        return categories;
    }

    public HashTableADT<Integer, Question> initQuestions() {
        int caId1 = categories.get(1).getId();
        int caId2 = categories.get(2).getId();
        Question question1 = new Question(caId1, "Ngay giai phong hoan toan mien nam thong nhat dat nuoc la ngay may ?",
                "30/4/1970", "30/4/1975", "30/5/1975", "30/3/1970", 2);
        Question question2 = new Question(caId1, "Thu do cua Viet Nam ?", "Ha Noi", "Ho Chi Minh", "Da Nang", "Hue", 1);
        Question question3 = new Question(caId1, "Viet Nam co bao nhieu thanh pho truc thuoc trung uong ?", "1", "3",
                "4", "5", 4);
        Question question4 = new Question(caId2, "Dau la ten mot ngon ngu lap trinh ?", "Javac", "C?", "HTML", "Python",
                4);
        Question question5 = new Question(caId2, "OOP la viet tat cua ?", "Object Open Programming",
                "Object Oriented Proccessing", "Object Oriented Programming", "Open Object Programming", 3);
        Question question6 = new Question(caId1, "Thanh pho dong dan nhat Viet Nam ?", "Hai Phong", "Ha Noi",
                "Can Thoi", "Ho Chi Minh", 4);
        Question question7 = new Question(caId2, "Su truu tuong trong lap trinh huong doi tuong la gi ?", "Inheritance",
                "Polymorphism", "Abstraction", "Encapsulation", 1);
        Question question8 = new Question(caId2,
                "Cac node khong co cay con trai va phai trong cau truc du lieu cay nhi phan goi la gi ?", "Root",
                "Leaf", "Parent", "Tree", 2);
        Question question9 = new Question(caId2,
                "Trong cau truc du lieu danh sach lien ket don, mot node gom bao nhieu thanh phan ?", "3", "1", "2",
                "0", 3);
        Question question10 = new Question(caId2, "Co che hoat dong cua STACK ?", "FIFO", "LIFO", "Ca 2 deu dung",
                "Ca 2 deu sai", 1);
        questions.insert(question1.getId(), question1);
        questions.insert(question2.getId(), question2);
        questions.insert(question3.getId(), question3);
        questions.insert(question4.getId(), question4);
        questions.insert(question5.getId(), question5);
        questions.insert(question6.getId(), question6);
        questions.insert(question7.getId(), question7);
        questions.insert(question8.getId(), question8);
        questions.insert(question9.getId(), question9);
        questions.insert(question10.getId(), question10);
        return questions;
    }


    public static void main(String[] args) {
        InitData initData = new InitData();
        HashTableADT<Integer, Category> categories = initData.initCategories();
        categories.values().display();
        System.out.println();
        HashTableADT<Integer, Question> questions = initData.initQuestions();
        questions.values().display();
    }
}
