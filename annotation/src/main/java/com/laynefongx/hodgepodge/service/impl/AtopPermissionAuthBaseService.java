package com.laynefongx.hodgepodge.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.domain.GroupUserBizVO;
import com.laynefongx.hodgepodge.service.IAtopPermissionAuthBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtopPermissionAuthBaseService implements IAtopPermissionAuthBaseService {

    @Resource
    private OwnerHandleService ownerHandleService;


    @Override
    public GroupUserBizVO getUserRoleBelongHome(String uid, Long ownerId) {
        return null;
    }

    @Override
    public void verifyHomeOwner(String uid, Long ownerId) {

    }

    @Override
    public void verifyHomeAdmin(String uid, Long ownerId) {

    }

    @Override
    public void verifyHomeMember(String uid, Long ownerId) {

    }

    /**
     * 校验用户对设备是否有管理权限(移动，解绑，移除等)
     *
     * @param uid       用户Id
     * @param deviceIds 设备Id列表
     */
    @Override
    public void verifyUserManageDevice(String uid, List<String> deviceIds) {

    }

    @Override
    public void verifyUserControlDevice(String uid, List<String> deviceIds) {

    }

    @Override
    public void verifyDeviceShared2User(String uid, List<String> deviceIds) {

    }

    @Override
    public void verifyUserManageRoom(String uid, List<Long> roomIds) {

    }

    @Override
    public void verifyUserQueryRoom(String uid, List<Long> roomIds) {

    }

    @Override
    public void verifyUserManageGroup(String uid, List<Long> groupIds) {

    }

    @Override
    public void verifyUserQueryGroup(String uid, List<Long> groupIds) {

    }

    @Override
    public void verifyUserManageScene(String uid, List<String> sceneIds) {

    }

    @Override
    public void verifyDeviceBelongGateway(String gatewayId, List<String> deviceIds) {

    }

    @Override
    public void verifyDeviceBelongHome(String ownerId, List<String> deviceIds) {

    }

    @Override
    public void verifyUserInBizType(String uid, Integer bizType) {

    }

    @Override
    public void verifyPanelUser(String userId, String deviceId, String uid) {

    }

    @Override
    public void verifyManageDeviceOrGroupByResType(String resId, Integer resType, String uid) {

    }

    @Override
    public void verifyControlDeviceOrGroupByResType(String resId, Integer resType, String uid) {

    }
}
