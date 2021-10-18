package com.laynefongx.hodgepodge.domain;

import com.laynefongx.hodgepodge.annotation.AtopPermissionAuthParam;
import com.laynefongx.hodgepodge.enums.VerifyMethodEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class AtopPermissionAuthMeta {

    /**
     * 要调用的校验方法信息
     */
    private VerifyMethodEnum verifyMethod;

    /**
     * 授权参数信息
     *
     * @return 返回授权参数列表
     */
    private AtopPermissionAuthParam verifyMethodParams;
}
