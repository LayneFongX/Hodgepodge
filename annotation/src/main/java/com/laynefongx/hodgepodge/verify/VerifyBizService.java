package com.laynefongx.hodgepodge.verify;

import com.google.common.collect.ImmutableMap.Builder;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Legal[guo.li@tuya.com]
 * @date 2021/3/10
 */

@Slf4j
@Service
public class VerifyBizService {

    public void matchVerifyMethodByMethodName(String methodName, AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        methodMappings.get(methodName).accept(verifyMethodParams,argsMap);
    }


    private final Map<String, BiConsumer<AtopPermissionAuthParams, Map<String,Object>>> methodMappings
            = new Builder<String, BiConsumer<AtopPermissionAuthParams, Map<String,Object>>>()
            .put(VerifyMethodEnum.VERIFY_HOME_OWNER.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyHomeOwner(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_HOME_ADMIN.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyHomeAdmin(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_HOME_MEMBER.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyHomeMember(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_MANAGE_DEVICE.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserManageDevice(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_CONTROL_DEVICE.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserControlDevice(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_MANAGE_ROOM.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserManageRoom(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_QUERY_ROOM.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserQueryRoom(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_MANAGE_GROUP.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserManageGroup(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_QUERY_GROUP.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserQueryGroup(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_USER_MANAGE_SCENE.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyUserManageScene(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_DEVICE_BELONG_GATEWAY.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyDeviceBelongGateway(verifyMethodParams, argsMap))
            .put(VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME.getMethodName(),
                    (verifyMethodParams, argsMap) -> verifyDeviceBelongHome(verifyMethodParams, argsMap))
            .build();


    public void verifyHomeOwner(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something

    }


    public void verifyHomeAdmin(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyHomeMember(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserManageDevice(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserControlDevice(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserManageRoom(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserQueryRoom(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserManageGroup(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserQueryGroup(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyUserManageScene(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyDeviceBelongGateway(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


    public void verifyDeviceBelongHome(AtopPermissionAuthParams verifyMethodParams, Map<String,Object> argsMap) {
        // do something
    }


}
