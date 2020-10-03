package ru.otus.app.domain;

import java.util.*;

public class Question {
    private String query;
    private String right_answer;
    private List<String> wrong_answers;
    private Integer answer_id;
    private Boolean result;

    public Question(String query, String right_answer, String[] wrong_answers) {
        this.query = query;
        this.right_answer = right_answer;
        this.wrong_answers = Arrays.asList(wrong_answers);
    }

    public void show(int order_num) {
        List<String> possible_answers = new ArrayList<String>(wrong_answers);
        possible_answers.add(right_answer);
        Collections.shuffle(possible_answers);
        answer_id = possible_answers.indexOf(right_answer) + 1;

        System.out.printf("--%n%d) %s%n", order_num, query);
        for (int i = 0; i < possible_answers.size(); i++) {
            System.out.printf("\t%d. %s%n", i + 1, possible_answers.get(i));
        }
        System.out.printf("Your aswer [1 .. %d]: ", possible_answers.size());
    }

    public void receiveAnswer() {
        Scanner in = new Scanner(System.in);
        Integer num = in.nextInt();
        result = num == answer_id ? true : false;
    }

    public Boolean isGuessed() {
        return result;
    }

    public void report() {
        System.out.printf("Query: %s%nyour answer was %B%n--%n", query, this.isGuessed());
    }

}
