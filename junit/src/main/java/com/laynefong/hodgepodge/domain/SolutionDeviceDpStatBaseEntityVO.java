package com.laynefong.hodgepodge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ε¬η[gongsheng.wu@tuya.com]
 * @
 * @create 2021/1/22 10:52 δΈε
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDeviceDpStatBaseEntityVO {

    private String dt;

    private String devId;

    private String dpId;

    private BigDecimal rangeValue;

}
