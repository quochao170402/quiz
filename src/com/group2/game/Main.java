package com.group2.game;

import java.util.Random;
import java.util.Scanner;

import com.group2.dsa.hashtable.HashTableADT;
import com.group2.dsa.hashtable.HashTableImpl;
import com.group2.dsa.linkedlist.DoublyLinkedList;
import com.group2.dsa.linkedlist.DoublyLinkedListImpl;
import com.group2.dsa.linkedlist.Node;
import com.group2.models.Category;
import com.group2.models.Player;
import com.group2.models.Question;
import com.group2.utils.Sorter;

public class Main {
    private HashTableADT<Integer, Question> questions = new HashTableImpl<>(97);
    private HashTableADT<Integer, Category> categories = new HashTableImpl<>(7);
    private HashTableADT<Integer, Player> players = new HashTableImpl<>();
    private Sorter sorter = new Sorter();
    private Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Main game = new Main();
        game.initData();
        game.run();

    }

    private void initData() {
        InitData initData = new InitData();
        categories = initData.initCategories();
        questions = initData.initQuestions();
        // Random random = new Random();
        // for (int i = 0; i < 3; i++) {
        // Category category = new Category(UUID.randomUUID().toString());
        // categories.insert(category.getId(), category);
        // }

        // for (int i = 0; i < 20; i++) {
        // Question question = new Question(random.nextInt(3),
        // UUID.randomUUID().toString(),
        // UUID.randomUUID().toString(),
        // UUID.randomUUID().toString(), UUID.randomUUID().toString(),
        // UUID.randomUUID().toString(),
        // random.nextInt(4) + 1);
        // questions.insert(question.getId(), question);
        // }

        // for (int i = 0; i < 50; i++) {
        // Player player = new Player(UUID.randomUUID().toString(), random.nextInt(10) *
        // 10,
        // (long) random.nextInt(1000000));
        // players.insert(player.getId(), player);
        // }
    }

    public void menu() {
        System.out.println("QUIZ GAME");
        System.out.println("1. Play game.");
        System.out.println("2. View rankings.");
        System.out.println("3. View rankings by player name.");
        System.out.println("4. Display all question.");
        System.out.println("5. Display all question by category.");
        System.out.println("6. Find question by id.");
        System.out.println("7. Add new question.");
        System.out.println("8. Update question.");
        System.out.println("9. Delete question.");
        System.out.println("10. Quit.");
        System.out.print("Your option (1-10): ");
    }

    public void run() {
        boolean flag = true;
        int option = 0;
        do {
            // Player input and check input
            do {
                menu();
                String str = scan.nextLine();
                if (isNumber(str) && isOption(Integer.parseInt(str))) {
                    option = Integer.parseInt(str);
                    break;
                } else {
                    System.out.println("Option must number in [1-10].Reiptut!!!");
                }
            } while (true);

            switch (option) {
                case 1:// Play game
                    Player player = playGame();
                    players.insert(player.getId(), player);
                    System.out.println(player);
                    break;

                case 2:// view rankings
                    DoublyLinkedList<Player> playerList = displayRankings();
                    System.out.println("RANKING");
                    Node<Player> nodes = sorter.mergeSort(playerList.getHead());
                    while (nodes != null) {
                        System.out.println(nodes);
                        nodes = nodes.getNext();
                    }
                    break;

                case 3:
                    Player foundPlayer = findRankingByPlayerName();
                    if (foundPlayer != null) {
                        System.out.println("Rank of player " + foundPlayer.getName() + ":");
                        System.out.println(foundPlayer);
                    } else {
                        System.out.println("NOT FOUND PLAYER");
                    }
                    break;

                case 4:
                    DoublyLinkedList<Question> questionList = displayAllQuestions();
                    System.out.println("ALL QUESTIONS");
                    questionList.display();
                    break;

                case 5:
                    DoublyLinkedList<Question> questionListWithCategory = displayAllQuestionsByCategory();
                    System.out.println("ALL QUESTION WITH CATEGORY ID "
                            + questionListWithCategory.getHead().getData().getCategoryId());
                    questionListWithCategory.display();
                    break;

                case 6:
                    Question question = findQuestionById();
                    if (question != null) {
                        System.out.println("QUESTION WITH ID " + question.getId());
                        System.out.println(question);
                    } else {
                        System.out.println("NOT FOUND QUESTION");
                    }
                    break;

                case 7:
                    addQuestion();
                    System.out.println("ADD QUESTION SUCCESSFUL");
                    break;

                case 8:
                    updateQuestion();
                    System.out.println("UPDATE QUESTION SUCCESSFUL");
                    break;

                case 9:
                    deleteQuestion();
                    System.out.println("DELETE QUESTION SUCCESSFUL");
                    break;
                case 10:
                default:
                    flag = false;
                    System.out.println("Thank you!!! See you again.");
                    break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (flag);
    }

    private DoublyLinkedList<Question> displayAllQuestionsByCategory() {
        DoublyLinkedList<Category> list = categories.values();
        list.display();
        System.out.println("Choose category id : ");
        int id = -1;
        do {
            String str = scan.nextLine();

            if (isNumber(str) && isCategoryId(Integer.parseInt(str), list)) {
                id = Integer.parseInt(str);
                break;
            } else {
                System.out.println("Invalid!!!Reinput");
            }
        } while (true);
        DoublyLinkedList<Question> listQuestion = questions.values();
        DoublyLinkedList<Question> result = new DoublyLinkedListImpl<>();
        for (Question question : listQuestion) {
            if (question.getCategoryId() == id) {
                result.add(question);
            }
        }
        return result;
    }

    private void deleteQuestion() {
        System.out.println("Enter question id: ");
        String strId = scan.nextLine();
        if (isNumber(strId)) {
            int id = Integer.parseInt(strId);

            if (questions.has(id)) {
                questions.remove(id);
            } else {
                System.out.println("Not found question with id " + id);
            }

        } else {
            System.out.println("Id must numeric");
        }
    }

    private void updateQuestion() {
        System.out.println("Enter question id: ");
        String strId = scan.nextLine();
        if (isNumber(strId)) {
            int id = Integer.parseInt(strId);
            Question question = questions.get(id);

            if (question == null) {
                System.out.println("Not found question with id " + id);
                return;
            }
            question = enterQuestion(question);
            questions.insert(id, question);

        } else {
            System.out.println("Id must numeric");
            System.out.println("Quit.");
            return;
        }

    }

    private void addQuestion() {
        System.out.println("ADD NEW QUESTION");
        Question question = new Question();
        question = enterQuestion(question);
        questions.insert(question.getId(), question);
    }

    private Question enterQuestion(Question question) {
        System.out.println("Choose category: ");
        categories.values().display();
        do {
            String str = scan.nextLine();

            if (isNumber(str) && isCategoryId(Integer.parseInt(str), categories.values())) {
                question.setCategoryId(Integer.parseInt(str));
                break;
            } else {
                System.out.println("Invalid!!!Reinput");
            }
        } while (true);

        System.out.print("Content : ");
        question.setContent(scan.nextLine());

        System.out.print("Answer A: ");
        question.setAnswerA(scan.nextLine());

        System.out.print("Answer B: ");
        question.setAnswerB(scan.nextLine());

        System.out.print("Answer C: ");
        question.setAnswerC(scan.nextLine());

        System.out.print("Answer D: ");
        question.setAnswerD(scan.nextLine());

        System.out.println("Correct answer : ");
        do {
            String str = scan.nextLine();
            if (isNumber(str) && isAnswer(Integer.parseInt(str))) {
                question.setCorrectAnswer(Integer.parseInt(str));
                break;
            } else {
                System.out.println("Invalid!!!Reinput");
            }
        } while (true);

        return question;

    }

    private Question findQuestionById() {
        System.out.println("Enter question id; ");
        int id = 0;

        do {
            String strId = scan.nextLine();
            if (isNumber(strId)) {
                id = Integer.parseInt(strId);
                break;
            } else {
                System.out.println("Invalid!!! Reinput.");
            }
        } while (true);

        for (Question question : questions.values()) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        System.out.println("Not found question with id " + id);
        return null;
    }

    private DoublyLinkedList<Question> displayAllQuestions() {
        return questions.values();
    }

    private Player findRankingByPlayerName() {
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        Player player = rankingByName(name);
        if (player == null) {
            return null;
        } else
            return player;
    }

    private Player rankingByName(String name) {
        for (Player player : players.values()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    private DoublyLinkedList<Player> displayRankings() {
        return players.values();
    }

    // Generate random questions for the game.
    // Use java's random string generator to generate questions and answers

    public DoublyLinkedList<Question> randomQuestion(int size) {
        int sizeOfTable = questions.size();
        DoublyLinkedList<Question> linkedList = new DoublyLinkedListImpl<>();
        Random random = new Random();
        int temp = 0;
        while (temp < size) {
            Question question = questions.get(random.nextInt(sizeOfTable));
            if (!linkedList.contains(question) && question != null) {
                linkedList.add(question);
                temp++;
            }
        }
        return linkedList;
    }

    private boolean isNumber(String string) {
        if (string == null || string.isEmpty())
            return false;
        if (string.matches("\\d+")) {
            return true;
        }
        return false;
    }

    private boolean isAnswer(int answer) {
        if (answer >= 1 && answer <= 4)
            return true;
        return false;
    }

    private boolean isOption(int option) {
        if (option >= 1 && option <= 10)
            return true;
        return false;
    }

    private boolean isCategoryId(int id, DoublyLinkedList<Category> values) {
        for (Category category : values) {
            if (category.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Player playGame() {
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
                } else {
                    if (!isAnswer(Integer.parseInt(answer))) {
                        System.out.println("Answer must be in the range 1 - 4");
                    } else {
                        break;
                    }
                }

            } while (true);

            if (question.getCorrectAnswer() == Integer.parseInt(answer)) {
                score += 10;
            }
            node = node.getNext();
        }
        long end = System.currentTimeMillis();
        long time = end - start;

        Player player = new Player(name, score, time);
        return player;
    }

}
