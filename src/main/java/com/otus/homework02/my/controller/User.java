package com.otus.homework02.my.controller;

import com.otus.homework02.my.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class User {

    @Operation(summary = "Create a new Person object", description = "Create a new person record in DB", tags = { "User" })
    //@RequestMapping(path = "/person", method = RequestMethod.POST)
    @PostMapping(
            //path = "/person",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Person person(
            //@Valid @RequestBody Person person
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Pet object that needs to be added to the store", required = true) @Valid @RequestBody Person person
    ) {
        return person;
    }

    @Operation(summary = "Add a new object", description = "Operation desc", tags = { "User" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user response",content = @Content(schema = @Schema(implementation = Person.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, path = "/{userId}", consumes = { "application/json", "application/xml" })
    //public ResponseEntity<Person> get_user(@PathVariable(value="userId") String user_id) {
    public Person get_user(@PathVariable(value="userId") String user_id) {
        //return Collections.singletonMap("status", String.format("info by user %s", user_id));
        //return ResponseEntity.ok().headers(responseHeaders).body(new Person());
        return new Person();
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
//    @GetMapping("/{userId}")
//    public Map get_user(@PathVariable(value="userId") String user_id) {
//        return Collections.singletonMap("status", String.format("info by user %s", user_id));
//    }
//
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
