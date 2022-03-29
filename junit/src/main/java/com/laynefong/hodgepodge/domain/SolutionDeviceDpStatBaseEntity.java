package com.laynefong.hodgepodge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 公生[gongsheng.wu@tuya.com]
 * @
 * @create 2021/1/22 10:52 上午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDeviceDpStatBaseEntity {
    private int timeType;

    /**
     * 主键id
     * This field corresponds to the database column solution_device_dp_stat_hours.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 统计的时间格式
     * This field corresponds to the database column solution_device_dp_stat_hours.dt
     *
     * @mbg.generated
     */
    private String dt;

    /**
     * This field corresponds to the database column solution_device_dp_stat_hours.pid
     *
     * @mbg.generated
     */
    private String pid;

    /**
     * This field corresponds to the database column solution_device_dp_stat_hours.uid
     *
     * @mbg.generated
     */
    private String uid;

    /**
     * This field corresponds to the database column solution_device_dp_stat_hours.bizType
     *
     * @mbg.generated
     */
    private String bizType;

    /**
     * This field corresponds to the database column solution_device_dp_stat_hours.owner_id
     *
     * @mbg.generated
     */
    private String ownerId;

    /**
     * This field corresponds to the database column solution_device_dp_stat_hours.devId
     *
     * @mbg.generated
     */
    private String devId;

    /**
     * This field corresponds to the database column solution_device_dp_stat_hours.dpId
     *
     * @mbg.generated
     */
    private String dpId;

    /**
     * 范围值
     * This field corresponds to the database column solution_device_dp_stat_hours.range_value
     *
     * @mbg.generated
     */
    private BigDecimal rangeValue;

    /**
     * 费用
     * This field corresponds to the database column solution_device_dp_stat_hours.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * 状态；1：有效，0：无效
     * This field corresponds to the database column solution_device_dp_stat_hours.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 创建时间
     * This field corresponds to the database column solution_device_dp_stat_hours.gmt_create
     *
     * @mbg.generated
     */
    private Long gmtCreate;

    /**
     * 更新时间
     * This field corresponds to the database column solution_device_dp_stat_hours.gmt_modified
     *
     * @mbg.generated
     */
    private Long gmtModified;
}
