-- >> sqlite3 sqlitesample.db < sqlitesample.sql

CREATE TABLE User
(
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(120) NOT NULL,
    last_name  VARCHAR(120) NOT NULL,
    version    INTEGER DEFAULT 1 NOT NULL,
    UNIQUE (email)
);

CREATE TABLE User_OUTBOX
(
    OrderID INTEGER PRIMARY KEY AUTOINCREMENT,
    Operation VARCHAR(255) NOT NULL,
    CreatedAt TEXT NOT NULL,
    ID INTEGER NOT NULL,
    email VARCHAR(255) DEFAULT NULL,
    first_name VARCHAR(120) DEFAULT NULL,
    last_name  VARCHAR(120) DEFAULT NULL,
    version INTEGER DEFAULT NULL
);

CREATE TRIGGER IF NOT EXISTS user_outbox_insert
   AFTER INSERT
   ON User
BEGIN
    INSERT INTO User_OUTBOX(Operation, CreatedAt, ID, email, first_name, last_name, version)
    VALUES (
        'INSERT',
        DATETIME('NOW'),
        new.ID,
        new.email,
        new.first_name,
        new.last_name,
        new.version
    );
END;

CREATE TRIGGER IF NOT EXISTS user_outbox_update
   AFTER UPDATE
   ON User
BEGIN
    INSERT INTO User_OUTBOX(Operation, CreatedAt, ID, email, first_name, last_name, version)
    VALUES (
        'UPDATE',
        DATETIME('NOW'),
        old.ID,
        CASE WHEN new.email <> old.email THEN new.email ELSE NULL END,
        CASE WHEN new.first_name <> old.first_name THEN new.first_name ELSE NULL END,
        CASE WHEN new.last_name <> old.last_name THEN new.last_name ELSE NULL END,
        new.version
    );
END;

CREATE TRIGGER IF NOT EXISTS user_outbox_delete
   AFTER DELETE
   ON User
BEGIN
    INSERT INTO User_OUTBOX(Operation, CreatedAt, ID, version)
    VALUES (
        'DELETE',
        DATETIME('NOW'),
        old.ID,
        old.version
    );
END;