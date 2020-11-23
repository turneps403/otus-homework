-- sqlite3 wallet.db < hw09-wallet/src/main/resources/db.sql

DROP TABLE IF EXISTS Wallet;
CREATE TABLE Wallet
(
    userID INTEGER PRIMARY KEY,
    balance INTEGER NOT NULL DEFAULT 0
);

INSERT INTO Wallet(userID, balance) VALUES (100500, 300);