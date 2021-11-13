package com.laynefong.hodgepodge.domain;

import com.laynefong.hodgepodge.domain.basedo.DeviceVO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SchneiderIhcDeviceVO extends DeviceVO implements Serializable {

    private static final long serialVersionUID = -2196084911062515041L;


    public SchneiderIhcDeviceVO() {

    }

    /**
     * 设备ID
     */
    private String devId;

    /**
     * 图标
     */
    private String iconUrl;

    /**
     *
     */
    private Boolean selected;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 房间名称
     */
    private String roomName;


}