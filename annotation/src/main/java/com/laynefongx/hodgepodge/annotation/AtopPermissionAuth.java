package com.laynefongx.hodgepodge.annotation;

import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Repeatable(AtopPermissionAuthContainer.class)
public @interface AtopPermissionAuth {

    /**
     * 要调用的校验方法
     *
     * @return
     */
    VerifyMethodEnum method();

    /**
     * 需要校验的参数信息
     *
     * @return 返回授权参数列表
     */
    AtopPermissionAuthParams methodParams();

}