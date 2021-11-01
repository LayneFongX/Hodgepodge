package com.laynefongx.hodgepodge.domain;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParams;
import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * @author falcon
 * @since 2021/10/27
 */
@Data
public class AuthParameter{

  /**
   * 只校验请求上下文中的参数
   */
  String none;

  /**
   * 家庭ID
   */
  String homeId;

  /**
   * 用户ID
   */
  String uid;

  /**
   * 设备ID集合
   */
  String deviceId;

  /**
   * 房间ID集合
   */
  String roomId;

  /**
   * 群组ID集合
   */
  String groupId;

  /**
   * 场景ID集合
   */
  String sceneId;

  /**
   * 网关ID
   */
  String gatewayId;

  /**
   * 面板用户ID
   */
  String userId;

  public AtopPermissionAuthParams toAtopPermissionAuthParams(){
    return new AtopPermissionAuthParams(){
      @Override
      public String none() {
        return getNone();
      }

      @Override
      public String homeId() {
        return getHomeId();
      }

      @Override
      public String uid() {
        return getUid();
      }

      @Override
      public String deviceIds() {
        return getDeviceId();
      }

      @Override
      public String roomIds() {
        return getRoomId();
      }

      @Override
      public String groupIds() {
        return getGroupId();
      }

      @Override
      public String sceneIds() {
        return getSceneId();
      }

      @Override
      public String gatewayId() {
        return getGatewayId();
      }

      @Override
      public String userId() {
        return getUserId();
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return null;
      }
    };
  }
}
