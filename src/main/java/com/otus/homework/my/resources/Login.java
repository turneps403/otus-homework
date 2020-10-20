package com.otus.homework.my.resources;

import com.otus.homework.my.domain.User;
import com.otus.homework.my.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class Login {
    @Autowired
    UserService userService;

    @RequestMapping("/lookup")
    public Map<String, String> lookup(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.lookupByEmail(email);
            if(!BCrypt.checkpw(password, user.getPassword())) {
                return Collections.singletonMap("user", "-1");
            } else {
                return Collections.singletonMap("user", String.valueOf(user.getUserId()));
            }
        } catch (Exception e) {
            return Collections.singletonMap("user", "0");
        }
    }
}
