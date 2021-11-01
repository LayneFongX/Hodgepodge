package com.laynefongx.hodgepodge.service.impl;

import com.tuya.asgard.client.domain.device.vo.DeviceGroupRsVO;
import com.tuya.asgard.client.domain.relation.vo.RoomVOV2;
import com.tuya.asgard.client.service.room.IRoomMService;
import com.tuya.athena.client.domain.device.DeviceRsVO;
import com.tuya.europa.common.exception.EuropaException;
import com.tuya.europa.common.exception.errorcode.EuropaErrorCode;
import com.tuya.europa.integration.device.IDeviceAdaptService;
import com.tuya.europa.integration.device.IDeviceGroupAdaptService;
import com.tuya.europa.integration.permissauth.service.IAtopPermissAuthAdaptService;
import com.tuya.jupiter.client.domain.linkage.vo.LinkageRuleVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author falcon
 * @since 2021/10/15
 */
@Service
public class OwnerHandleService {

  public static final int ZERO_INDEX = 0;

  @Resource
  private IAtopPermissAuthAdaptService authAdaptService;

  @Resource
  private IRoomMService roomMService;

  @Resource
  private IDeviceAdaptService deviceAdaptService;

  @Resource
  private IDeviceGroupAdaptService deviceGroupAdaptService;

  public String getOwnerIdByDevices(List<String> deviceIds) {
    // 获取设备详情,比对设备所属家庭和当前家庭是否为同一家庭
    List<DeviceRsVO> deviceRsVOList = deviceAdaptService.getRsByIds(deviceIds);
    if (CollectionUtils.isEmpty(deviceRsVOList)) {
      throw new EuropaException(EuropaErrorCode.DEVICE_NOT_EXIST);
    }

    List<String> deviceOwnerIdList = deviceRsVOList.stream().map(DeviceRsVO::getOwnerId).distinct()
        .collect(Collectors.toList());
    return filterOwnerId(deviceOwnerIdList);

  }

  public String getOwnerByRooms(List<Long> rooms) {
    List<RoomVOV2> roomList = roomMService.getRoomsByIds(rooms);
    if (CollectionUtils.isEmpty(roomList)) {
      throw new EuropaException(EuropaErrorCode.ROOM_NOT_EXIST);
    }

    List<String> ownerList = roomList.stream().map(RoomVOV2::getOwnerId).distinct()
        .collect(Collectors.toList());
    return filterOwnerId(ownerList);
  }

  public String getOwnerByGroups(List<Long> groups) {
    List<DeviceGroupRsVO> groupList = deviceGroupAdaptService.getRsByIds(groups);
    if (CollectionUtils.isEmpty(groupList)) {
      throw new EuropaException(EuropaErrorCode.ROOM_NOT_EXIST);
    }

    List<String> ownerList = groupList.stream().map(DeviceGroupRsVO::getOwnerId).distinct()
        .collect(Collectors.toList());
    return filterOwnerId(ownerList);
  }

  public String getOwnerByScenes(List<String> sceneIds) {
    List<LinkageRuleVO> sceneList = authAdaptService.getSceneList(sceneIds);
    if (CollectionUtils.isEmpty(sceneList)) {
      throw new EuropaException(EuropaErrorCode.SCENE_NOT_EXIST);
    }

    List<String> ownerList = sceneList.stream().map(LinkageRuleVO::getOwnerId).distinct()
        .collect(Collectors.toList());
    return filterOwnerId(ownerList);
  }

  public String filterOwnerId(List<String> ownerList) {
    if (CollectionUtils.isEmpty(ownerList) || ownerList.size() > 1) {
      // 家庭ID为空或者家庭ID大于1 说明跨家庭操作 这种操作是不被允许的
      throw new EuropaException(EuropaErrorCode.DEVICE_ACROSS_GROUP);
    }

    return ownerList.get(ZERO_INDEX);
  }
}
