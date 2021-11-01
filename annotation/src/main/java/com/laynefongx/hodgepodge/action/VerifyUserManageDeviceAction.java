package com.laynefongx.hodgepodge.action;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.base.ApiRequestDO;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author falcon
 * @since 2021/10/29
 */
@Slf4j
@Component
public class VerifyUserManageDeviceAction extends BaseAction{

  @Override
  public VerifyMethodEnum getAction() {
    return VerifyMethodEnum.VERIFY_USER_MANAGE_DEVICE;
  }

  @Override
  public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                       String uid, ApiRequestDO apiRequestDO) {
    String deviceIds = (String) argsMap.get(verifyMethodParams.deviceIds());
    if (StringUtils.isBlank(deviceIds)){
      log.warn("valid param is empty deviceId={}", deviceIds);
      throw new RuntimeException("PARAM_ILLEGAL");
    }

    List<String> deviceIdList = Arrays.stream(deviceIds.split(REGEX)).collect(Collectors.toList());
    authBaseService.verifyUserManageDevice(uid, deviceIdList);
  }
}
