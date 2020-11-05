package com.otus.homework.my.projections;

import com.otus.homework.my.dao.User;
import com.otus.homework.my.queries.UserInfoQuery;
import com.otus.homework.my.service.UserH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProjection {
    @Autowired
    private UserH2Service uservice;

    public User handle(UserInfoQuery query) {
        return uservice.selectByID(query.getUserID());
    }

    public List<User> handle() {
        return uservice.selectAll();
    }

}
