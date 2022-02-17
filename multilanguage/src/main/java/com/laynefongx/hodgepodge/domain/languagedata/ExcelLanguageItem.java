package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.Data;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author falcon
 * @since 2022/2/11
 */
@Data
public class ExcelLanguageItem {

    /**
     * 语种
     * */
    private String lang;

    /**
     * 语种内容
     * */
    private String value;

    private IndexedColors cellColor = IndexedColors.WHITE;
}
