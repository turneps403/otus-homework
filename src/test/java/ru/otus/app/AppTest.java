package ru.otus.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.otus.app.service.QuizService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void checkQuizServiceBean() {
        /*
            Check existence of "quizService" bean in cotext xml
         */
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
            assertTrue(context.containsBean("quizService"));
            QuizService service = context.getBean("quizService", QuizService.class);

            boolean hasMethodPlay = false;
            Method[] methods = service.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals("play")) {
                    hasMethodPlay = true;
                    break;
                }
            }
            assertTrue(hasMethodPlay);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
    }
}
