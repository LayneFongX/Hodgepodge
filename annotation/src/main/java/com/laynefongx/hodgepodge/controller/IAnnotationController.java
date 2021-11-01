package com.laynefongx.hodgepodge.controller;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;

public interface IAnnotationController {

    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_USER_MANAGE_ROOM, methodParams = @AtopPermissionAuthParams(roomIds = "param1"))
    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME, methodParams = @AtopPermissionAuthParams(deviceIds = "deviceId"))
    public String sayHello(String deviceId, String param1);

}
