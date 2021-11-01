package com.laynefongx.hodgepodge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyBusinessEnum {

    NONE("none", "无明确业务"),

    VERIFY_COMMON("common", "通用鉴权方式(仅在研测平台配置场景下)"),

    VERIFY_SCHNEIDER("schneider", "施耐德相关业务"),

    VERIFY_DOREL("dorel", "dorel定制业务"),

    VERIFY_TAG_FOOD("food", "润丰食物秤业务");

    private String businessType;

    private String desc;

    public String getBusinessType() {
        return businessType;
    }

    public String getDesc() {
        return desc;
    }
}
