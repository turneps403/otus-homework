package com.otus.homework02.my.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public User(
        Integer userId,
        @NotNull @NotBlank @Size(max = 256) String firstName,
        @NotNull @NotBlank @Size(max = 256) String lastName,
        @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address") String email,
        @Pattern(
            // https://stackoverflow.com/questions/36991770/create-phone-number-regex
            regexp = "^(?:[+][0-9]{2}\\s?[0-9]{3}[-]?[0-9]{3,}|(?:[(][0-9]{3}[)]|[0-9]{3})\\s*[-]?\\s*[0-9]{3}[-][0-9]{4})(?:\\s*x\\s*[0-9]+)?",
            message = "Please provide a valid phone number"
        ) String phone
    ) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
