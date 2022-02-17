package com.laynefongx.hodgepodge.exception;

import com.laynefongx.hodgepodge.enums.ErrorType;

/**
 * @author falcon
 * @since 2022/2/11
 */
public class FlowException extends RuntimeException {

    private String msg;
    private Integer errorCode;
    private ErrorType type;

    public FlowException(String msg, Integer errorCode) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public FlowException(ErrorType errorType) {
        this.msg = errorType.getMessage();
        this.errorCode = errorType.getError();
        this.type = errorType;
    }

    public ErrorType getType() {
        return type;
    }
}
