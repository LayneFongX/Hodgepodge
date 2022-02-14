package com.laynefongx.hodgepodge.handle.strategy.operate.biz;

import com.tuya.shendeng.domain.operate.FileResult;
import com.tuya.shendeng.domain.operate.LanguageOperateResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;
import com.tuya.shendeng.enums.OperateType;
import com.tuya.shendeng.handle.FileHandleSelector;
import com.tuya.shendeng.handle.strategy.operate.IOperateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Component
public class FileCompareStrategy implements IOperateStrategy {

    @Autowired
    private FileHandleSelector fileHandleSelector;

    @Override
    public int type() {
        return OperateType.COMPARE.getType();
    }

    @Override
    public LanguageOperateResult handle(List<OperateData> dataList, OperateConfigDto operateConfigDto) {
        LanguageOperateResult languageOperateResult = new LanguageOperateResult();
        List<FileResult> fileResults = new ArrayList<>();
        for (OperateData operateData : dataList) {
            FileResult fileResult =
                    fileHandleSelector.handle(OperateType.COMPARE.getType(), operateData.getFileType().getType(), operateData,
                            operateConfigDto);
            fileResults.add(fileResult);
        }
        languageOperateResult.setFileResults(fileResults);
        return languageOperateResult;
    }
}
