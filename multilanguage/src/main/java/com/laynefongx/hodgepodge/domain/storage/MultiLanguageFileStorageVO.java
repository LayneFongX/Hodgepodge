package com.laynefongx.hodgepodge.domain.storage;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 公生[gongsheng.wu@tuya.com]
 * @date 2021/12/29 11:23 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiLanguageFileStorageVO implements Serializable {

    /**
     * 云存储ID
     */
    private Long fileId;

    /**
     * 预签名地址的请求方法。GET:读取授权；PUT:上传授权
     */
    private String httpMethod;

    /**
     * 预签名的上传文件地址
     */
    private String uploadSignUrl;

    /**
     * 预签名上传文件地址的有效时间，单位秒
     */
    private Integer expireTime;

}
