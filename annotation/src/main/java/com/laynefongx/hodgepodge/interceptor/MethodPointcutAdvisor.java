package com.laynefongx.hodgepodge.interceptor;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author falcon
 * @since 2021/10/22
 */
public class MethodPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

  @Override
  public boolean matches(Method method, Class<?> aClass) {
      if (method.isAnnotationPresent(AtopPermissionAuth.class)) {
          return true;
      }

      // 该方法有对应的接口方法，且接口方法上加了注解
      Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
      for (Class<?> anInterface : interfaces) {
          Method[] methods = anInterface.getMethods();
          for (Method value : methods) {
              if (value.getName().equals(method.getName())) {
                  return value.isAnnotationPresent(AtopPermissionAuth.class);
              }
          }
      }
      return false;
  }
}
