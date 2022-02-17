package com.laynefongx.hodgepodge.handle.strategy.file.biz;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Slf4j
@Component
public class FAQFileHandleStrategy implements IFileHandleStrategy {

    @Resource
    private MultiLanguageIotDataHelperService iotDataHelperService;

    @Resource
    private MultiLanguageOperateHelperService operateHelperService;

    @Override
    public List<Integer> type() {
        Integer[] data = {FileType.ELKO_APP_FAQ.getType(), FileType.ELKO_APP_FAQ.getType(),
                FileType.DEVICE_FAQ.getType()};
        return Stream.of(data).collect(Collectors.toList());
    }

    @Override
    public FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto) {
        // 获取文件path
        String filePath = operateData.getPath();
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // 获取sheet页数据
        List<ExcelSheetPage> sheetPages = operateData.getSheetPages();
        // 对于FAQ多语言来说目前没有多sheet页的情况
        ExcelSheetPage sheetPage = sheetPages.get(0);
        // 获取此次操作的语言
        List<Integer> languageIds = operateConfigDto.getLanguageIds();
        // 获取上传的Excel多语言数据
        List<SheetLineData> excelLanguageData = sheetPage.getSheetLineDataList();
        // 获取本次操作的FAQ类型
        String typeName = fileName.split("_")[0]; // WiserApp_FAQ/ElkoApp_FAQ/IPCarema_FAQ

        OperateDetail operateDetail =
                operateHelperService.getOperateDetail(operateData.getOperateId(), operateData.getFileType().getType(), filePath, fileName,
                        sheetPage.getSheetName());

        // 获取IoT平台多语言数据
        List<IotLanguageData> iotLanguageDataList =
                iotDataHelperService.getFAQIotLanguageDataByType(operateDetail, operateConfigDto.getEnv(), typeName);

        if (operate == OperateType.COMPARE.getType()) {
            return operateHelperService.getFileResult(filePath,
                    operateHelperService.compareLanguageDatas(operateDetail, sheetPage, languageIds, excelLanguageData,
                            iotLanguageDataList));
        }

        if (operate == OperateType.MERGE.getType()) {
            return operateHelperService.getFileResult(filePath,
                    operateHelperService.mergeLanguageDatas(operateDetail, sheetPage, excelLanguageData, iotLanguageDataList));
        }

        return new FileResult();
    }

}
