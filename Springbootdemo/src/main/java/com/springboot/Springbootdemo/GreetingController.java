package com.springboot.Springbootdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping("/")
    public String GreetingController(){
        return"Hello testing the spring";
    }
}

