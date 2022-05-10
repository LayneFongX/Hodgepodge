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
public class SolutionTariffPlanEntity {

    /**
     * 主键id
     * This field corresponds to the database column solution_tariff_plan.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 所属资源id
     * This field corresponds to the database column solution_tariff_plan.res_id
     *
     * @mbg.generated
     */
    private String resId;

    /**
     * 所属资源类型: 1：设备, 2：设备群组
     * This field corresponds to the database column solution_tariff_plan.res_type
     *
     * @mbg.generated
     */
    private Integer resType;

    /**
     * 计划类型；1-正常，2-高低峰
     * This field corresponds to the database column solution_tariff_plan.plan_type
     *
     * @mbg.generated
     */
    private Integer planType;

    /**
     * 币种；
     * This field corresponds to the database column solution_tariff_plan.currency
     *
     * @mbg.generated
     */
    private String currency;

    /**
     * 消耗月总量
     * This field corresponds to the database column solution_tariff_plan.consumption_month_target
     *
     * @mbg.generated
     */
    private BigDecimal consumptionMonthTarget;

    /**
     * 生产月总量
     * This field corresponds to the database column solution_tariff_plan.product_month_target
     *
     * @mbg.generated
     */
    private BigDecimal productMonthTarget;

    /**
     * plan_type为1则是正常价格，plan_type为2则低峰价格
     * This field corresponds to the database column solution_tariff_plan.plat_rate
     *
     * @mbg.generated
     */
    private BigDecimal platRate;

    /**
     * plan_type为2则高峰价格
     * This field corresponds to the database column solution_tariff_plan.plat_rate_on_peak
     *
     * @mbg.generated
     */
    private BigDecimal platRateOnPeak;

    /**
     * 高峰开始时间格式yyyyyMMHHmm
     * This field corresponds to the database column solution_tariff_plan.on_peak_start_time_formate
     *
     * @mbg.generated
     */
    private String onPeakStartTimeFormate;

    /**
     * 高峰结束时间格式,yyyyyMMHHmm
     * This field corresponds to the database column solution_tariff_plan.on_peak_end_time_formate
     *
     * @mbg.generated
     */
    private String onPeakEndTimeFormate;

    /**
     * 状态；1：有效，0：无效
     * This field corresponds to the database column solution_tariff_plan.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 创建时间
     * This field corresponds to the database column solution_tariff_plan.gmt_create
     *
     * @mbg.generated
     */
    private Long gmtCreate;

    /**
     * 更新时间
     * This field corresponds to the database column solution_tariff_plan.gmt_modified
     *
     * @mbg.generated
     */
    private Long gmtModified;
}
