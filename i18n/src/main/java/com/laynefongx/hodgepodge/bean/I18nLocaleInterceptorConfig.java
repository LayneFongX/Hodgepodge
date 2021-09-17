package com.laynefongx.hodgepodge.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

//@Configuration
public class I18nLocaleInterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 默认拦截器 会拦截请求中的lang属性
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(localeChangeInterceptor())
        //         .addPathPatterns("/**") // 拦截所有请求
        //         .excludePathPatterns("/static/**");  // 静态资源过滤
        // super.addInterceptors(registry);
    }
}