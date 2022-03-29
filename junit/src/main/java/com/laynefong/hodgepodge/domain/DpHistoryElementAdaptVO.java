package com.laynefong.hodgepodge.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author created by Zeb灬D on 2021-07-05 14:53
 */
@Setter
@Getter
public class DpHistoryElementAdaptVO {
    private String devId;
    private Integer dpId;
    private String value;
    private long timeStamp;
    private String timeStr;
    private String dt;
}
