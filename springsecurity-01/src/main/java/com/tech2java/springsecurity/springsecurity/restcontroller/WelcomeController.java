package com.tech2java.springsecurity.springsecurity.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to SpringSecurity";
    }


    @GetMapping("/msg")
    public String msg(){
        return "Sample Message";
    }
}
