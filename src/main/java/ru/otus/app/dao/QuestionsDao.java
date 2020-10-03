package ru.otus.app.dao;

import ru.otus.app.domain.Question;

import java.util.List;

public interface QuestionsDao {
    public List<Question> getQuestionList();
}
