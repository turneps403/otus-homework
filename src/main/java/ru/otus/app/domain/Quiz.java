package ru.otus.app.domain;

import ru.otus.app.dao.QuestionsDao;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    private Integer success_rate;

    public Quiz(QuestionsDao questions, Integer success_rate) {
        this.success_rate = success_rate;
        this.questions = questions.getQuestionList();
    }

    public void play() {
        int order_num = 1;
        for (Question q : questions) {
            q.show(order_num++);
            q.receiveAnswer();
        }
        System.out.println(new String(new char[20]).replace("\0", "-"));
    }

    public void showResult() {
        Integer guessedCnt = 0;
        for (Question q : questions) {
            q.report();
            if (q.isGuessed()) {
                guessedCnt++;
            }
        }
        System.out.printf(
                "Quiz has been %s%n",
                (char) 27 + (
                        guessedCnt >= Math.ceil(questions.size() * success_rate / 100f) ?
                                ("[32m" + "passed") : ("[31m" + "failed")
                )
        );
    }
}
