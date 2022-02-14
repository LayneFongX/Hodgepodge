package com.laynefongx.hodgepodge.exception;

import com.tuya.shendeng.enums.ErrorType;

/**
 * @author falcon
 * @since 2022/2/11
 */
public class FlowException extends RuntimeException {

    private String msg;
    private Integer errorType;

    public FlowException(String msg, Integer errorType) {
        this.msg = msg;
        this.errorType = errorType;
    }

    public FlowException(ErrorType errorType) {
        this.msg = errorType.getMessage();
        this.errorType = errorType.getError();
    }
}
