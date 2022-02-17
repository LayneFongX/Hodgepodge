package com.laynefongx.hodgepodge.enums;

public enum MultiLangAppInfo {

    WISER_APP(15164, "Wiser App"),
    ELKO_APP(21560, "Elko App"),
    ;

    private int appId;
    private String appName;

    MultiLangAppInfo(int appId, String appName) {
        this.appId = appId;
        this.appName = appName;
    }

    public int getAppId() {
        return appId;
    }

    public String getAppName() {
        return appName;
    }

    public static Integer getAppIdByAppName(String appName) {
        for (MultiLangAppInfo appIdEnum : values()) {
            if (appIdEnum.getAppName().equals(appName)) {
                return appIdEnum.getAppId();
            }
        }
        return null;
    }
}
