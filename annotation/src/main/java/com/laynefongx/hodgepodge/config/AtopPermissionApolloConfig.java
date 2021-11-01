package com.laynefongx.hodgepodge.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class AtopPermissionApolloConfig {

    private static Map<String, String> permissionApolloConfigMap = new HashMap<>();

    /**
     * 是否开启用户家庭校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyUserHome() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.user.home.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启用户设备校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyUserDevice() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.user.device.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启用户房间校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyUserRoom() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.user.room.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启用户群组校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyUserGroup() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.user.group.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启用户场景校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyUserScene() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.user.scene.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启设备家庭校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyDeviceHome() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.device.home.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启用户家庭校验
     *
     * @return boolean the boolean
     */
    public Boolean enableVerifyDeviceGateway() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.device.gateway.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否校验用户所在业务类型校验
     *
     * @return 返回开关
     */
    public Boolean enableVerifyUserInBizType() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.user.in.biz.type", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启面板用户校验
     *
     * @return
     */
    public Boolean enableVerifyPannerUser() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.panner.user.verify", "true");
        return enable.equals("true");
    }

    /**
     * 获取方法白名单
     *
     * @return
     */
    public List<String> getWhiteMethods() {
        String methods = permissionApolloConfigMap.getOrDefault("verify.methods.white", "");
        return methods.equals("") ? Collections.emptyList() : Arrays.asList(methods.split(","));
    }

    /**
     * 是否开启设备分享
     */
    public boolean enableVerifyDeviceSharedUser() {
        String enable = permissionApolloConfigMap.getOrDefault("enable.device.shared.user.verify", "true");
        return enable.equals("true");
    }

    /**
     * 是否开启校验指定业务类型
     *
     * @param businessType
     * @return
     */
    public boolean enableVerifyBusiness(String businessType) {
        String enable = permissionApolloConfigMap.getOrDefault("enable.verify.business." + businessType, "true");
        return enable.equals("true");
    }
}
