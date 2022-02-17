package com.laynefongx.hodgepodge.domain.operate;

import lombok.Data;

/**
 * @author falcon
 * @since 2022/2/14
 */
@Data
public class OperateRecord {

    /**
     * 操作类型
     */
    private Integer operate;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 最后操作时间(包括成功结束、错误结束、超时结束)
     */
    private Long lastTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 处理进度
     */
    private Integer percent;

    /**
     * 操作Id
     */
    private String operateId;
}
