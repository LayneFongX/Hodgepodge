package com.laynefong.hodgepodge.enums;

public enum StatTypeEnum {
    USAGE(1, "电量 USAGE"),
    CHARGE(2, "电费 CHARGE");


    private Integer code;
    private String desc;

    StatTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatTypeEnum getByCode(Integer code) {
        if (code != null) {
            for (StatTypeEnum unitEnum : StatTypeEnum.values()) {
                if (unitEnum.getCode().equals(code)) {
                    return unitEnum;
                }
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
