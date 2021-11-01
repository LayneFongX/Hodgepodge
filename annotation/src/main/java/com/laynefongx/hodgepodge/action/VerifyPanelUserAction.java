package com.laynefongx.hodgepodge.action;


import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.base.ApiRequestDO;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author falcon
 * @since 2021/10/29
 */
@Slf4j
@Component
public class VerifyPanelUserAction extends BaseAction {

  @Override
  public VerifyMethodEnum getAction() {
    return VerifyMethodEnum.VERIFY_PANEL_USER;
  }

  @Override
  public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                       String uid, ApiRequestDO apiRequestDO) {
    String userId = (String) argsMap.get(verifyMethodParams.userId());
    String deviceId = (String) argsMap.get(verifyMethodParams.deviceIds());
    if (StringUtils.isBlank(userId)) {
      log.warn("valid param is empty userId={} deviceId={} uid={}", userId, deviceId, uid);
      throw new RuntimeException("PARAM_ILLEGAL");
    }

    authBaseService.verifyPanelUser(userId, deviceId, uid);
  }
}
