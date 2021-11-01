package com.laynefongx.hodgepodge.service;

import com.laynefongx.hodgepodge.domain.GroupUserBizVO;

import java.util.List;

/**
 * @author falcon
 * @since 2021/10/14
 */
public interface IAtopPermissionAuthBaseService {

    /**
     * 获取用户在家庭的角色信息 :
     * 0: 家庭普通成员
     * 1: 家庭管理员
     * 2: 家庭拥有者
     * status: 用户是否有效
     *
     * @param ownerId 家庭Id
     * @param uid     用户Id
     * @return
     */
    GroupUserBizVO getUserRoleBelongHome(String uid, Long ownerId);


    /**
     * 校验用户是否是家庭的owner
     *
     * @param uid     用户Id
     * @param ownerId 家庭Id
     * @return
     */
    void verifyHomeOwner(String uid, Long ownerId);

    /**
     * 校验用户是否是家庭的管理员
     *
     * @param uid     用户Id
     * @param ownerId 家庭Id
     * @return
     */
    void verifyHomeAdmin(String uid, Long ownerId);

    /**
     * 校验用户是否是家庭的成员
     *
     * @param uid     用户Id
     * @param ownerId 家庭Id
     * @return
     */
    void verifyHomeMember(String uid, Long ownerId);

    /**
     * 校验用户对设备是否有管理权限(移动，解绑，移除等)
     *
     * @param uid       用户Id
     * @param deviceIds 设备Id列表
     * @return
     */
    void verifyUserManageDevice(String uid, List<String> deviceIds);

    /**
     * 校验用户是否可对设备的查看和dp下发权限
     *
     * @param uid       用户Id
     * @param deviceIds 设备列表
     * @return
     */
    void verifyUserControlDevice(String uid, List<String> deviceIds);


    /**
     * 校验用户是否可对设备的查看和dp下发权限
     *
     * @param uid       用户Id
     * @param deviceIds 设备列表
     * @return
     */
    void verifyDeviceShared2User(String uid, List<String> deviceIds);

    /**
     * 校验用户是否有权限管理房间
     *
     * @param uid     用户Id
     * @param roomIds 房间Id
     * @return
     */
    void verifyUserManageRoom(String uid, List<Long> roomIds);

    /**
     * 校验是否是可以查看房间
     *
     * @param uid     用户Id
     * @param roomIds 房间Id
     * @return
     */
    void verifyUserQueryRoom(String uid, List<Long> roomIds);

    /**
     * 校验用户是否有权限管理群组
     *
     * @param uid      用户Id
     * @param groupIds 群组Id
     * @return
     */
    void verifyUserManageGroup(String uid, List<Long> groupIds);

    /**
     * 校验是否有权限查看群组
     *
     * @param uid      用户Id
     * @param groupIds 群组Id
     * @return
     */
    void verifyUserQueryGroup(String uid, List<Long> groupIds);

    /**
     * 校验用户是否能操作场景
     *
     * @param uid      用户Id
     * @param sceneIds 场景Id
     * @return
     */
    void verifyUserManageScene(String uid, List<String> sceneIds);

    /**
     * 校验设备是否属于网关
     *
     * @param gatewayId 网关Id
     * @param deviceIds 设备Id
     * @return
     */
    void verifyDeviceBelongGateway(String gatewayId, List<String> deviceIds);

    /**
     * 校验设备是否属于家庭
     *
     * @param ownerId   家庭Id
     * @param deviceIds 设备id列表
     * @return
     */
    void verifyDeviceBelongHome(String ownerId, List<String> deviceIds);

    /**
     * 验证用户是否在bizType中
     * PS：针对kv存储与虚拟用户存储场景，权限不是很好控制，只要在一个业务类型下即可操作
     *
     * @param uid     用户Id
     * @param bizType 业务类型
     */
    void verifyUserInBizType(String uid, Integer bizType);

    /**
     * 校验面板用户
     *
     * @param userId   面板用户Id
     * @param deviceId 设备id
     * @param uid      APP登录用户id
     * @return
     */
    void verifyPanelUser(String userId, String deviceId, String uid);

    /**
     * 根据resid和restype校验用户是否具有操作(创建/删除等)权限
     *
     * @param resId
     * @param resType
     * @param uid
     */
    void verifyManageDeviceOrGroupByResType(String resId, Integer resType, String uid);


    /**
     * 根据resid和restype校验用户是否具有查看权限
     *
     * @param resId
     * @param resType
     * @param uid
     */
    void verifyControlDeviceOrGroupByResType(String resId, Integer resType, String uid);
}
