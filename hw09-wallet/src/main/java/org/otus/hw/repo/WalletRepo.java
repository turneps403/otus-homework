package org.otus.hw.repo;

import org.otus.hw.entities.Wallet;
import org.otus.utils.exceptions.MyBadRequestException;
import org.otus.utils.exceptions.MyResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class WalletRepo {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String SQL_LOOKUP =
            "SELECT userID, balance FROM Wallet WHERE userID = ?";
    private static final String SQL_CREATE =
            "INSERT OR IGNORE INTO Wallet(userID, balance) VALUES (?, 0)";
    private static final String SQL_TOPUP =
            "UPDATE Wallet SET balance = balance + ? WHERE userID = ?";
    private static final String SQL_WITHDRAW =
            "UPDATE Wallet SET balance = balance - ? WHERE userID = ? AND balance >= ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Wallet read(Integer userID) throws MyResourceNotFoundException {
        try {
            log.info("Query for lookup:" + SQL_LOOKUP + "with user " + userID);
            return jdbcTemplate.queryForObject(SQL_LOOKUP, new Object[]{userID}, walletRowMapper);
        } catch (Exception e) {
            throw new MyResourceNotFoundException("Wallet not found", e);
        }
    }

    public Wallet topUp(Integer userID, Integer amount) throws MyBadRequestException {
        try {
            log.info("Query for create if needed:" + SQL_CREATE);
            jdbcTemplate.update(SQL_CREATE, new Object[]{ userID });
            log.info("Query for top up:" + SQL_TOPUP);
            jdbcTemplate.update(SQL_TOPUP, new Object[]{ amount, userID });
        } catch (Exception e) {
            throw new MyBadRequestException("Invalid request", e);
        }

        return this.read(userID);
    }

    public boolean withdraw(Integer userID, Integer amount) throws MyBadRequestException {
        Integer affectedCnt = 0;
        try {
            log.info("Query for create if needed:" + SQL_CREATE);
            jdbcTemplate.update(SQL_CREATE, new Object[]{ userID });
            log.info("Query for withdraw:" + SQL_TOPUP);
            affectedCnt = jdbcTemplate.update(SQL_WITHDRAW, new Object[]{ amount, userID, amount });
        } catch (Exception e) {
            throw new MyBadRequestException("Invalid request", e);
        }

        return affectedCnt > 0;
    }

    private RowMapper<Wallet> walletRowMapper = ((rs, rowNum) -> {
        System.out.println("In row mapper");
        return new Wallet(
                rs.getInt("userID"),
                rs.getInt("balance")
        );
    });

}
