package com.laynefongx.hodgepodge.handle.strategy.file.biz;

import com.alibaba.fastjson.JSONObject;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageData;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.operate.OperateDetail;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.enums.OperateType;
import com.laynefongx.hodgepodge.handle.strategy.file.IFileHandleStrategy;
import com.laynefongx.hodgepodge.service.MultiLanguageIotDataHelperService;
import com.laynefongx.hodgepodge.service.MultiLanguageOperateHelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Slf4j
@Component
public class AppFileHandleStrategy implements IFileHandleStrategy {

    @Resource
    private MultiLanguageIotDataHelperService iotDataHelperService;

    @Resource
    private MultiLanguageOperateHelperService operateHelperService;

    @Override
    public List<Integer> type() {
        Integer[] data = {FileType.ELKO_APP.getType(), FileType.WISER_APP.getType()};
        return Stream.of(data).collect(Collectors.toList());
    }

    @Override
    public FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto) {
        // 获取文件path
        String filePath = operateData.getPath();
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // 获取文件类型
        int fileType = operateData.getFileType().getType();

        // 获取sheet页数据
        List<ExcelSheetPage> sheetPages = operateData.getSheetPages();
        // 对于App多语言来说目前没有多sheet页的情况
        ExcelSheetPage sheetPage = sheetPages.get(0);
        // 获取此次操作的语言
        List<Integer> languageIds = operateConfigDto.getLanguageIds();
        // 获取上传的Excel多语言数据
        List<SheetLineData> excelLanguageData = sheetPage.getSheetLineDataList();

        OperateDetail operateDetail =
                operateHelperService.getOperateDetail(operateData.getOperateId(), fileType, filePath, fileName, sheetPage.getSheetName());
        Set<String> itemCodesSet = iotDataHelperService.getItemCodesSet(excelLanguageData);
        // 获取IoT平台多语言数据
        List<IotLanguageData> iotLanguageDataList =
                iotDataHelperService.getAppIotLanguageDataByFileName(operateDetail, operateConfigDto.getEnv(), languageIds, itemCodesSet);
        log.info("AppFileHandleStrategy handle operate = {},operateDetail = {},operateConfigDto = {},itemCodesSet = {}," +
                        "iotLanguageDataList = {}", operate, JSONObject.toJSONString(operateDetail), JSONObject.toJSONString(operateConfigDto),
                JSONObject.toJSONString(itemCodesSet), JSONObject.toJSONString(iotLanguageDataList));

        if (operate == OperateType.COMPARE.getType()) {
            List<ExcelSheetPage> compareExcelSheetPages =
                    operateHelperService.compareLanguageDatas(operateDetail, sheetPage, languageIds, excelLanguageData,
                            iotLanguageDataList);

            log.info("AppFileHandleStrategy compare filePath = {},compareExcelSheetPages = {}", filePath,
                    JSONObject.toJSONString(compareExcelSheetPages));
            return operateHelperService.getFileResult(filePath, compareExcelSheetPages);

        }

        if (operate == OperateType.MERGE.getType()) {
            List<ExcelSheetPage> mergeExcelSheetPages =
                    operateHelperService.mergeLanguageDatas(operateDetail, sheetPage, excelLanguageData, iotLanguageDataList);

            log.info("AppFileHandleStrategy merge filePath = {},mergeExcelSheetPages = {}", filePath,
                    JSONObject.toJSONString(mergeExcelSheetPages));
            return operateHelperService.getFileResult(filePath, mergeExcelSheetPages);
        }

        return new FileResult();
    }
}
