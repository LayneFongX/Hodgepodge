package com.laynefongx.hodgepodge.domain.operate;

import com.tuya.shendeng.domain.languagedata.ExcelSheetPage;
import lombok.Data;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/11
 */
@Data
public class FileResult {
    /**
     * 压缩包路径
     * */
    private String path;

    /**
     * excel文件内容
     * */
    private List<ExcelSheetPage> resultItems;
}
