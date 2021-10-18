package com.laynefongx.hodgepodge.annotation;

import com.laynefongx.hodgepodge.enums.VerifyMethodParamsEnum;
import com.laynefongx.hodgepodge.enums.VerifyMethodsEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuth {

    /**
     * 要调用的校验方法
     *
     * @return
     */
    VerifyMethodsEnum[] methods() default {};

    /**
     * 是否解析公共参数 目前是在公共参数中获取用户ID和家庭ID
     * @return
     */
    boolean isParseApiRequestDO() default false;

    /**
     * 需要校验的参数信息
     *
     * @return 返回授权参数列表
     */
    VerifyMethodParamsEnum[] methodParams() default {};

}