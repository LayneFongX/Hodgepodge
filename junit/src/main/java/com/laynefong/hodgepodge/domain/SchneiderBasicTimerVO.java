package com.laynefong.hodgepodge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author banchao.feng@tuya.com
 * @date 2021/5/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchneiderBasicTimerVO implements Serializable {

    private static final long serialVersionUID = 2249732360062474549L;

    private String loops;

    private String resId;

    private Integer resType;

    private String bizId;

    private String startTime;

    private Long localTime;

    private String note;

    private Integer timerStatus;

    private String ext;
}
