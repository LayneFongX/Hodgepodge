package com.laynefongx.hodgepodge.domain.operate;


import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author falcon
 * @since 2022/2/15
 */
@Data
@Builder
public class ExecuteTask {

    /**
     * 用户Id
     * */
    private String uid;

    /**
     * 操作Id
     * */
    private String operateId;

    /**
     * 操作类型
     * */
    private Integer operate;

    /**
     * zip包解压数据
     * */
    private Map<String, byte[]> unZipMap;

    /**
     * 操作配置信息
     * */
    private OperateConfigDto configDto;
}
