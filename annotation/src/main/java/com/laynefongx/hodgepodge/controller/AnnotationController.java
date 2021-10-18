package com.laynefongx.hodgepodge.controller;

import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParam;
import com.laynefongx.hodgepodge.base.ApiContextDO;
import com.laynefongx.hodgepodge.base.ApiRequestDO;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
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
    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME,
            methodParams = @AtopPermissionAuthParam(apiRequestDO = "apiRequestDO", deviceIds = "deviceId", homeId = "home1", groupIds = "home2"))
    public String sayHello(String deviceId, String home1, String home2, ApiRequestDO apiRequestDO) {
        return annotationService.sayHello();
    }

    public static void main(String[] args) {
        ApiContextDO contextDO = new ApiContextDO();
        contextDO.setGid("312534534");
        contextDO.setUid("laynefong");

        ApiRequestDO requestDO = new ApiRequestDO();
        requestDO.setApiContextDo(contextDO);
        System.out.println(JSONObject.toJSONString(requestDO));
    }
}
