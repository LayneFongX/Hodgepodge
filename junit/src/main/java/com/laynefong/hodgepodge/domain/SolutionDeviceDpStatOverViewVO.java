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
public class SolutionDeviceDpStatOverViewVO {
    /**
     * 总使用量
     */
    private BigDecimal total;
    /**
     * 不同电表类型的总使用情况
     */
    private Map<String, BigDecimal> typeDetail;
}
