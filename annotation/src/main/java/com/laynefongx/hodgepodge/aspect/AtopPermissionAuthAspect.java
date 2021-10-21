package com.laynefongx.hodgepodge.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.domain.AtopPermissionAuthMeta;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
import com.laynefongx.hodgepodge.utils.ClassUtils;
import com.laynefongx.hodgepodge.verify.VerifyBizService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Aspect
@Component
public class AtopPermissionAuthAspect {

    @Resource
    private VerifyBizService verifyBizService;

    /**
     * key为方法名称 value为该方法元数据
     */
    private static final Map<String, List<AtopPermissionAuthMeta>> permissionMetaMap = new ConcurrentHashMap<>();

    /**
     * 切面，定义拦截指定注解
     */
    @Pointcut(value = "execution(* com.laynefongx.hodgepodge..*.*(..))")
    // @Pointcut(value = "@annotation(com.tuya.europa.auth.annotation.AtopPermissionAuth)")
    private void pointcut() {
    }

    /**
     * 环绕切面
     *
     * @param joinPoint 切面点
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取切面方法
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method targetMethod = methodSignature.getMethod();
            Class<?> declaringClass = targetMethod.getDeclaringClass();
            String methodFullPathName = declaringClass.getName() + "." + targetMethod.getName();
            // 获取注解方法的元数据
            List<AtopPermissionAuthMeta> authMetaList = getPermissionAuthMeta(targetMethod);
            for (AtopPermissionAuthMeta authMeta : authMetaList) {
                Object[] args = joinPoint.getArgs();
                Map<String, Object> argsMap = getArgsMap(methodSignature, args);
                log.info("AtopPermissionAuthAspect doAround argsMap = {}", JSONObject.toJSONString(argsMap));
                VerifyMethodEnum methodsEnum = authMeta.getVerifyMethod();
                String methodName = methodsEnum.getMethodName();
                verifyBizService.matchVerifyMethodByMethodName(methodName, authMeta.getVerifyMethodParams(), argsMap);
            }
        } catch (Exception e) {
            log.warn("AtopPermissionAuthAspect doAround error", e);
        }
        return joinPoint.proceed();
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
        List<AtopPermissionAuthMeta> authMetaList = new ArrayList<>();
        List<AtopPermissionAuth> permissionAuthList = ClassUtils.getAnnotationBySource(method, AtopPermissionAuth.class);
        if (CollectionUtils.isEmpty(permissionAuthList)) {
            return authMetaList;
        }
        for (AtopPermissionAuth permissionAuth : permissionAuthList) {
            if (Objects.isNull(permissionAuth)) {
                continue;
            }
            AtopPermissionAuthMeta authMeta = new AtopPermissionAuthMeta();
            authMeta.setVerifyMethod(permissionAuth.method());
            authMeta.setVerifyMethodParams(permissionAuth.methodParams());
            log.info("AtopPermissionAuthAspect getPermissionAuthMeta method = {} , permissionAuth={}", method.toGenericString(),
                    JSON.toJSONString(permissionAuth));
            authMetaList.add(authMeta);
        }
        permissionMetaMap.putIfAbsent(method.toGenericString(), authMetaList);
        return authMetaList;
    }


    private Map<String, Object> getArgsMap(MethodSignature methodSignature, Object[] args) {
        Map<String, Object> argsMap = new HashMap<>();
        String[] parameterNames = methodSignature.getParameterNames();
        if (ArrayUtils.isEmpty(parameterNames)) {
            String methodName = methodSignature.getMethod().toGenericString();
            log.warn("AtopPermissionAuthAspect getArgsMap method = {}", methodName);
            return argsMap;
        }
        for (int i = 0; i < parameterNames.length; i++) {
            argsMap.put(parameterNames[i], args[i]);
        }
        return argsMap;
    }
}
