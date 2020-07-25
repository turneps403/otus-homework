package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/health")
    public Map health() {
        return Collections.singletonMap("status", "OK");
    }

    @RequestMapping("/ping")
    public Map ping() {
        return Collections.singletonMap("res", "pong");
    }

    @RequestMapping(value = "/isready", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map isready() {
        return Collections.singletonMap("res", "yes");
    }

}
