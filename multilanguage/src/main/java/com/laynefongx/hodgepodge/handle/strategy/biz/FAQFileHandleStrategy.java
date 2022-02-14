package com.laynefongx.hodgepodge.handle.strategy.biz;


import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageData;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.enums.OperateType;
import com.laynefongx.hodgepodge.handle.strategy.IFileHandleStrategy;
import com.laynefongx.hodgepodge.service.MultiLanguageIotDataHelperService;
import com.laynefongx.hodgepodge.service.MultiLanguageOperateHelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
    public int type() {
        return FileType.WISER_APP_FAQ.getType();
    }

    @Override
    public FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto) {
        // 获取文件path
        String filePath = operateData.getPath();
        // 获取文件名称
        String fileName = filePath.split("/")[2];
        // 获取sheet页数据
        List<ExcelSheetPage> sheetPages = operateData.getSheetPages();
        // 对于FAQ多语言来说目前没有多sheet页的情况
        ExcelSheetPage sheetPage = sheetPages.get(0);
        // 获取此次操作的语言
        List<String> languages = operateConfigDto.getLanguages();
        // 获取上传的Excel多语言数据
        List<SheetLineData> excelLanguageData = sheetPage.getSheetLineDataList();
        // 获取本次操作的FAQ类型
        String typeName = fileName.split("_")[0]; // WiserApp_FAQ/ElkoApp_FAQ/IPCarema_FAQ
        // 获取IoT平台多语言数据
        List<IotLanguageData> iotLanguageDataList =
                iotDataHelperService.getFAQIotLanguageDataByType(operateData.getFileType(), operateConfigDto.getEnv(), typeName);

        if (operate == OperateType.COMPARE.getType()) {
            return operateHelperService.getFileResult(filePath,
                    operateHelperService.compareLanguageDatas(operateData.getFileType().getType(), sheetPage, languages, excelLanguageData,
                            iotLanguageDataList));
        }

        if (operate == OperateType.MERGE.getType()) {
            return operateHelperService.getFileResult(filePath,
                    operateHelperService.mergeLanguageDatas(sheetPage, excelLanguageData, iotLanguageDataList));
        }

        return new FileResult();
    }

}
