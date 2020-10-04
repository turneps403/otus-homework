package ru.otus.app;

import ru.otus.app.service.HelloOtus;

public class App {
    public static void main(String[] args) {
        System.out.println(new HelloOtus().getGreeting());
    }
}
