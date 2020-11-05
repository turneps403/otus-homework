package com.otus.homework.my.resources;

import com.otus.homework.my.dao.User;
import com.otus.homework.my.projections.UserProjection;
import com.otus.homework.my.queries.UserInfoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/q", produces = MediaType.APPLICATION_JSON_VALUE)
public class Query {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserProjection uProjection;

    @GetMapping("/user")
    public User getUser(@RequestBody UserInfoQuery query) {
        return uProjection.handle(query);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return uProjection.handle();
    }


}
