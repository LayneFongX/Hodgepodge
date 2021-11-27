package com.laynefong.hodgepodge.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SchneiderIhcSubDevicesConfigBO implements Serializable {

    private static final long serialVersionUID = -7560015312637200104L;
    
    /**
     * ihc子设备要转移到的家庭ID
     */
    private String homeId;


    /**
     * 家庭名称
     */
    private String homeName;


    /**
     * 家庭下最大设备数量
     */
    private Integer limit;


    /**
     * 家庭排序顺序
     */
    private Integer order;


}
