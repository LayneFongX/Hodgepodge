package com.laynefongx.hodgepodge.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multilanguage")
public class MultilanguageController {


    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }


}
