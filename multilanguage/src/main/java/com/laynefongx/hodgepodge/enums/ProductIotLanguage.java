package com.laynefongx.hodgepodge.enums;

import java.util.ArrayList;
import java.util.List;

public enum ProductIotLanguage {

    PRODUCT_NAME(0, "产品名称", "产品", "Product Name"),
    PRODUCT_INFO(1, "产品功能", "产品", "Data Point"),
    APP_INFO(2, "设备面板", "产品app面板配置多语言", "Device Panel"),
    SCHEMA_LINK(3, "配网引导", "产品配网", "Pairing Guide"),
    ALARM_INFO(4, "设备消息推送", "告警信息", "Push Notification"),
    FIRMWARE_UPGRADE_INFO(5, "固件升级", "固件升级", "Firmware Update"),
    PANEL_PUBLIC_INFO(6, "公共词条", "UI面板多语言", "Public Entries"),
    ;

    private final int value;
    private final String desc;
    private final String moduleName;
    private final String englishName;

    ProductIotLanguage(int value, String desc, String moduleName, String englishName) {
        this.value = value;
        this.desc = desc;
        this.moduleName = moduleName;
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public static String getModuleNameByEnglishName(String englishName) {
        for (ProductIotLanguage iotLanguage : values()) {
            if (iotLanguage.getEnglishName().equals(englishName)) {
                return iotLanguage.moduleName;
            }
        }
        return null;
    }

    public static List<String> getEnglishNames() {
        List<String> englishNames = new ArrayList<>();
        for (ProductIotLanguage iotLanguage : values()) {
            englishNames.add(iotLanguage.getEnglishName());
        }
        return englishNames;
    }
}
