package com.laynefong.hodgepodge.enums;

public enum TimeTypeEnum {
    HOUR(0, "小时"),
    DAY(1, "日"),
    WEEK(2, "周"),
    MONTH(3, "月"),
    YEAR(4, "年"),
    ;


    private Integer code;
    private String desc;

    TimeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TimeTypeEnum getByCode(Integer code) {
        if (code != null) {
            for (TimeTypeEnum unitEnum : TimeTypeEnum.values()) {
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
