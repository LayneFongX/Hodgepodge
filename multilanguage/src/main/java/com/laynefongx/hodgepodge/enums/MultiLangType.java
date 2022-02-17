package com.laynefongx.hodgepodge.enums;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

public enum MultiLangType {
    CHINESE(1, "简体中文", "zh", false, "Chinese"),
    CHINESE_TW(2, "繁体中文", "zh_tw", "Chinese_tw"),
    ENGLISH(3, "英语", "en", false, "English"),
    GERMAN(4, "德语", "de", "German"),
    ITALIAN(5, "意大利语", "it", "Italian"),
    SPANISH(6, "西班牙语", "es", "Spanish"),
    FRENCH(7, "法语", "fr", "French"),
    JAPANESE(8, "日语", "ja", false, "Japanese"),
    KOREAN(9, "韩语", "ko", "Korean"),
    HEBREW(10, "希伯来语", "he", "Hebrew"),
    PORTUGUESE(11, "葡萄牙语", "pt", "Portuguese"),
    THAI(12, "泰语", "th", "Thai"),
    RUSSIAN(13, "俄语", "ru", "Russian"),
    GREEK(14, "希腊语", "el", "Greek"),
    POLISH(15, "波兰语", "pl", "Polish"),
    INDONESIAN(16, "印尼语", "id", "Indonesian"),
    MALAY(17, "马来语", "ms", "Malay"),
    ARABIC(18, "阿拉伯语", "ar", "Arabic"),
    BULGARIAN(19, "保加利亚语", "bg", "Bulgarian"),
    CZECH(20, "捷克语", "cs", "Czech"),
    DUTCH(21, "荷兰语", "nl", "Dutch"),
    TURKISH(22, "土耳其语", "tr", "Turkish"),
    HUNGARIAN(23, "匈牙利语", "hu", "Hungarian"),
    VIETNAMESE(24, "越南语", "vi", "Vietnamese"),
    KAZAKH(25, "哈萨克语", "kk", "Kazakh"),
    NORWEGIAN(26, "挪威语", "no", "Norwegian"),
    DANISH(27, "丹麦语", "da", "Danish"),
    FINNISH(28, "芬兰语", "fi", "Finnish"),
    SWEDISH(29, "瑞典语", "sv", "Swedish"),
    ROMANIAN(30, "罗马尼亚语", "ro", "Romanian"),
    HINDI(31, "印地语", "hi", "Hindi"),
    UKRAINIAN(32, "乌克兰语", "uk", "Ukrainian"),
    SLOVAK(33, "斯洛伐克语", "sk", "Slovak"),
    //    KOREAN_KR(34,"韩语","ko_kr"),
    BENGALI(35, "孟加拉语", "bn", "Bengali"),
    URDU(36, "乌尔都语", "ur", "Urdu"),
    UZBEK(37, "乌兹别克语", "uz", "Uzbek"),
    UIGHUR(38, "维吾尔语", "ug", "Uighur"),
    MONGOLIAN(39, "蒙古语", "mn", "Mongolian"),
    MACEDONIAN(40, "马其顿语", "mk", "Macedonian"),
    FILIPINO(41, "菲律宾语", "tl", "Filipino"),
    CROATIAN(42, "克罗地亚语", "hr", "Croatian"),
    Burmese(43, "缅甸语", "my", "Burmese"),
    Lithuania(44, "立陶宛", "lt", "Lithuania"),
    Serbian(45, "塞尔维亚语", "sr", "Serbian"),
    SLOVENIA(46, "斯洛文尼亚语", "sl", "Slovenia"),

    ESTONIAN(47, "爱沙尼亚语", "et", "Estonian"),
    LATVIAN(48, "拉脱维亚语", "lv", "Latvian"),
    LAO(49, "老挝语", "lo", "Lao"),
    HAUSA(50, "豪萨语", "ha", "Hausa"),
    PERSIAN(51, "波斯语", "fa", "Persian"),
    BOSNIAN(52, "波斯尼亚语", "bs", "Bosnian"),
    NEPALI(53, "尼泊尔语", "ne", "Nepali"),
    LATIN(54, "拉丁语", "la", "Latin"),
    SWAHILI(55, "斯瓦希里语", "sw", "Swahili"),
    TELUGU(56, "泰卢固语", "te", "Telugu"),
    MARATHI(57, "马拉地语", "mr", "Marathi"),
    BELARUSIAN(58, "白俄罗斯语", "be", "Belarusian"),
    KINYARWANDA(59, "卢旺达语", "rw", "Kinyarwanda"),
    XHOSA(60, "科萨语", "xh", "Xhosa"),
    AZERBAIJANI(61, "阿塞拜疆语", "az", "Azerbaijani"),
    KASHMIRI(62, "克什米尔语", "ks", "Kashmiri"),
    JAVANESE(63, "爪哇语", "jv", "Javanese"),
    TAMIL(64, "泰米尔语", "ta", "Tamil"),
    GUJARATI(65, "古吉特拉语", "gu", "Gujarati"),

    /**
     *   以下几个语种带region、script，script应该为首字母大写，region应该为两位的大写字母或者3位的数字
     */
    ENGLISH_UNITED_KINGDOM(66, "英语(英国)", "en_GB", "English(United Kingdom)"),
    TRADITIONAL_CHINESE_HK(67, "繁体中文(中国香港特别行政区)", "zh_Hant_HK", "Traditional Chinese(Hong Kong Special Administrative Region,China)"),
    PORTUGUESE_BRAZIL(68, "葡萄牙语(巴西)", "pt_BR", "Portuguese(Brazil)"),
    SPANISH_LATIN_AMERICA(69, "西班牙语(拉丁美洲)", "es_419", "Spanish(Latin America)"),
    ARMENIAN(70, "亚美尼亚语", "hy", "Armenian"),
    ;

