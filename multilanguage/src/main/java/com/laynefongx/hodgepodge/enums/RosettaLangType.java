package com.laynefongx.hodgepodge.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public enum RosettaLangType {
    CHINESE(1, "简体中文", "zh", false),
    CHINESE_TW(2, "繁体中文", "zh_tw", false),
    ENGLISH(3, "英语", "en"),
    GERMAN(4, "德语", "de"),
    ITALIAN(5, "意大利语", "it"),
    SPANISH(6, "西班牙语", "es"),
    FRENCH(7, "法语", "fr"),
    JAPANESE(8, "日语", "ja", false),
    KOREAN(9, "韩语", "ko"),
    HEBREW(10, "希伯来语", "he"),
    PORTUGUESE(11, "葡萄牙语", "pt"),
    THAI(12, "泰语", "th"),
    RUSSIAN(13, "俄语", "ru"),
    GREEK(14, "希腊语", "el"),
    POLISH(15, "波兰语", "pl"),
    INDONESIAN(16, "印尼语", "id"),
    MALAY(17, "马来语", "ms"),
    ARABIC(18, "阿拉伯语", "ar"),
    BULGARIAN(19, "保加利亚语", "bg"),
    CZECH(20, "捷克语", "cs"),
    DUTCH(21, "荷兰语", "nl"),
    TURKISH(22, "土耳其语", "tr"),
    HUNGARIAN(23, "匈牙利语", "hu"),
    VIETNAMESE(24, "越南语", "vi"),
    KAZAKH(25, "哈萨克语", "kk"),
    NORWEGIAN(26, "挪威语", "no"),
    DANISH(27, "丹麦语", "da"),
    FINNISH(28, "芬兰语", "fi"),
    SWEDISH(29, "瑞典语", "sv"),
    ROMANIAN(30, "罗马尼亚语", "ro"),
    HINDI(31, "印地语", "hi"),
    UKRAINIAN(32, "乌克兰语", "uk"),
    SLOVAK(33, "斯洛伐克语", "sk"),
    //    KOREAN_KR(34,"韩语","ko_kr"),
    BENGALI(35, "孟加拉语", "bn"),
    URDU(36, "乌尔都语", "ur"),
    UZBEK(37, "乌兹别克语", "uz"),
    UIGHUR(38, "维吾尔语", "ug"),
    MONGOLIAN(39, "蒙古语", "mn"),
    MACEDONIAN(40, "马其顿语", "mk"),
    FILIPINO(41, "菲律宾语", "tl"),
    CROATIAN(42, "克罗地亚语", "hr"),
    Burmese(43, "缅甸语", "my"),
    Lithuania(44, "立陶宛", "lt"),
    Serbian(45, "塞尔维亚语", "sr"),
    SLOVENIA(46, "斯洛文尼亚语", "sl"),

    ESTONIAN(47, "爱沙尼亚语", "et"),
    LATVIAN(48, "拉脱维亚语", "lv"),
    LAO(49, "老挝语", "lo"),
    HAUSA(50, "豪萨语", "ha"),
    PERSIAN(51, "波斯语", "fa"),
    BOSNIAN(52, "波斯尼亚语", "bs"),
    NEPALI(53, "尼泊尔语", "ne"),
    LATIN(54, "拉丁语", "la"),
    SWAHILI(55, "斯瓦希里语", "sw"),
    TELUGU(56, "泰卢固语", "te"),
    MARATHI(57, "马拉地语", "mr"),
    BELARUSIAN(58, "白俄罗斯语", "be"),
    KINYARWANDA(59, "卢旺达语", "rw"),
    XHOSA(60, "科萨语", "xh"),
    AZERBAIJANI(61, "阿塞拜疆语", "az"),
    KASHMIRI(62, "克什米尔语", "ks"),
    JAVANESE(63, "爪哇语", "jv"),
    TAMIL(64, "泰米尔语", "ta"),
    GUJARATI(65, "古吉特拉语", "gu"),

    /**
     *   以下几个语种带region、script，script应该为首字母大写，region应该为两位的大写字母或者3位的数字
     */
    ENGLISH_UNITED_KINGDOM(66, "英语(英国)", "en_GB"),
    TRADITIONAL_CHINESE_HK(67, "繁体中文(中国香港特别行政区)", "zh_Hant_HK"),
    PORTUGUESE_BRAZIL(68, "葡萄牙语(巴西)", "pt_BR"),
    SPANISH_LATIN_AMERICA(69, "西班牙语(拉丁美洲)", "es_419"),
    ARMENIAN(70, "亚美尼亚语", "hy"),
    ;

    private final int id;
    private final String name;
    private final String code;
    private final boolean blank;

    private final static Map<Integer, RosettaLangType> ID_LANGS = new HashMap<>();
    private final static Map<String, RosettaLangType> NAME_LANGS = new HashMap<>();
    private final static Map<String, RosettaLangType> CODE_LANGS = new HashMap<>();
    private final static Map<String, RosettaLangType> COMPATIBLE_CODE_LANGS = new HashMap<>();
    private static final Set<String> LATIN_AMERICA_SPANISH = new HashSet<>();
    private final static int MAX_ID;

    static {
        int maxId = 0;
        for (RosettaLangType type : RosettaLangType.values()) {
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

    public static RosettaLangType getByEnumName(String name) {
        for (RosettaLangType rosettaLangType : values()) {
            if (rosettaLangType.name().equals(name)) {
                return rosettaLangType;
            }
        }
        return null;
    }

    public static RosettaLangType get(int id) {
        //兼容朝鲜语
        if (id == 34) {
            return KOREAN;
        }
        return ID_LANGS.get(id);
    }

    public static RosettaLangType get(String name) {
        //兼容朝鲜语
        if ("朝鲜语".equals(name)) {
            return KOREAN;
        }
        return NAME_LANGS.get(name);
    }

    public static Map<Integer, RosettaLangType> getAll() {
        return new TreeMap<>(ID_LANGS);
    }

    /**
     * 转换成标准的语种代号，参考bcp47等规范
     *
     * @return
     */
    public static Optional<String> toStandardLangCode(int id) {
        RosettaLangType rosettaLangType = get(id);
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
    public RosettaLangType getFallback() {
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

    public static RosettaLangType getLangType(String lang) {
        if (lang == null) {
            return ENGLISH;
        }

        lang = lang.toLowerCase();
        lang = lang.replace("-", "_");

        RosettaLangType type = CODE_LANGS.get(lang);
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

    RosettaLangType(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.blank = true;
    }

    RosettaLangType(int id, String name, String code, boolean blank) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.blank = blank;
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
}