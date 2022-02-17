package com.laynefongx.hodgepodge.handle.strategy.operate.biz;

import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.LanguageOperateResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.operate.OperateDetail;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.enums.ErrorType;
import com.laynefongx.hodgepodge.enums.OperateType;
import com.laynefongx.hodgepodge.handle.FileHandleSelector;
import com.laynefongx.hodgepodge.handle.OperateLogService;
import com.laynefongx.hodgepodge.handle.strategy.operate.IOperateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Slf4j
@Component
public class FileMergeStrategy implements IOperateStrategy {

    @Autowired
    private FileHandleSelector fileHandleSelector;

    @Resource
    private OperateLogService logService;


    @Override
    public int type() {
        return OperateType.MERGE.getType();
    }

    @Override
    public LanguageOperateResult handle(List<OperateData> dataList, OperateConfigDto operateConfigDto) {
        List<FileResult> fileResults = new ArrayList<>();
        for (OperateData operateData : dataList) {
            try {
                FileResult fileResult =
                        fileHandleSelector.handle(operateData.getFileType().getType(), OperateType.MERGE.getType(), operateData,
                                operateConfigDto);
                fileResults.add(fileResult);
            } catch (Exception e) {
                log.info("FileCompareStrategy handle exception : ", e);
                OperateDetail operateDetail =
                        OperateDetail.builder().operateId(operateData.getOperateId()).fileType(operateData.getFileType().getType())
                                .path(operateData.getPath()).build();
                operateDetail.setErrorCode(ErrorType.MERGE_FILES_HANDLE_ERROR.getError());
                operateDetail.setReason(ErrorType.MERGE_FILES_HANDLE_ERROR.getMessage());
                logService.saveLog(operateDetail);
            }
        }
        return new LanguageOperateResult(fileResults);
    }
}
