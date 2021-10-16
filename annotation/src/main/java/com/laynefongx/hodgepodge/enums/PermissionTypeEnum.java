package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionTypeEnum {

    USER_HOME("USER_HOME", "用户家庭"),

    USER_DEVICE("USER_DEVICE", "用户设备"),

    USER_ROOM("USER_ROOM", "用户房间"),

    USER_GROUP("USER_GROUP", "用户群组"),

    USER_SCENE("USER_SCENE", "用户场景"),

    GATEWAY_DEVICE("GATEWAY_DEVICE", "网关设备");


    private String code;

    private String desc;



}
