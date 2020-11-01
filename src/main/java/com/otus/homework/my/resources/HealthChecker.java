//package com.otus.homework.my.resources;
//
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//import java.util.Map;
//
//@RestController
//@RequestMapping(path = "/myactuator", produces = MediaType.APPLICATION_JSON_VALUE)
//public class HealthChecker {
//    @RequestMapping("/health")
//    @Operation(hidden = true)
//    public Map health() {
//        return Collections.singletonMap("status", "OK");
//    }
//
//    @RequestMapping(value = "/isready")
//    @Operation(hidden = true)
//    public Map isready() {
//        return Collections.singletonMap("res", "yes");
//    }
//}
