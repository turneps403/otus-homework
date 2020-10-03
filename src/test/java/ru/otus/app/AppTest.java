package ru.otus.app;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.otus.app.service.HelloOtus;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        HelloOtus classUnderTest = new HelloOtus();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
        System.out.println("goo");
    }
}
