package com.laynefongx.hodgepodge.enums;

public enum ErrorType {
    EXCEL_OP(1, "excel 操作失败");

    private String message;

    private Integer error;

    ErrorType(Integer error,String message) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public Integer getError() {
        return error;
    }
}
