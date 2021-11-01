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
public class VerifyUserManageSceneAction extends BaseAction{

  @Override
  public VerifyMethodEnum getAction() {
    return VerifyMethodEnum.VERIFY_USER_MANAGE_SCENE;
  }

  @Override
  public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                       String uid, ApiRequestDO apiRequestDO) {
    String sceneIds = (String) argsMap.get(verifyMethodParams.sceneIds());
    if (StringUtils.isBlank(sceneIds)){
      log.warn("valid param is empty sceneId={}", sceneIds);
      throw new RuntimeException("PARAM_ILLEGAL");
    }

    List<String> sceneIdList = Arrays.stream(sceneIds.split(REGEX)).collect(Collectors.toList());
    authBaseService.verifyUserManageScene(uid, sceneIdList);
  }
}
