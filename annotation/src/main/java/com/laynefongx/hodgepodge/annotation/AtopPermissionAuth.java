package com.laynefongx.hodgepodge.annotation;

import com.laynefongx.hodgepodge.enums.PermissionTypeEnum;
import com.laynefongx.hodgepodge.enums.UserRoleTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuth {

    PermissionTypeEnum permissionType() default PermissionTypeEnum.USER_HOME;

    /**
     * 用户角色code,任意角色通过校验即可
     */
    UserRoleTypeEnum[] userRoleTypes() default {};

    /**
     * 需要校验的参数信息
     *
     * @return 返回授权参数列表
     */
    AtopPermissionAuthParam[] authParams() default {};

}