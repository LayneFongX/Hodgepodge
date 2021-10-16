package com.laynefongx.hodgepodge.domain;

import com.laynefongx.hodgepodge.enums.PermissionTypeEnum;
import com.laynefongx.hodgepodge.enums.UserRoleTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
public class AtopPermissionAuthMeta {

    private PermissionTypeEnum permissionType;

    /**
     * 角色code（多个逗号分隔）
     * 说明：Inox项目暂时没有用到，后期可能用到
     */
    private List<UserRoleTypeEnum> roleTypes;


    /**
     * 授权参数信息,Map<目标参数,方法中参数名>
     *
     * @return 返回授权参数列表
     */
    private Map<String, String> authParams;
}
