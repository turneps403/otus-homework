package org.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * sqlite3 wallet.db < hw09-wallet/src/main/resources/db.sql
 * DBPATH=wallet.db java -jar hw09-wallet/build/libs/hw09-wallet.jar
 *
 * http://localhost:8080/345/topUp?amount=10
 * http://localhost:8080/345/withdraw?amount=10
 * http://localhost:8080/345
 * {
 * "userID": 345,
 * "balance": 0
 * }
*/


@SpringBootApplication
public class WalletApp {
    public static void main(String[] args) {
        SpringApplication.run(WalletApp.class, args);
    }
}
