package com.laynefongx.hodgepodge.enums;

/**
 * @author falcon
 * @since 2022/2/10
 */
public enum OperateType {
    COMPARE(1),
    MERGE(2);
    private int type;

    OperateType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
