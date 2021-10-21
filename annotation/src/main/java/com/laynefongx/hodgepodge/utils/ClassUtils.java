package com.laynefongx.hodgepodge.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ClassUtils {

    /**
     * 获取方法继承接口方法上的注解
     *
     * @param method 方法
     * @return 返回注解
     */
    public static <T extends Annotation> List<T> getAnnotationBySource(Method method, Class<T> type) {

        //获取方法上注解配置
        T[] annotations = method.getDeclaredAnnotationsByType(type);
        if (ObjectUtils.isNotEmpty(annotations)) {
            return List.of(annotations);
        }

        //获取接口上的注解配置
        Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            try {
                Method interfaceMethod = anInterface.getDeclaredMethod(method.getName(), method.getParameterTypes());
                if (ObjectUtils.isEmpty(interfaceMethod)) {
                    continue;
                }
                return List.of(interfaceMethod.getDeclaredAnnotationsByType(type));
            } catch (Exception ex) {
                log.warn("ClassUtils getAnnotationBySource catch Exception", ex);
            }
        }
        return null;
    }

}
