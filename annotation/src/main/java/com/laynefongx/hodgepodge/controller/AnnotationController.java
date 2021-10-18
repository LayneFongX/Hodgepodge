package com.laynefongx.hodgepodge.controller;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParam;
import com.laynefongx.hodgepodge.base.ApiRequestDO;
import com.laynefongx.hodgepodge.constant.AtopPermissionAuthConstant;
import com.laynefongx.hodgepodge.enums.VerifyMethodsEnum;
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
    @AtopPermissionAuth(methods = {VerifyMethodsEnum.VERIFY_HOME_ADMIN}, isParseApiRequestDO = true, methodParams = {
            @AtopPermissionAuthParam(methodParamName = "homeId", paramMapping = AtopPermissionAuthConstant.HOME_ID)})
    public String sayHello(Long homeId, ApiRequestDO apiRequestDO) {
        return annotationService.sayHello();
    }
}
