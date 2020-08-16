package com.otus.homework02.my.resources;

import com.otus.homework02.my.domain.User;
import com.otus.homework02.my.exceptions.MyBadRequestException;
import com.otus.homework02.my.exceptions.MyResourceNotFoundException;
import com.otus.homework02.my.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResources {

    @Autowired
    UserService userService;

    @PostMapping()
    @Operation(summary = "Create a user", description = "Creates a new users record", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created",content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(implementation = MyBadRequestException.class)))
    })
    public ResponseEntity<User> create_user(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Set user_id with 0 value just for default",
                    required = true
            ) @RequestBody User req_user
    ) {
        User user = userService.createUser(req_user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Show user info", description = "Reads a particular user object and exposes it", tags = { "User" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user response",content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = MyResourceNotFoundException.class)))
    })
    public ResponseEntity<User> get_user(HttpServletRequest request, @PathVariable(value="userId") Integer user_id) {
        User user = userService.lookupById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/user")
//@Api(value = "HelloWorld Resource", description = "shows hello world")
//public class User {
////    @PostMapping()
////    public Map create_user(@RequestBody final String user) {
////        return Collections.singletonMap("status", String.format("delete user %s", user));
////    }
//
//    @DeleteMapping("/{userId}")
//    public Map delete_user(@PathVariable(value="userId") String user_id) {
//        return Collections.singletonMap("status", String.format("delete user %s", user_id));
//    }
//
//    @PutMapping("/{userId}")
//    public Map update_user(@PathVariable(value="userId") String user_id) {
//        return Collections.singletonMap("status", String.format("update user %s", user_id));
//    }
//
//    @GetMapping()
//    public List<Map> get_list_of_users() {
//        return Arrays.asList(
//                Collections.singletonMap("status", "user1")
//        );
//    }
//
//    private class UserItem {
//        private String userName;
//        private Long Salary;
//
//        public UserItem(String userName, Long salary) {
//            this.userName = userName;
//            Salary = salary;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public Long getSalary() {
//            return Salary;
//        }
//
//        public void setSalary(Long salary) {
//            Salary = salary;
//        }
//    }
//}
