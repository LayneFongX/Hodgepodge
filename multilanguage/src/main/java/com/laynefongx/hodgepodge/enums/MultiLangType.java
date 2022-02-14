package com.laynefongx.hodgepodge.enums;

import java.util.HashMap;
import java.util.Map;

public enum MultiLangType {
    CHINESE(1, "简体中文", "zh", "CHINESE"),
    CHINESE_TW(2, "繁体中文", "zh_tw", "CHINESE_TW"),
    ENGLISH(3, "英语", "en", "ENGLISH"),
    GERMAN(4, "德语", "de", "GERMAN"),
    ITALIAN(5, "意大利语", "it", "ITALIAN"),
    SPANISH(6, "西班牙语", "es", "SPANISH"),
    FRENCH(7, "法语", "fr", "FRENCH"),
    JAPANESE(8, "日语", "ja", "JAPANESE"),
    KOREAN(9, "韩语", "ko", "KOREAN"),
    HEBREW(10, "希伯来语", "he", "HEBREW"),
    PORTUGUESE(11, "葡萄牙语", "pt", "PORTUGUESE"),
    THAI(12, "泰语", "th", "THAI"),
    RUSSIAN(13, "俄语", "ru", "RUSSIAN"),
    GREEK(14, "希腊语", "el", "GREEK"),
    POLISH(15, "波兰语", "pl", "POLISH"),
    INDONESIAN(16, "印尼语", "id", "INDONESIAN"),
    MALAY(17, "马来语", "ms", "MALAY"),
    ARABIC(18, "阿拉伯语", "ar", "ARABIC"),
    BULGARIAN(19, "保加利亚语", "bg", "BULGARIAN"),
    CZECH(20, "捷克语", "cs", "CZECH"),
    DUTCH(21, "荷兰语", "nl", "DUTCH"),
    TURKISH(22, "土耳其语", "tr", "TURKISH"),
    HUNGARIAN(23, "匈牙利语", "hu", "HUNGARIAN"),
    VIETNAMESE(24, "越南语", "vi", "VIETNAMESE"),
    KAZAKH(25, "哈萨克语", "kk", "KAZAKH"),
    NORWEGIAN(26, "挪威语", "no", "NORWEGIAN"),
    DANISH(27, "丹麦语", "da", "DANISH"),
    FINNISH(28, "芬兰语", "fi", "FINNISH"),
    SWEDISH(29, "瑞典语", "sv", "SWEDISH"),
    ROMANIAN(30, "罗马尼亚语", "ro", "ROMANIAN"),
    HINDI(31, "印地语", "hi", "HINDI"),
    UKRAINIAN(32, "乌克兰语", "uk", "UKRAINIAN"),
    SLOVAK(33, "斯洛伐克语", "sk", "SLOVAK"),
    //    KOREAN_KR(34,"韩语","ko_kr"),
    BENGALI(35, "孟加拉语", "bn", "BENGALI"),
    URDU(36, "乌尔都语", "ur", "URDU"),
    UZBEK(37, "乌兹别克语", "uz", "UZBEK"),
    UIGHUR(38, "维吾尔语", "ug", "UIGHUR"),
    MONGOLIAN(39, "蒙古语", "mn", "MONGOLIAN"),
    MACEDONIAN(40, "马其顿语", "mk", "MACEDONIAN"),
    FILIPINO(41, "菲律宾语", "tl", "FILIPINO"),
    CROATIAN(42, "克罗地亚语", "hr", "CROATIAN"),
    Burmese(43, "缅甸语", "my", "Burmese"),
    Lithuania(44, "立陶宛", "lt", "Lithuania"),
    Serbian(45, "塞尔维亚语", "sr", "Serbian"),
    SLOVENIA(46, "斯洛文尼亚语", "sl", "SLOVENIA"),

    ESTONIAN(47, "爱沙尼亚语", "et", "ESTONIAN"),
    LATVIAN(48, "拉脱维亚语", "lv", "LATVIAN"),
    LAO(49, "老挝语", "lo", "LAO"),
    HAUSA(50, "豪萨语", "ha", "HAUSA"),
    PERSIAN(51, "波斯语", "fa", "PERSIAN"),
    BOSNIAN(52, "波斯尼亚语", "bs", "BOSNIAN"),
    NEPALI(53, "尼泊尔语", "ne", "NEPALI"),
    LATIN(54, "拉丁语", "la", "LATIN"),
    SWAHILI(55, "斯瓦希里语", "sw", "SWAHILI"),
    TELUGU(56, "泰卢固语", "te", "TELUGU"),
    MARATHI(57, "马拉地语", "mr", "MARATHI"),
    BELARUSIAN(58, "白俄罗斯语", "be", "BELARUSIAN"),
    KINYARWANDA(59, "卢旺达语", "rw", "KINYARWANDA"),
    XHOSA(60, "科萨语", "xh", "XHOSA"),
    AZERBAIJANI(61, "阿塞拜疆语", "az", "AZERBAIJANI"),
    KASHMIRI(62, "克什米尔语", "ks", "KASHMIRI"),
    JAVANESE(63, "爪哇语", "jv", "JAVANESE"),
    TAMIL(64, "泰米尔语", "ta", "TAMIL"),
    GUJARATI(65, "古吉特拉语", "gu", "GUJARATI"),

    /**
     *   以下几个语种带region、script，script应该为首字母大写，region应该为两位的大写字母或者3位的数字
     */
    ENGLISH_UNITED_KINGDOM(66, "英语(英国)", "en_GB", "ENGLISH_UNITED_KINGDOM"),
    TRADITIONAL_CHINESE_HK(67, "繁体中文(中国香港特别行政区)", "zh_Hant_HK", "TRADITIONAL_CHINESE_HK"),
    PORTUGUESE_BRAZIL(68, "葡萄牙语(巴西)", "pt_BR", "PORTUGUESE_BRAZIL"),
    SPANISH_LATIN_AMERICA(69, "西班牙语(拉丁美洲)", "es_419", "SPANISH_LATIN_AMERICA"),
    ARMENIAN(70, "亚美尼亚语", "hy", "ARMENIAN"),
    ;

    private final int id;
    private final String name;
    private final String code;
    private final String enName;

    private final static Map<String, String> NAME_LANGS = new HashMap<>();

    static {
        for (MultiLangType type : MultiLangType.values()) {
            NAME_LANGS.put(type.name, type.enName);
        }
    }

    MultiLangType(int id, String name, String code, String enName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.enName = enName;
    }

    public String getEnName() {
        return enName;
    }
}