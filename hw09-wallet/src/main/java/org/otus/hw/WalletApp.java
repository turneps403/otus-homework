package org.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletApp {
    public static void main(String[] args) {
        System.out.printf("======== Foo Bar");
        SpringApplication.run(WalletApp.class, args);
    }
}
