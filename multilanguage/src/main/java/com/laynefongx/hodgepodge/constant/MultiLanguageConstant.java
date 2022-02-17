package com.laynefongx.hodgepodge.constant;


import com.laynefongx.hodgepodge.domain.config.MultiLanguageIotAccountBO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiLanguageConstant {

    /**
     * highway接口的B端bizType
     */
    public static final int BIZ_TYPE_BUSINESS_END = 0;

    public static final String MULTI_LANGUAGE_KEY_MODULE_NAME = "MULTI_LANGUAGE";

    public static final String MULTI_LANGUAGE_IOT_ACCOUNT_PRODUCTS = "MULTI_LANGUAGE_IOT_ACCOUNT_PRODUCTS";

    public static final String MULTI_LANGUAGE_LANGUAGES = "MULTI_LANGUAGE_LANGUAGES";

    public static final String MULTI_LANGUAGE_FAQ_TITLE = "Knowledge title";

    public static final String MULTI_LANGUAGE_FAQ_ANSWER = "Text Answer";

    public static final String OPERATE_COMPARE = "%s_compare_file";

    public static final String OPERATE_MERGE = "%s_merge_file";

    public static final String USER_OPERATE_STATUS = "%s_status";

    public static final String USER_UPLOAD_FILE = "%s_upload";

    public static final String USER_OPERATE_LOG = "%s_log";

    public static final String BUSINESS_TYPE = "tuya-developer";

    public static final Integer MULTI_LANGUAGE_GET_DATA_LIMIT_100 = 100;

    public static final Integer MULTI_LANGUAGE_GET_DATA_LIMIT_200 = 200;

    // App 配网设备列表
    public static final String MULTI_LANGUAGE_APP_DISTRIBUTION_NETWORK_DEVICES = "MULTI_LANGUAGE_APP_DISTRIBUTION_NETWORK_DEVICES_%s";

    // APP 客户端语言包
    public static final String MULTI_LANGUAGE_APP_CLIENT_LANGUAGE_PACKAGE = "MULTI_LANGUAGE_APP_CLIENT_LANGUAGE_PACKAGE_%s";

    // APP 服务端语言包
    public static final String MULTI_LANGUAGE_APP_SERVER_LANGUAGE_PACKAGE = "MULTI_LANGUAGE_APP_SERVER_LANGUAGE_PACKAGE_%s";

    // 产品 产品名称多语言
    public static final String MULTI_LANGUAGE_PRODUCT_NAME = "MULTI_LANGUAGE_PRODUCT_NAME_%s";

    // 产品 产品功能多语言
    public static final String MULTI_LANGUAGE_DATA_POINT = "MULTI_LANGUAGE_DATA_POINT_%s";

    // 产品 设备面板多语言
    public static final String MULTI_LANGUAGE_DEVICE_PANEL = "MULTI_LANGUAGE_DEVICE_PANEL_%s";

    // 产品 配网引导多语言
    public static final String MULTI_LANGUAGE_PARING_GUIDE = "MULTI_LANGUAGE_PARING_GUIDE_%s";

    // 产品 设备消息推送多语言
    public static final String MULTI_LANGUAGE_PUSH_NOTIFICATION = "MULTI_LANGUAGE_PUSH_NOTIFICATION_%s";

    // 产品 固件升级多语言
    public static final String MULTI_LANGUAGE_FIRMWARE_UPGRADE = "MULTI_LANGUAGE_FIRMWARE_UPGRADE_%s";

    // 产品 公共词条多语言
    public static final String MULTI_LANGUAGE_PUBLIC_ENTRIES = "MULTI_LANGUAGE_PUBLIC_ENTRIES_%s";

    public static final int ENCRYPT_TYPE = 0;

    public static final String ENCRYPT_KEY = "";

    public static final boolean COVER_UPLOAD = true;

    /**
     * 施耐德Iot平台账号
     */
    public static List<MultiLanguageIotAccountBO> multiLanguageIotAccounts =
            Stream.of(new MultiLanguageIotAccountBO("Release", "wiser.release@se.com"),
                    new MultiLanguageIotAccountBO("Field Test", "wiser.fieldtest@se.com"),
                    new MultiLanguageIotAccountBO("Staging", "wiser.staging@se.com"),
                    new MultiLanguageIotAccountBO("Development", "wiser.development@se.com")).collect(
                    Collectors.toList());


}
