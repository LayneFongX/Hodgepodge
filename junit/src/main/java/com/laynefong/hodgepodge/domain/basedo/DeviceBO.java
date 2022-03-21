package com.laynefong.hodgepodge.domain.basedo;


import lombok.Data;
import lombok.ToString;

/**
 * @author yangzj
 * @desc 防腐层的设备BO信息
 * @create 2021-01-09 6:21 下午
 **/
@Data
@ToString(callSuper = true)
public class DeviceBO extends BaseBO {

    private String id;
    private String uid;
    private String timeZone;
    private String productId;
    private String uuid;
    private Boolean isExists;
    private Integer accessType;
    private Integer ability;
    private Boolean sub;
    private String timeZoneId;
    private String ownerId;
    private String mac;
    private Long activeTime;

    private String name;
    private String customName;

    public DeviceBO(String customName) {
        this.customName = customName;
    }

    public DeviceBO() {
        
    }

}
