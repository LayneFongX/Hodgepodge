package com.laynefongx.hodgepodge.interceptor;


import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author falcon
 * @since 2021/10/22
 */
public class MethodPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

  @Override
  public boolean matches(Method method, Class<?> aClass) {
      if(method.getAnnotationsByType(AtopPermissionAuth.class).length > 0){
        return true;
      }

      // 该方法有对应的接口方法，且接口方法上加了注解
      Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
      for (int i = 0; i < interfaces.length; i++) {
        Method[] methods = interfaces[i].getMethods();
        for (int j = 0; j < methods.length; j++) {
          if(methods[j].getName().equals(method.getName())){
              AtopPermissionAuth[] annotationsByType = methods[j].getAnnotationsByType(AtopPermissionAuth.class);
              return annotationsByType.length > 0 ;
          }
        }
      }
      return false;
  }
}
