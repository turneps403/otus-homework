package com.otus.homework02.my.model;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class Person {
    private long id;

    @NotNull
    @NotBlank
    @Size(max = 256)
    private String username;

    private String firstName;

    private String lastName;

    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    @Email()
    private String email1;

    @Min(18)
    @Max(30)
    private int age;
}
