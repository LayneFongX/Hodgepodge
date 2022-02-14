package com.laynefongx.hodgepodge.domain.operate;

import com.tuya.shendeng.domain.languagedata.ExcelSheetPage;
import com.tuya.shendeng.enums.FileType;
import lombok.Data;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/11
 */
@Data
public class OperateData {

    private String path;

    private FileType fileType;

    private List<ExcelSheetPage> sheetPages;
}
