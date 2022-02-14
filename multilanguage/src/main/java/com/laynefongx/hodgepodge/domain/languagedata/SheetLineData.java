package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.Data;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/11
 */
@Data
public class SheetLineData {

    private String key;

    private IndexedColors lineColor = IndexedColors.WHITE;

    private List<ExcelLanguageItem> languageItems;
}
