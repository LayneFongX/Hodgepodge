package com.laynefong.hodgepodge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author created by Zeb灬D on 2021-04-28 21:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolutionDeviceDpStatListVO {
    /**
     * 1-CONSUMPTION 消耗
     * 2-product 生产
     */
    private int dashBoardType;
    /**
     * 1-CONSUMPTION 消耗
     * 2-product 生产
     */
    private int statType;

    private String currency;

    /**
     * 总概览
     */
    private SolutionDeviceDpStatOverViewVO overview;

    /**
     * 对应timeType下的列表
     */
    private Map<String, Map<String, BigDecimal>> timeDetailMap;
}
