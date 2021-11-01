package com.laynefongx.hodgepodge.service;


import com.laynefongx.hodgepodge.action.AuthAction;
import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.config.AtopPermissionApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
public class VerifyBizService {

    private Map<String, AuthAction> authActions;

    @Resource
    private AtopPermissionApolloConfig apolloConfig;

    public VerifyBizService(List<AuthAction> authActions) {
        this.authActions = authActions.stream().collect(Collectors.toMap(authAction ->
                authAction.getAction().getMethodName(), authAction -> authAction));
    }

    public void matchVerifyMethodByMethodName(String methodName, String businessType, AtopPermissionAuthParams verifyMethodParams,
                                              Map<String, Object> argsMap) {
        // 获取切面方法
        try {
            if (!apolloConfig.enableVerifyBusiness(businessType)) {
                log.info("VerifyBizService matchVerifyMethodByMethodName enableVerifyBusiness = {},businessType = {}", Boolean.FALSE,
                        businessType);
                return;
            }
            log.info("VerifyBizService matchVerifyMethodByMethodName enableVerifyBusiness = {},businessType = {}", Boolean.TRUE,
                    businessType);
            AuthAction authAction = authActions.get(methodName);
            if (Objects.isNull(authAction)) {
                throw new RuntimeException("UNKNOWN_AUTH_TYPE");
            }
            // 根据业务类型校验 此处需要获取apollo配置 默认是全部关闭
            authAction.validAction(verifyMethodParams, argsMap);
        } catch (Exception e) {
            throw new RuntimeException("PERMISSION_VERIFY_ERROR");
        }
    }

}
