package com.laynefong.hodgepodge.domain;

import com.laynefong.hodgepodge.domain.basedo.DeviceVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class SchneiderBridgeDeviceVO extends DeviceVO implements Serializable {

    private static final long serialVersionUID = -2196084911062515041L;

    /**
     * 设备ID
     */
    private String devId;

    /**
     * bridge网关ID
     */
    private String bridgeGatewayId;

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

    public SchneiderBridgeDeviceVO(String deviceName) {
        this.deviceName = deviceName;
    }


}
