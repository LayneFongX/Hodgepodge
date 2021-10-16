package com.laynefongx.hodgepodge.controller;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.enums.PermissionTypeEnum;
import com.laynefongx.hodgepodge.enums.UserRoleTypeEnum;
import com.laynefongx.hodgepodge.service.IAnnotationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Resource
    private IAnnotationService annotationService;

    @GetMapping("/sayHello")
    @AtopPermissionAuth(permissionType = PermissionTypeEnum.USER_HOME, userRoleTypes = {UserRoleTypeEnum.OWNER, UserRoleTypeEnum.ADMIN,
            UserRoleTypeEnum.MEMBER})
    public String sayHello() {
        return annotationService.sayHello();
    }
}
