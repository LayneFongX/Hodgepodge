package com.laynefong.hodgepodge.enums;

public enum DashBoardTypeEnum {
    CONSUMPTION(1, "消耗"),
    PRODUCT(2, "生产"),
    TOTAL(3, "生产+消耗");


    private Integer code;
    private String desc;

    DashBoardTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DashBoardTypeEnum getByCode(Integer code) {
        if (code != null) {
            for (DashBoardTypeEnum unitEnum : DashBoardTypeEnum.values()) {
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
