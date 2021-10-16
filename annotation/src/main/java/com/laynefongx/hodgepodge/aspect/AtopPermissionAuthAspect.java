package com.laynefongx.hodgepodge.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParam;
import com.laynefongx.hodgepodge.domain.AtopPermissionAuthMeta;
import com.laynefongx.hodgepodge.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class AtopPermissionAuthAspect {

    /**
     * 静态方法权限配置元数据
     */
    private static final Map<String, AtopPermissionAuthMeta> methodPermissionMeta = new ConcurrentHashMap<>();

    /**
     * 切面，定义拦截指定注解
     */
    @Pointcut(value = "@annotation(com.laynefongx.hodgepodge.annotation.AtopPermissionAuth)")
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
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        // 获取注解方法的元数据
        AtopPermissionAuthMeta authMeta = getPermissionAuthMeta(targetMethod);
        //获取参数映射
        Map<String, String> params = getPermissionAuthParams(methodSignature, joinPoint.getArgs(), authMeta);
        log.info("AtopPermissionAuthAspect doAround params:{}", JSONObject.toJSONString(params));

        // //获取权限校验服务
        // IAuthService authService = authServiceMap.get(AtopPermissionAuthMeta.getPermissionType().getAuthServiceName());
        // if (ObjectUtils.isEmpty(authService)) {
        //     log.warn("method no authPermission,method={}", targetMethod.toGenericString());
        //     throw new SpaceException(SpaceErrorCodeEnum.PERMISSION_DENIED);
        // }
        //
        // String testPermission = apolloConfigurator.getStringConfigData("test.permission");
        // if (testPermission.equals("open")) {
        //     //权限开始校验--如参数校验
        //     if (Boolean.FALSE.equals(authService.beforeCheck(params))) {
        //         log.warn("IAuthService validate before check is false,method={},AtopPermissionAuthMeta={},param={}",
        //             targetMethod.toGenericString(), AtopPermissionAuthMeta.toString(), JSON.toJSON(params));
        //         throw new SpaceException(SpaceErrorCodeEnum.PERMISSION_DENIED);
        //     }
        //
        //     //权限开始校验--权限校验
        //     if (Boolean.FALSE.equals(authService.permissionCheck(AtopPermissionAuthMeta, params))) {
        //         log.warn("IAuthService validate permission check is false,method={},AtopPermissionAuthMeta={},param={}",
        //             targetMethod.toGenericString(), AtopPermissionAuthMeta.toString(), JSON.toJSON(params));
        //         throw new SpaceException(SpaceErrorCodeEnum.PERMISSION_DENIED);
        //     }
        // }
        return joinPoint.proceed();
    }

    /**
     * 获取授权参数信息
     *
     * @param methodSignature 方法
     * @param authMeta        授权元数据
     * @return 返回授权权限参数信息
     */
    private Map<String, String> getPermissionAuthParams(MethodSignature methodSignature, Object[] args, AtopPermissionAuthMeta authMeta) {
        Map<String, String> result = new HashMap<>();
        //获取方法参数
        String[] parameterNames = methodSignature.getParameterNames();
        if (parameterNames == null || parameterNames.length <= 0) {
            log.warn("getPermissionAuthParams parameters is null,method={}", methodSignature.getMethod().toGenericString());
            return result;
        }

        //获取参数
        for (Map.Entry<String, String> authParam : authMeta.getAuthParams().entrySet()) {
            int paramIndex = ArrayUtils.indexOf(parameterNames, authParam.getValue());
            if (paramIndex < 0 || paramIndex >= args.length) {
                continue;
            }
            result.putIfAbsent(authParam.getKey(), String.valueOf(args[paramIndex]));
        }
        return result;
    }

    /**
     * 获取和初始化权限配置
     *
     * @param method 切面方法
     * @return 返回权限配置
     */
    private AtopPermissionAuthMeta getPermissionAuthMeta(Method method) {
        //获取缓存
        if (methodPermissionMeta.containsKey(method.toGenericString())) {
            return methodPermissionMeta.get(method.toGenericString());
        }
        AtopPermissionAuthMeta result = new AtopPermissionAuthMeta();
        AtopPermissionAuth authPermission = ClassUtils.getAnnotationBySource(method, AtopPermissionAuth.class);
        result.setPermissionType(authPermission.permissionType());
        result.setRoleTypes(Arrays.asList(authPermission.userRoleTypes()));
        result.setAuthParams(Arrays.stream(authPermission.authParams()).collect(Collectors.toMap(AtopPermissionAuthParam::name,
                AtopPermissionAuthParam::methodParamName)));
        methodPermissionMeta.putIfAbsent(method.toGenericString(), result);
        log.info("get method annotation method={} annotation={}", method.toGenericString(), JSON.toJSONString(authPermission));
        return result;
    }

}
