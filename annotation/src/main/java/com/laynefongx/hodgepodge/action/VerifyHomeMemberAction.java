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
public class VerifyHomeMemberAction extends BaseAction{

  @Override
  public VerifyMethodEnum getAction() {
    return VerifyMethodEnum.VERIFY_HOME_MEMBER;
  }

  @Override
  public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                       String uid, ApiRequestDO apiRequestDO) {
    String homeId = (String) argsMap.get(verifyMethodParams.homeId());
    if (StringUtils.isBlank(homeId)){
      log.warn("valid param is empty homeId={}", homeId);
      log.warn("valid param is empty homeId={}", homeId);
      throw new RuntimeException("PARAM_ILLEGAL");
    }

    authBaseService.verifyHomeMember(uid,Long.valueOf(homeId));
  }
}
