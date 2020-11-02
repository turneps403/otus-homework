package com.otus.homework.my.commands;

import lombok.Data;

@Data
public class CreateUserCommand implements Command {
    public String firstName;
    public String lastName;
}
