package com.laynefongx.hodgepodge.bean;


import com.laynefongx.hodgepodge.enums.LanguageEnums;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@Configuration
public class I18nLocaleConfig {

    /**
     * 默认解析器 其中locale表示默认语言
     */
    @Bean
    public LocaleResolver localeResolver() {
        I18nLocalResolver localeResolver = new I18nLocalResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    static class I18nLocalResolver extends AcceptHeaderLocaleResolver {

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            Locale defaultLocale = getDefaultLocale();
            if (defaultLocale != null && ObjectUtils.isEmpty(request.getHeader("i18n-lang"))) {
                return defaultLocale;
            }
            String language = request.getHeader("i18n-lang");
            Locale requestLocale = LanguageEnums.getLocaleByLanguage(language);
            List<Locale> supportedLocales = super.getSupportedLocales();
            if (supportedLocales.isEmpty() || supportedLocales.contains(requestLocale)) {
                return requestLocale;
            }
            Locale supportedLocale = findSupportedLocale(request, supportedLocales);
            if (supportedLocale != null) {
                return supportedLocale;
            }
            return (defaultLocale != null ? defaultLocale : requestLocale);
        }

        @Nullable
        private Locale findSupportedLocale(HttpServletRequest request, List<Locale> supportedLocales) {
            Enumeration<Locale> requestLocales = request.getLocales();
            Locale languageMatch = null;
            while (requestLocales.hasMoreElements()) {
                Locale locale = requestLocales.nextElement();
                if (supportedLocales.contains(locale)) {
                    if (languageMatch == null || languageMatch.getLanguage().equals(locale.getLanguage())) {
                        return locale;
                    }
                } else if (languageMatch == null) {
                    for (Locale candidate : supportedLocales) {
                        if (!StringUtils.hasLength(candidate.getCountry()) && candidate.getLanguage().equals(locale.getLanguage())) {
                            languageMatch = candidate;
                            break;
                        }
                    }
                }
            }
            return languageMatch;
        }
    }
}