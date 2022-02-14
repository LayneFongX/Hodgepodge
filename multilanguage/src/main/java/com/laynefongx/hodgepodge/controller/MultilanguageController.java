package com.laynefongx.hodgepodge.controller;


import com.laynefongx.hodgepodge.domain.operate.FileResult;
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

    @GetMapping("/getWiserAppCompareResult")
    public FileResult getWiserAppCompareResult() {
        return null;
    }

    @GetMapping("/getDeviceCompareResult")
    public FileResult getDeviceCompareResult() {
        return null;
    }

    @GetMapping("/getFAQCompareResult")
    public FileResult getFAQCompareResult() {
        return null;
    }

    @GetMapping("/getWiserAppMergeResult")
    public FileResult getWiserAppMergeResult() {
        return null;
    }

    @GetMapping("/getDeviceMergeResult")
    public FileResult getDeviceMergeResult() {
        return null;
    }

    @GetMapping("/getFAQMergeResult")
    public FileResult getFAQMergeResult() {
        return null;
    }
}
