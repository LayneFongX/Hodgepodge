package com.laynefongx.hodgepodge.action;



import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.base.ApiRequestDO;
import com.laynefongx.hodgepodge.service.IAtopPermissionAuthBaseService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author falcon
 * @since 2021/10/28
 */
public abstract class BaseAction implements AuthAction {

  public static final String REGEX = ",";

  @Resource
  public IAtopPermissionAuthBaseService authBaseService;

  @Override
  public void validAction(AtopPermissionAuthParams verifyMethodParams,
                          Map<String, Object> argsMap) {
    ApiRequestDO apiRequestDO = getRequestDO(argsMap);
    if (Objects.isNull(apiRequestDO)) {
      throw new RuntimeException("API_REQUEST_DO_NOT_EXIST");
    }

    String uid = apiRequestDO.getApiContextDo().getUid();
    validate(verifyMethodParams, argsMap, uid, apiRequestDO);
  }

  public abstract void validate(AtopPermissionAuthParams verifyMethodParams,
      Map<String, Object> argsMap, String uid, ApiRequestDO apiRequestDO);

  public ApiRequestDO getRequestDO(Map<String, Object> argsMap) {
    for (Map.Entry<String, Object> entry : argsMap.entrySet()) {
      Object arg = entry.getValue();
      if (arg instanceof ApiRequestDO) {
        return (ApiRequestDO) arg;
      }
    }
    return null;
  }
}
