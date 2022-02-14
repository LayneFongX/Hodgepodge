package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Data
@AllArgsConstructor
public class IotLanguageItem {

    /**
     * 语种
     */
    private String lang;

    /**
     * 语种内容
     */
    private String value;

    /**
     * 标记颜色
     */
    private IndexedColors cellColor = IndexedColors.WHITE;

}
