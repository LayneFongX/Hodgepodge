package com.laynefongx.hodgepodge.annotation;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuthParams {

    /**
     * 只校验请求上下文中的参数
     *
     * @return
     */
    String none() default StringUtils.EMPTY;

    /**
     * 家庭ID
     */
    String homeId() default StringUtils.EMPTY;

    /**
     * 用户ID
     */
    String uid() default StringUtils.EMPTY;

    /**
     * 设备ID集合
     */
    String deviceIds() default StringUtils.EMPTY;

    /**
     * 房间ID集合
     */
    String roomIds() default StringUtils.EMPTY;

    /**
     * 群组ID集合
     */
    String groupIds() default StringUtils.EMPTY;

    /**
     * 场景ID集合
     */
    String sceneIds() default StringUtils.EMPTY;

    /**
     * 网关ID
     */
    String gatewayId() default StringUtils.EMPTY;

    /**
     * 面板用户ID
     */
    String userId() default StringUtils.EMPTY;

}
