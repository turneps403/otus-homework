package ru.otus.app.service;

import ru.otus.app.domain.Quiz;

public class QuizServiceImpl implements QuizService {
    private final Quiz q;

    public QuizServiceImpl(Quiz q) {
        this.q = q;
    }

    public void play() {
        q.play();
        q.showResult();
    }
}
