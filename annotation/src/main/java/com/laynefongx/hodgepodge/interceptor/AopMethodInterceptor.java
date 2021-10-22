package com.laynefongx.hodgepodge.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.domain.AtopPermissionAuthMeta;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
import com.laynefongx.hodgepodge.utils.ClassUtils;
import com.laynefongx.hodgepodge.verify.VerifyBizService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author falcon
 * @since 2021/10/22
 */
@Slf4j
public class AopMethodInterceptor implements MethodInterceptor {

    @Resource
    private VerifyBizService verifyBizService;


    /**
     * key为方法名称 value为该方法元数据
     */
    private static final Map<String, List<AtopPermissionAuthMeta>> permissionMetaMap = new ConcurrentHashMap<>();


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        String methodFullPathName = declaringClass.getName() + "." + method.getName();
        // 获取注解方法的元数据
        List<AtopPermissionAuthMeta> authMetaList = getPermissionAuthMeta(method);
        for (AtopPermissionAuthMeta authMeta : authMetaList) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters){
                System.out.println(parameter.getName());
            }
            // Map<String, Object> argsMap = getArgsMap(methodSignature, parameters);
            // log.info("AtopPermissionAuthAspect doAround argsMap = {}", JSONObject.toJSONString(argsMap));
            // VerifyMethodEnum methodsEnum = authMeta.getVerifyMethod();
            // String methodName = methodsEnum.getMethodName();
            // verifyBizService.matchVerifyMethodByMethodName(methodName, authMeta.getVerifyMethodParams(), argsMap);
        }
        log.info("start interceptor {}", methodInvocation.getMethod().getName());
        return methodInvocation.proceed();
    }

    /**
     * 获取和初始化权限配置
     *
     * @param method 切面方法
     * @return 返回权限配置
     */
    private List<AtopPermissionAuthMeta> getPermissionAuthMeta(Method method) {
        if (permissionMetaMap.containsKey(method.toGenericString())) {
            return permissionMetaMap.get(method.toGenericString());
        }

        List<AtopPermissionAuth> permissionAuthList = ClassUtils.getAnnotationBySource(method, AtopPermissionAuth.class);
        if (CollectionUtils.isEmpty(permissionAuthList)) {
            return Collections.EMPTY_LIST;
        }

        List<AtopPermissionAuthMeta> authMetaList = new ArrayList<>();
        for (AtopPermissionAuth permissionAuth : permissionAuthList) {
            if (Objects.isNull(permissionAuth)) {
                continue;
            }
            AtopPermissionAuthMeta authMeta = new AtopPermissionAuthMeta();
            authMeta.setVerifyMethod(permissionAuth.method());
            authMeta.setVerifyMethodParams(permissionAuth.methodParams());
            authMetaList.add(authMeta);
        }
        permissionMetaMap.putIfAbsent(method.toGenericString(), authMetaList);
        return authMetaList;
    }


    private Map<String, Object> getArgsMap(MethodSignature methodSignature, Object[] args) {
        Map<String, Object> argsMap = new HashMap<>();
        String[] parameterNames = methodSignature.getParameterNames();
        if (ArrayUtils.isEmpty(parameterNames)) {
            return argsMap;
        }
        for (int i = 0; i < parameterNames.length; i++) {
            argsMap.put(parameterNames[i], args[i]);
        }
        return argsMap;
    }
}
