package com.laynefongx.hodgepodge.enums;

public enum RecordStatus {
    PROCESSING("processing", "正在处理中"),
    FAILED("failed", "处理失败"),
    TIMEOUT("timeout", "处理超时"),
    FINISH("finish", "处理完成");
    private String status;

    private String desc;

    RecordStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }
}
