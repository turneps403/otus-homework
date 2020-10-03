package ru.otus.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.app.service.QuizService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService service = context.getBean("quizService", QuizService.class);
        service.play();
    }
}
