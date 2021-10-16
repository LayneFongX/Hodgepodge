package com.laynefongx.hodgepodge.utils;

import org.apache.commons.lang3.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ClassUtils {

    /**
     * 获取方法继承接口方法上的注解
     *
     * @param method 方法
     * @return 返回注解
     */
    public static <T extends Annotation> T getAnnotationBySource(Method method, Class<T> type) {

        //获取方法上注解配置
        T annotation = method.getDeclaredAnnotation(type);
        if (ObjectUtils.isNotEmpty(annotation)) {
            return annotation;
        }

        //获取接口上的注解配置
        Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            try {
                Method interfaceMethod = anInterface.getDeclaredMethod(method.getName(), method.getParameterTypes());
                if (ObjectUtils.isEmpty(interfaceMethod)) {
                    continue;
                }
                return interfaceMethod.getDeclaredAnnotation(type);
            } catch (Exception ex) {
                annotation = null;
            }
        }

        return annotation;
    }

}
