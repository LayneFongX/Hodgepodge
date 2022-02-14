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


    /**
     * http响应成功
     */
    public static final int RESPONSE_CODE_SUCCESS = 200;

    public static final String MULTI_LANGUAGE_KEY_MODULE_NAME = "MULTI_LANGUAGE";

    public static final String MULTI_LANGUAGE_IOT_ACCOUNT_PRODUCTS = "MULTI_LANGUAGE_IOT_ACCOUNT_PRODUCTS";

    public static final String MULTI_LANGUAGE_LANGUAGES = "MULTI_LANGUAGE_LANGUAGES";

    public static final String MULTI_LANGUAGE_FAQ_TITLE = "Knowledge title";

    public static final String MULTI_LANGUAGE_FAQ_ANSWER = "Text Answer";

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
