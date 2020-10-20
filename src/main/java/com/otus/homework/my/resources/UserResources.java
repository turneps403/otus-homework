package com.otus.homework.my.resources;

import com.otus.homework.my.domain.User;
import com.otus.homework.my.exceptions.MyBadRequestException;
import com.otus.homework.my.exceptions.MyForbiddenException;
import com.otus.homework.my.exceptions.MyResourceNotFoundException;
import com.otus.homework.my.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResources {

    @Autowired
    UserService userService;

    @Value("${authorization.user.header}")
    String AUTH_USER_HEADER;

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
        @ApiResponse(responseCode = "404", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = MyResourceNotFoundException.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = MyForbiddenException.class)))
    })
    public ResponseEntity<User> get_user(HttpServletRequest request, @PathVariable(value="userId") Integer user_id) {
        String aUser = request.getHeader(AUTH_USER_HEADER);
        if (!aUser.equals( String.valueOf(user_id) )) {
            throw new MyForbiddenException("forbidden");
        }

        User user = userService.lookupById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user info", description = "Remove a particular user object", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "user had been deleted",content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = MyForbiddenException.class)))
    })
    public ResponseEntity<Map<String, String>> delete_user(HttpServletRequest request, @PathVariable(value="userId") Integer user_id) {
        String aUser = request.getHeader(AUTH_USER_HEADER);
        if (!aUser.equals( String.valueOf(user_id) )) {
            throw new MyForbiddenException("forbidden");
        }

        userService.removeUser(user_id);
        Map resp = Collections.singletonMap("deleted", "ok");
        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user", description = "Update exists users record", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user updated",content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(implementation = MyBadRequestException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = MyForbiddenException.class)))
    })
    public ResponseEntity<User> update_user(
            HttpServletRequest request,
            @PathVariable(value="userId") Integer user_id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true
            ) @RequestBody User req_user
    ) {
        String aUser = request.getHeader(AUTH_USER_HEADER);
        if (!aUser.equals( String.valueOf(user_id) )) {
            throw new MyForbiddenException("forbidden");
        }

        userService.updateUser(user_id, req_user);
        User user = userService.lookupById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
