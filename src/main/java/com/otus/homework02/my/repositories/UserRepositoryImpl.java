package com.otus.homework02.my.repositories;

import com.otus.homework02.my.domain.User;
import com.otus.homework02.my.exceptions.MyBadRequestException;
import com.otus.homework02.my.exceptions.MyResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    // see homework_db.sql
    private static final String FILEDS = "user_id, first_name, last_name, email, phone";

    private static final String SQL_FIND_BY_ID =
            "SELECT "+ FILEDS +" FROM my_users WHERE user_id = ?";
    private static final String SQL_CREATE =
            "INSERT INTO my_users ("+ FILEDS +") VALUES(NEXTVAL('my_users_seq'), ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE my_users SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE user_id = ?";
    private static final String SQL_DELETE_USER =
            "DELETE FROM my_users WHERE user_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String phone) throws MyBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, phone);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("user_id");
        }catch (Exception e) {
            throw new MyBadRequestException("Invalid request", e);
        }
    }

    @Override
    public User read(Integer userId) throws MyResourceNotFoundException {
        try {
            System.out.println("Query for lookup " + SQL_FIND_BY_ID + " with " + userId);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
        }catch (Exception e) {
            throw new MyResourceNotFoundException("User not found", e);
        }
    }

    @Override
    public void update(Integer userId, User user) throws MyBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{
                    user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), userId
            });
        }catch (Exception e) {
            throw new MyBadRequestException("Invalid request", e);
        }
    }

    @Override
    public void delete(Integer userId) {
        jdbcTemplate.update(SQL_DELETE_USER, new Object[]{userId});
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        System.out.println("In row mapper");
        return new User(
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone")
        );
    });
}
