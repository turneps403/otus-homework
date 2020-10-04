package ru.otus.app;

import org.junit.Test;
import ru.otus.app.service.HelloOtus;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class AppTest {
    @Test
    public void testAppHasAGreeting() {
        HelloOtus classUnderTest = new HelloOtus();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
