package com.laynefongx.hodgepodge.enums;

public enum ErrorType {
    UPLOAD_FILE_NOT_FOUND(1, "用户上传文件未找到"),
    DOWNLOAD_USER_FILE_ERROR(2, "下载用户上传文件失败"),
    UNZIP_FILE_ERROR(3, "解压zip文件失败"),
    ZIP_DIRECTORY_ERROR(4, "解析文件目录错误"),
    EXCEL_PARSE_ERROR(5, "excel解析失败"),
    EXCEL_WRITE_ERROR(6, "excel写入失败"),
    SAVE_RESULT_ZIP_ERROR(7, "保存处理结果失败"),
    COMPARE_LANGUAGE_ITEM_ERROR(8, "多语言错误词条"),
    USER_TASK_PROCESSING(9, "当前用户有任务正在处理"),
    GET_IOT_LANG_ERROR(10, "获取IoT平台多语言数据错误"),
    COMPARE_LANG_DATAS_ERROR(11, "对比数据错误"),
    COMPARE_FILES_HANDLE_ERROR(12, "对比时文件处理错误"),
    MERGE_LANG_DATAS_ERROR(13, "合并数据错误"),
    MERGE_FILES_HANDLE_ERROR(14, "合并时文件处理错误"),
    DEVICE_SHEETNAME_ERROR(15, "Excel文件Sheet名称错误"),
    USER_PROCESSING(16, "用户已有任务在执行"),
    ;

    private String message;

    private Integer error;

    ErrorType(Integer error, String message) {
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
