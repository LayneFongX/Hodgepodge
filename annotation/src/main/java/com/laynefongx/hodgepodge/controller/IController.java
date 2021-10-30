package com.laynefongx.hodgepodge.controller;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.base.ApiRequestDO;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;

public interface IController {

    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME, methodParams = @AtopPermissionAuthParams(deviceIds = "deviceId"))
    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_HOME_MEMBER, methodParams = @AtopPermissionAuthParams(deviceIds = "deviceId"))
    public String sayHello(String deviceId, String params1);

}
