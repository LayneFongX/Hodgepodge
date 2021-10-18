package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionParamFiledEnum {

    NONE("NONE", ""),

    HOME_ID("HOME_ID", "家庭ID"),

    UID("UID", "用户ID"),

    DEVICE_IDS("DEVICE_IDS", "设备ID集合"),

    ROOM_IDS("ROOM_IDS", "房间ID集合"),

    GROUP_IDS("GROUP_IDS", "群组ID集合"),

    SCENE_IDS("SCENE_IDS", "场景ID集合"),

    GATEWAY_ID("GATEWAY_ID", "网关设备ID"),

    RES_ID("RES_ID", "资源ID"),

    RES_TYPE("RES_TYPE", "资源类型"),
    ;

    private String code;

    private String desc;


}