    private final int id;
    private final String name;
    private final String code;
    private final boolean blank;
    private final String englishName;

    private final static Map<Integer, MultiLangType> ID_LANGS = new HashMap<>();
    private final static Map<String, MultiLangType> NAME_LANGS = new HashMap<>();
    private final static Map<String, MultiLangType> CODE_LANGS = new HashMap<>();
    private final static Map<String, MultiLangType> COMPATIBLE_CODE_LANGS = new HashMap<>();
    private static final Set<String> LATIN_AMERICA_SPANISH = new HashSet<>();
    private final static int MAX_ID;

    static {
        int maxId = 0;
        for (MultiLangType type : MultiLangType.values()) {
            ID_LANGS.put(type.id, type);
            NAME_LANGS.put(type.name, type);
            CODE_LANGS.put(type.code, type);
            CODE_LANGS.put(type.code.toLowerCase(), type);

            if (maxId < type.id) {
                maxId = type.id;
            }
        }
        COMPATIBLE_CODE_LANGS.put("fli", FILIPINO);
        COMPATIBLE_CODE_LANGS.put("iw", HEBREW);
        COMPATIBLE_CODE_LANGS.put("in", INDONESIAN);
        COMPATIBLE_CODE_LANGS.put("nb", NORWEGIAN);
        COMPATIBLE_CODE_LANGS.put("nn", NORWEGIAN);

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

        MAX_ID = maxId;
    }

    public static MultiLangType getByEnumName(String name) {
        for (MultiLangType rosettaLangType : values()) {
            if (rosettaLangType.name().equals(name)) {
                return rosettaLangType;
            }
        }
        return null;
    }

    public static MultiLangType get(int id) {
        //兼容朝鲜语
        if (id == 34) {
            return KOREAN;
        }
        return ID_LANGS.get(id);
    }

    public static MultiLangType get(String name) {
        //兼容朝鲜语
        if ("朝鲜语".equals(name)) {
            return KOREAN;
        }
        return NAME_LANGS.get(name);
    }

    public static Map<Integer, MultiLangType> getAll() {
        return new TreeMap<>(ID_LANGS);
    }

    /**
     * 转换成标准的语种代号，参考bcp47等规范
     *
     * @return
     */
    public static Optional<String> toStandardLangCode(int id) {
        MultiLangType rosettaLangType = get(id);
        if (rosettaLangType == null) {
            return Optional.empty();
        }
        if (rosettaLangType == CHINESE) {
            return Optional.of("zh-Hans");
        }

        if (rosettaLangType == CHINESE_TW) {
            return Optional.of("zh-Hant");
        }

        return Optional.of(rosettaLangType.getCode());
    }

    /**
     * 获取兜底语言，比如zh-Hant-HK(繁体中文-香港) -> zh-Hant(繁体中文)
     * 本方法不包含英文兜底的逻辑 !!!
     *
     * @return 如果返回null，说明已经是最顶级了，比如en、es等
     */
    public MultiLangType getFallback() {
        String fallback = this.code;

        if (CHINESE_TW.code.equals(fallback)) {
            //繁体中文需要特殊处理下,zh-tw
            return null;
        }

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
        return getLangType(lang);
    }

    public static MultiLangType getLangType(String lang) {
        if (lang == null) {
            return ENGLISH;
        }

        lang = lang.toLowerCase();
        lang = lang.replace("-", "_");

        MultiLangType type = CODE_LANGS.get(lang);
        if (type != null) {
            return type;
        }

        /*
           中文特殊处理下，繁体应该是zh-Hant,历史原因定义为了zh-tw，这里需要兼容下
         */
        if (lang.equalsIgnoreCase("zh") || lang.startsWith("zh_cn") || lang.startsWith("zh_hans")) {
            return CHINESE;
        } else if (lang.equalsIgnoreCase("zh_tw") || lang.equalsIgnoreCase("tw") || lang.startsWith("zh_hant")
                || lang.equalsIgnoreCase("zh_mo")) {
            return CHINESE_TW;
        } else if (lang.equalsIgnoreCase("zh_hk")) {
            return TRADITIONAL_CHINESE_HK;
        } else if (LATIN_AMERICA_SPANISH.contains(lang)) {
            return SPANISH_LATIN_AMERICA;
        }

        switch (lang) {
            case "tw":
            case "zh_rtw":
                return CHINESE_TW;
            case "tl_rph":
            case "fli":
                return FILIPINO;
            case "ko_kr":
            case "ko_rkr":
            case "ko_kp":
            case "ko_rkp":
                return KOREAN;
            case "iw":
                return HEBREW;
            case "in":
                return INDONESIAN;
            case "nb":
            case "nn":
                return NORWEGIAN;
            default:
                break;
        }

        int index = lang.indexOf("-");
        if (index == -1) {
            index = lang.indexOf("_");
        }
        if (index != -1) {

            String codeWithoutRegion = lang.substring(0, index);
            type = COMPATIBLE_CODE_LANGS.get(codeWithoutRegion);
            if (type != null) {
                return type;
            }

            type = CODE_LANGS.get(codeWithoutRegion);
            return type != null ? type : ENGLISH;
        }

        return ENGLISH;
    }

    public static int getLangId(String lang) {
        return getLangType(lang).getId();
    }

    public static int getMaxLangId() {
        return MAX_ID;
    }

    MultiLangType(int id, String name, String code, String englishName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.englishName = englishName;
        this.blank = true;
    }


    MultiLangType(int id, String name, String code, boolean blank, String englishName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.blank = blank;
        this.englishName = englishName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isBlank() {
        return blank;
    }

    public String getEnglishName() {
        return englishName;
    }
}
