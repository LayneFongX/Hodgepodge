package com.laynefongx.hodgepodge.controller;


import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.enums.OperateType;
import com.laynefongx.hodgepodge.handle.FileHandleSelector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/multilanguage")
public class MultilanguageController {

    @Resource
    private FileHandleSelector fileHandleSelector;


    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }


    @GetMapping("/getDeviceCompareResult")
    public FileResult getDeviceCompareResult() {
        return fileHandleSelector.handle(FileType.DEVICE_RELATED.getType(), OperateType.COMPARE.getType(),
                null, getOperateConfigDto());
    }

    private OperateConfigDto getOperateConfigDto() {
        String _a =
                "{\"env\":\"release\",\"fileTypes\":[\"Device Related\"],\"languageIds\":[1],\"products\":[\"IP Indoor Camera\",\"IP Outdoor Camera\"]}";
        return JSONObject.parseObject(_a, OperateConfigDto.class);
    }
}
