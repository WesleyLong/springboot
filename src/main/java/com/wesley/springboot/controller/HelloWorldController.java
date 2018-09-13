package com.wesley.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    @RequestMapping("/hello1")
    public String hello1() {
        return "Hello World1";
    }
}