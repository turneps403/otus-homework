package ru.otus.app.dao;

import ru.otus.app.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvQuestionsDao implements QuestionsDao {
    private String fileName;
    private List<Question> questionList;

    public CsvQuestionsDao(String fileName) {
        if (!fileName.startsWith("/")) {
            fileName = "/" + fileName;
        }
        this.fileName = fileName;
        this.questionList = new ArrayList<Question>();
        this.init();
    }

    public void init() {
        try {
            InputStream is = this.getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                /**
                 * question;right_answer;wrong_answer1;...;wrong_answerN
                 */
                String[] sentenses = line.split(";");
                Question q = new Question(
                        sentenses[0],
                        sentenses[1],
                        Arrays.copyOfRange(sentenses, 2, sentenses.length)
                );
                questionList.add(q);
            }
            reader.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (Exception e) {
            System.err.printf("Error: Target File Cannot Be Read. Reason is %s%n", e);
        }
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
