package com.laynefongx.hodgepodge.annotation;

import com.laynefongx.hodgepodge.enums.VerifyMethodParamsEnum;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuthParam {

    /**
     * 参数在方法入参名字
     */
    String methodParamName() default "";

    /**
     * 参数对应的校验方法的参数映射
     *
     * @return
     */
    String paramMapping() default "";

}
