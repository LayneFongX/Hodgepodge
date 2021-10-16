package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleTypeEnum {

    OWNER("OWNER", "家庭拥有者"),

    ADMIN("USER_DEVICE", "家庭管理员"),

    MEMBER("USER_ROOM", "家庭成员");

    private String code;

    private String desc;


}
