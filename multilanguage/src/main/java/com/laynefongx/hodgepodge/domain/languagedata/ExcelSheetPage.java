package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 每个sheet页解析之后的数据
 *
 * @author falcon
 * @since 2022/2/11
 */
@Data
public class ExcelSheetPage {

    private String sheetName;

    private Map<Integer, String> titleMap;

    private List<SheetLineData> sheetLineDataList;
}
