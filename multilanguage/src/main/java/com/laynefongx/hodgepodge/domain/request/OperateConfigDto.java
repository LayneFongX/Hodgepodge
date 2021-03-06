package com.laynefongx.hodgepodge.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class OperateConfigDto {

    /**
     * 环境
     */
    private String env;

    /**
     * 语种Id集合
     */
    private List<Integer> languageIds;

    /**
     * 文件类型
     */
    private List<String> fileTypes;
    /**
     * 设备列表
     */
    private List<String> products;

    /**
     * 可编辑选项
     */
    private Integer editType;
}
