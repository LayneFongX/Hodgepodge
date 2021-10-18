package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyMethodEnum {

    VERIFY_HOME_OWNER("verifyHomeOwner", "校验用户是否是家庭的owner"),

    VERIFY_HOME_ADMIN("verifyHomeAdmin", "校验用户是否是家庭的管理员"),

    VERIFY_HOME_MEMBER("verifyHomeMember", "校验用户是否是家庭的成员"),

    VERIFY_USER_MANAGE_DEVICE("verifyUserManageDevice", "校验用户对设备是否有管理权限(移动，解绑，移除等)"),

    VERIFY_USER_CONTROL_DEVICE("verifyUserControlDevice", "校验用户是否可对设备的查看和dp下发权限"),

    VERIFY_USER_MANAGE_ROOM("verifyUserManageRoom", "校验用户是否有权限管理房间"),

    VERIFY_USER_QUERY_ROOM("verifyUserQueryRoom", "校验是否是可以查看房间"),

    VERIFY_USER_MANAGE_GROUP("verifyUserManageGroup", "校验用户是否有权限管理群组"),

    VERIFY_USER_QUERY_GROUP("verifyUserQueryGroup", "校验是否有权限查看群组"),

    VERIFY_USER_MANAGE_SCENE("verifyUserManageScene", "校验用户是否能操作场景"),

    VERIFY_DEVICE_BELONG_GATEWAY("verifyDeviceBelongGateway", "校验设备是否属于网关"),

    VERIFY_DEVICE_BELONG_HOME("verifyDeviceBelongHome", "校验设备是否属于家庭"),
    ;

    private String methodName;

    private String desc;


}
