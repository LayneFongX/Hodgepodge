package com.laynefongx.hodgepodge.domain.operate;

import lombok.Data;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Data
public class LanguageOperateResult {
    /**
     * 记录每个文件处理完之后的结果
     * */
    private List<FileResult> fileResults;
}
