package com.laynefongx.hodgepodge.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author banchao.feng@tuya.com
 * @date 2021/10/15 11:27
 */
@Data
public class GroupUserBizVO implements Serializable {

    private static final long serialVersionUID = -3946500373205314162L;

    private String uid;

    private Long gid;

    private Boolean status;

    private String name;
    
    private Boolean admin;

    private Integer role;
}
