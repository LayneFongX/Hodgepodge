package com.laynefongx.hodgepodge.enums;

import org.springframework.util.ObjectUtils;

import java.util.Locale;


public enum LanguageEnums {
    CHINA("zh", new Locale("zh")),

    CHINA_TAIWAN("zh_tw", new Locale("zh_tw")),

    ENGLISH("en", new Locale("en"));


    private String language;

    private Locale locale;

    LanguageEnums(String language, Locale locale) {
        this.language = language;
        this.locale = locale;
    }

    public static Locale getLocaleByLanguage(String language) {
        if (ObjectUtils.isEmpty(language)) {
            return Locale.getDefault();
        }
        for (LanguageEnums languageEnums : LanguageEnums.values()) {
            if (languageEnums.language.equalsIgnoreCase(language)) {
                return languageEnums.locale;
            }
        }
        return Locale.getDefault();
    }


}
