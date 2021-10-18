package com.laynefongx.hodgepodge.domain;

import com.laynefongx.hodgepodge.enums.VerifyMethodParamsEnum;
import com.laynefongx.hodgepodge.enums.VerifyMethodsEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
public class AtopPermissionAuthMeta {

    /**
     * 要调用的校验方法信息
     */
    private List<VerifyMethodsEnum> verifyMethodsList;

    /**
     * 是否从公共参数中获取信息 目前是获取uid和gid
     */
    private Boolean isParseApiRequestDO;

    /**
     * 授权参数信息,Map<参数名称,映射的校验方法中的参数名称>
     *
     * @return 返回授权参数列表
     */
    private List<VerifyMethodParamsEnum> verifyMethodParamsList;
}
