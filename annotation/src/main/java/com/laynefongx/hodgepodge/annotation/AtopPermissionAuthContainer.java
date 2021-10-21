package com.laynefongx.hodgepodge.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AtopPermissionAuthContainer {

    AtopPermissionAuth[] value();

}