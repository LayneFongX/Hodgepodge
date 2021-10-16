package com.laynefongx.hodgepodge.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuthParam {

    /**
     * 权限校验-参数注解
     */
    String name() default "";

    /**
     * 参数在方法入参名字
     */
    String methodParamName() default "";

    /**
     * 入参分隔符- _ |
     *
     */
    String methodSplit() default "";

}
