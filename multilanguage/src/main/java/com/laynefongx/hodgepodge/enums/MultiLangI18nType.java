package com.laynefongx.hodgepodge.enums;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum MultiLangI18nType {

    SIMPLIFIED_CHINESE(1, "简体中文", "Chinese", "zh"),
    TRADITIONAL_CHINESE(2, "繁体中文", "Traditional Chinese", "tw"),
    ENGLISH(4, "英语", "English", "en"),
    SPANISH(8, "西班牙语", "Spanish", "es"),
    FRENCH(16, "法语", "French", "fr"),
    ARABIC(32, "阿拉伯语", "Arabic", "ar"),
    JAPANESE(64, "日语", "Japanese", "ja"),
    DEUTSCH(128, "德语", "German", "de"),
    ITALIAN(256, "意大利语", "Italian", "it"),
    GREEK(512, "希腊语", "Greek", "el"),
    BULGARIA(1024, "保加利亚语", "Bulgarian", "bg"),
    CZECH(2048, "捷克语", "Czech", "cs"),
    KOREAN(4096, "韩语", "Korean", "ko"),
    RUSSIAN(8192, "俄语", "Russian", "ru"),
    DUTCH(16384, "荷兰语", "Dutch", "nl"),
    TURKISH(32768, "土耳其语", "Turkish", "tr"),
    HEBREW(65536, "希伯来语", "Hebrew", "he"),
    POLISH(131072, "波兰语", "Polish", "pl"),
    HUNGARIAN(262144, "匈牙利语", "Hungarian", "hu"),
    VIETNAMESE(524288, "越南语", "Vietnamese", "vi"),
    KAZAKH(1 << 20, "哈萨克语", "Kazakh", "kk"),
    NORWEGIAN(1 << 21, "挪威语", "Norwegian", "no"),
    DANISH(1 << 22, "丹麦语", "Danish", "da"),
    FINNISH(1 << 23, "芬兰语", "Finnish", "fi"),
    SWEDISH(1 << 24, "瑞典语", "Svenska", "sv"),
    PORTUGUESE(1 << 25, "葡萄牙语", "Portuguese", "pt"),
    INDONESIAN(1 << 26, "印尼语", "Indonesian", "id"),
    THAI(1 << 27, "泰语", "Thai", "th"),
    ROMANIAN(1 << 28, "罗马尼亚语", "Romanian", "ro"),
    HINDI(1 << 29, "印度语", "Hindi", "hi"),
    BURMESE(1 << 30, "缅甸语", "Burmese", "my"),
    //2019年09月19日之后新增的语种,id规则改成自增的,不再使用位移表示
    UKRAINIAN((1 << 30) + 1, "乌克兰语", "Ukrainian", "uk"),
    SLOVAK((1 << 30) + 2, "斯洛伐克语", "Slovak", "sk"),
    MALAY((1 << 30) + 3, "马来", "Malay", "ms"),
    BENGALI((1 << 30) + 4, "孟加拉语", "Bengalese", "bn"),
    URDU((1 << 30) + 5, "乌尔都语", "Urdu", "ur"),
    UZBEK((1 << 30) + 6, "乌兹别克语", "Uzbek", "uz"),
    UIGHUR((1 << 30) + 7, "维吾尔语", "Uygur", "ug"),
    MONGOLIAN((1 << 30) + 8, "蒙古语", "Mongol", "mn"),
    MACEDONIAN((1 << 30) + 9, "马其顿语", "Macedonian", "mk"),
    FILIPINO((1 << 30) + 10, "菲律宾语", "Filipino", "tl"),
    CROATIAN((1 << 30) + 11, "克罗地亚语", "Croatian", "hr"),
    SERBIAN((1 << 30) + 12, "塞尔维亚语", "Serb", "sr"),
    SLOVENIA((1 << 30) + 13, "斯洛文尼亚语", "Slovenia", "sl"),
    ESTONIAN((1 << 30) + 14, "爱沙尼亚语", "Estonian", "et"),
    LATVIAN((1 << 30) + 15, "拉脱维亚语", "Latvian", "lv"),
    LAO((1 << 30) + 16, "老挝语", "Lao", "lo"),
    HAUSA((1 << 30) + 17, "豪萨语", "Hausa", "ha"),
    PERSIAN((1 << 30) + 18, "波斯语", "Persian", "fa"),
    BOSNIAN((1 << 30) + 19, "波斯尼亚语", "Bosnian", "bs"),
    NEPALI((1 << 30) + 20, "尼泊尔语", "Nepali", "ne"),
    LATIN((1 << 30) + 21, "拉丁语", "Latin", "la"),
    SWAHILI((1 << 30) + 22, "斯瓦希里语", "Swahili", "sw"),
    TELUGU((1 << 30) + 23, "泰卢固语", "Telugu", "te"),
    MARATHI((1 << 30) + 24, "马拉地语", "Marathi", "mr"),
    BELARUSIAN((1 << 30) + 25, "白俄罗斯语", "Belarusian", "be"),
    KINYARWANDA((1 << 30) + 26, "卢旺达语", "Kinyarwanda", "rw"),
    XHOSA((1 << 30) + 27, "科萨语", "Xhosa", "xh"),
    AZERBAIJANI((1 << 30) + 28, "阿塞拜疆语", "Azerbaijani", "az"),
    KASHMIRI((1 << 30) + 29, "克什米尔语", "Kashmiri", "ks"),
    JAVANESE((1 << 30) + 30, "爪哇语", "Javanese", "jv"),
    TAMIL((1 << 30) + 31, "泰米尔语", "Tamil", "ta"),
    GUJARATI((1 << 30) + 32, "古吉特拉语", "Gujarati", "gu"),

    /**
     *   以下几个语种带region、script，script应该为首字母大写，region应该为两位的大写字母或者3位的数字，这里为了兼容，都处理为小写
     */
    ENGLISH_UNITED_KINGDOM((1 << 30) + 33, "英语(英国)", "English(United Kingdom)", "en_GB"),
    TRADITIONAL_CHINESE_HK((1 << 30) + 34, "繁体中文(中国香港特别行政区)", "Traditional Chinese(Hong Kong Special Administrative Region,China)",
            "zh_Hant_HK"),
    PORTUGUESE_BRAZIL((1 << 30) + 35, "葡萄牙语(巴西)", "Portuguese(Brazil)",
            "pt_BR"),
    SPANISH_LATIN_AMERICA((1 << 30) + 36, "西班牙语(拉丁美洲)", "Spanish(Latin America)", "es_419"),
    ARMENIAN((1 << 30) + 37, "亚美尼亚语", "Armenian", "hy"),

    //分割线,新增的语种放上面
    LITHUANIAN(1 << 31, "立陶宛语", "Lithuanian", "lt"),
    ;

    private static final Map<String, MultiLangI18nType> LANG_CODE_MAP = Maps.newHashMapWithExpectedSize(
            MultiLangI18nType.values().length * 2);

    private final static Map<Integer, MultiLangI18nType> ID_LANGS = new HashMap<>();

    private static final Set<String> LATIN_AMERICA_SPANISH = new HashSet<>();

    static {
        for (MultiLangI18nType i18n2LangEnum : values()) {
            ID_LANGS.put(i18n2LangEnum.getId(), i18n2LangEnum);
            LANG_CODE_MAP.put(i18n2LangEnum.getShortName(), i18n2LangEnum);
            LANG_CODE_MAP.put(i18n2LangEnum.getShortName().toLowerCase(), i18n2LangEnum);
        }

        LATIN_AMERICA_SPANISH.add("es_AR".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_BO".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_BR".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_BZ".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_CL".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_CO".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_CR".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_CU".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_DO".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_EC".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_GT".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_HN".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_MX".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_NI".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_PA".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_PE".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_PR".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_PY".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_SV".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_US".toLowerCase());
        LATIN_AMERICA_SPANISH.add("es_UY".toLowerCase());
    }

    private final Integer id;
    private final String name;
    private final String englishName;
    private final String shortName;

    MultiLangI18nType(int id, String name, String englishName, String shortName) {
        this.id = id;
        this.name = name;
        this.englishName = englishName;
        this.shortName = shortName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getShortName() {
        return shortName;
    }

    public MultiLangI18nType getFallback() {
        String fallback = this.shortName;
        int extStart = fallback.indexOf('@');
        if (extStart == -1) {
            extStart = fallback.length();
        }
        int last = fallback.lastIndexOf('_', extStart);
        if (last == -1) {
            last = 0;
        } else {
            // truncate empty segment
            while (last > 0) {
                if (fallback.charAt(last - 1) != '_') {
                    break;
                }
                last--;
            }
        }
        String lang = fallback.substring(0, last) + fallback.substring(extStart);
        if (StringUtils.isBlank(lang)) {
            return null;
        }
        return MultiLangI18nType.getCompatibleLangEnum(lang);
    }

    public static MultiLangI18nType getCompatibleLangEnum(String lang) {
        if (StringUtils.isBlank(lang)) {
            return MultiLangI18nType.SIMPLIFIED_CHINESE;
        }

        lang = lang.toLowerCase();
        lang = lang.replace("-", "_");

        MultiLangI18nType i18n2LangEnum = LANG_CODE_MAP.get(lang);
        if (i18n2LangEnum != null) {
            return i18n2LangEnum;
        }

        // zh-Hans-CN、zh-Hant-TW 为IOS9会传过来的语言
        if (lang.equalsIgnoreCase("zh") || lang.startsWith("zh_cn") || lang.startsWith("zh_hans")) {
            return MultiLangI18nType.SIMPLIFIED_CHINESE;
        } else if (lang.equalsIgnoreCase("zh_tw") || lang.equalsIgnoreCase("tw") || lang.startsWith("zh_hant")
                || lang.equalsIgnoreCase("zh_mo")) {
            return MultiLangI18nType.TRADITIONAL_CHINESE;
        } else if (lang.equalsIgnoreCase("zh_hk")) {
            return MultiLangI18nType.TRADITIONAL_CHINESE_HK;
        } else if (LATIN_AMERICA_SPANISH.contains(lang)) {
            return SPANISH_LATIN_AMERICA;
        }

        for (MultiLangI18nType langEnum : values()) {
            if (lang.startsWith(langEnum.getShortName())) {
                return langEnum;
            }
        }

        //见: https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
        //iw 是老的iso国家码, android还是用的老的, 所以需要兼容
        //类似的情况还有. yi和 id 两种语言
        if (lang.startsWith("iw")) {
            return MultiLangI18nType.HEBREW;
        }

        if (lang.startsWith("nn") || lang.startsWith("nb")) {
            return NORWEGIAN;
        }

        return MultiLangI18nType.ENGLISH;
    }

    public static MultiLangI18nType get(int id) {
        return ID_LANGS.get(id);
    }
}
