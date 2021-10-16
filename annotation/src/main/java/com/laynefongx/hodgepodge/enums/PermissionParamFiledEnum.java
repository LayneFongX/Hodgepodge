package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionParamFiledEnum {

    HOME_ID("HOME_ID", "家庭ID"),

    GROUP_ID("GROUP_ID", "群组ID"),

    DEVICE_ID("DEVICE_ID", "设备ID"),
    ;

    private String code;

    private String desc;


}
