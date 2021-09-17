package com.laynefongx.hodgepodge.controller;


import com.laynefongx.hodgepodge.service.I18nMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Resource
    private I18nMessageService messageService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }


    @GetMapping("/getI18nValue")
    public String getI18nValue(@RequestParam("key") String key) {
        return messageService.getMessage(key);
    }

    @GetMapping("/getI18nSay")
    public String getI18nSay(@RequestParam("key1") String key1,@RequestParam("key2") String key2) {
        String[] i18nKeys = {key2};
        return messageService.getMessage(key1, i18nKeys);
    }

}
