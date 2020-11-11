package org.otus.hw07.repo;

import org.otus.hw07.entities.User;
import org.otus.hw07.exceptions.MyBadRequestException;
import org.otus.hw07.exceptions.MyResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepo {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String SQL_CREATE =
            "INSERT INTO User (email, first_name, last_name) VALUES (?, ?, ?)";

    private static final String SQL_FIND_BY_ID =
            "SELECT ID, email, first_name, last_name, version FROM User WHERE ID = ?";

    private static final String SQL_UPDATE_BY_ID_VERSION_STRATEGY =
            "UPDATE User SET email = ?, first_name =? , last_name = ?, version = version + 1 "
                    + "WHERE ID = ? AND version = ?";

    private static final String SQL_DELETE_USER =
            "DELETE FROM User WHERE ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User read(Integer userID) throws MyResourceNotFoundException {
        try {
            log.info("Query for lookup:" + SQL_FIND_BY_ID + "with user " + userID);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userID}, userRowMapper);
        } catch (Exception e) {
            throw new MyResourceNotFoundException("User not found", e);
        }
    }

    public User create(User req_user) throws MyBadRequestException {
        Integer userID;

        try {
            log.info("Query for create:" + SQL_CREATE);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, req_user.getEmail());
                ps.setString(2, req_user.getFirstName());
                ps.setString(3, req_user.getLastName());
                return ps;
            }, keyHolder);
            userID = (Integer) keyHolder.getKey();
        } catch (Exception e) {
            throw new MyBadRequestException("Invalid request", e);
        }

        return this.read(userID);
    }

    public User update(Integer userID, User req_user) throws MyBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE_BY_ID_VERSION_STRATEGY, new Object[]{
                    req_user.getEmail(), req_user.getFirstName(), req_user.getLastName(),
                    userID, req_user.getVersion()
            });
        } catch (Exception e) {
            throw new MyBadRequestException("Invalsid request", e);
        }
        return this.read(userID);
    }

    public void delete(Integer userID) {
        jdbcTemplate.update(SQL_DELETE_USER, new Object[]{userID});
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        System.out.println("In row mapper");
        return new User(
                rs.getInt("ID"),
                rs.getString("email"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("version")
        );
    });

}
