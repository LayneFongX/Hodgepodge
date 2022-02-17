package com.laynefongx.hodgepodge.domain.operate;

import lombok.Data;

/**
 * @author falcon
 * @since 2022/2/14
 */
@Data
public class OperateLog {

    /**
     * 操作ID
     * */
    private String operateId;

    /**
     * 错误类型
     * */
    private Integer errorCode;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 文件名称 冗余使用
     */
    private String fileName;

    /**
     * sheet页名称
     */
    private String sheetName;

    /**
     * 错误key
     */
    private String key;

    /**
     * 错误原因
     * */
    private String reason;
}
