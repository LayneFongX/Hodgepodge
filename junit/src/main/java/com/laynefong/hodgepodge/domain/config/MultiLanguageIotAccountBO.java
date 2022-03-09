package com.laynefong.hodgepodge.domain.config;

import lombok.Data;

@Data
public class MultiLanguageIotAccountBO {

    /**
     * 环境
     */
    private String env;

    /**
     * iot账号名
     */
    private String account;

    public MultiLanguageIotAccountBO(String env, String account) {
        this.env = env;
        this.account = account;
    }
}
