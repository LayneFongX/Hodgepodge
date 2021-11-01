package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyMethodParamsEnum {

    HOME_ID("homeId", "家庭ID"),

    UID("uid", "用户ID"),

    DEVICES("devices", "设备ID集合"),

    ROOMS("rooms", "房间ID集合"),

    GROUPS("groups", "群组ID集合"),

    SCENES("scenes", "场景ID集合"),

    GATEWAYID("gatewayId", "网关设备ID"),

    USER_ID("userId", "面板用户ID"),
    ;

    private String paramName;

    private String desc;


}
