package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.Data;

/**
 * @author falcon
 * @since 2022/2/11
 */
@Data
public class ExcelFaqItem {

    private String lang;

    /**
     * FAQ问题
     * */
    private FaqTitle faqTitle;

    /**
     * 答案
     * */
    private FaqAnswer faqAnswer;


    @Data
    public static class FaqTitle {
        private String title;
        private Integer cellColor;
    }

    @Data
    public static class FaqAnswer {
        private String answer;
        private Integer cellColor;
    }
}
