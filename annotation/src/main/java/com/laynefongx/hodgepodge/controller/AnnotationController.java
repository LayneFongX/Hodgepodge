package com.laynefongx.hodgepodge.controller;

import com.laynefongx.hodgepodge.service.IAnnotationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Resource
    private IAnnotationService annotationService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return annotationService.sayHello();
    }
}
