package com.laynefongx.hodgepodge.aspect;

import com.alibaba.fastjson.JSON;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuth;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParam;
import com.laynefongx.hodgepodge.domain.AtopPermissionAuthMeta;
import com.laynefongx.hodgepodge.enums.PermissionTypeEnum;
import com.laynefongx.hodgepodge.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
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
@Order(10)
public class AtopPermissionAuthAspect {

    /**
     * 静态方法权限配置元数据
     */
    private static final Map<String, AtopPermissionAuthMeta> methodPermissionMeta = new ConcurrentHashMap<>();

    /**
     * 切面，定义(只拦截 @AuthCheck 注释的函数)
     */
    @Pointcut(value = "execution(* com.laynefongx.hodgepodge.*.controller..*.*(..))")
    private void permissionCheckCut() {
    }

    /**
     * 环绕切面
     *
     * @param joinPoint 切面点
     */
    @Around("permissionCheckCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取切面方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        //获取授权注解：不存在/权限校验规则为None/权限Code编码为空将不校验
        AtopPermissionAuthMeta AtopPermissionAuthMeta = getAndInitAuthPermission(targetMethod);
        // if (ObjectUtils.isEmpty(AtopPermissionAuthMeta)
        //     || AtopPermissionAuthMeta.getPermissionType().equals(AuthPermissionType.NONE)
        //     || CollectionUtils.isEmpty(AtopPermissionAuthMeta.getPermissionCodes())
        //     || AtopPermissionAuthMeta.getPermissionCodes().size() <= 0) {
        //     log.info("method no authPermission config,method={}", targetMethod.toGenericString());
        //     return joinPoint.proceed();
        // }

        //获取参数映射
        Map<String, String> params = getAuthPermissionParam(methodSignature, joinPoint.getArgs(), AtopPermissionAuthMeta);
        log.info("params:{}", JSON.toJSON(params));

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
     * @param methodSignature        方法
     * @param AtopPermissionAuthMeta 授权
     * @return 返回授权权限参数信息
     */
    private Map<String, String> getAuthPermissionParam(MethodSignature methodSignature, Object[] args,
                                                       AtopPermissionAuthMeta AtopPermissionAuthMeta) {
        Map<String, String> result = new HashMap<>();

        //获取方法参数
        String[] parameterNames = methodSignature.getParameterNames();
        if (parameterNames == null || parameterNames.length <= 0) {
            log.warn("getAuthPermissionParam parameters is null,method={}", methodSignature.getMethod().toGenericString());
            return result;
        }

        //获取参数
        for (Map.Entry<String, String> authParam : AtopPermissionAuthMeta.getAuthParams().entrySet()) {

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
    private AtopPermissionAuthMeta getAndInitAuthPermission(Method method) {
        //获取缓存
        if (methodPermissionMeta.containsKey(method.toGenericString())) {
            return methodPermissionMeta.get(method.toGenericString());
        }

        //在源码中直接获取
        AtopPermissionAuthMeta result = new AtopPermissionAuthMeta();
        AtopPermissionAuth authPermission = ClassUtils.getAnnotationBySource(method, AtopPermissionAuth.class);
        if (ObjectUtils.isEmpty(authPermission)) {
            result.setPermissionType(PermissionTypeEnum.USER_HOME);
        } else {
            result.setPermissionType(authPermission.permissionType());
            result.setRoleTypes(Arrays.asList(authPermission.userRoleTypes()));
            result.setAuthParams(Arrays.stream(authPermission.authParams())
                    .collect(Collectors.toMap(AtopPermissionAuthParam::name, AtopPermissionAuthParam::methodParamName)));
        }
        methodPermissionMeta.putIfAbsent(method.toGenericString(), result);

        log.info("get method annotation method={} annotation={}", method.toGenericString(), JSON.toJSONString(authPermission));
        return result;
    }

}