package com.laynefongx.hodgepodge.action;


import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;

import java.util.Map;

/**
 * @author falcon
 * @since 2021/10/28
 */
public interface AuthAction {

  /**
   * 获取检验类型
   * */
  VerifyMethodEnum getAction();

  /**
   * 检验权限类型
   * */
  void validAction(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap);
}
