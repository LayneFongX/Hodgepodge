package com.laynefong.hodgepodge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yanyu.l
 * @date 2021/7/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatListVO {
    /**
     * 统计的时间格式
     */
    private String dt;

    /**
     * 范围值
     */
    private Double rangeValue;

    /**
     * 费用
     */
    private BigDecimal price;
}
