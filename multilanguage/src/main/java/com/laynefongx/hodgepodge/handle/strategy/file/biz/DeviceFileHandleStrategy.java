package com.laynefongx.hodgepodge.handle.strategy.file.biz;

import com.tuya.shendeng.domain.languagedata.ExcelSheetPage;
import com.tuya.shendeng.domain.languagedata.IotLanguageData;
import com.tuya.shendeng.domain.languagedata.SheetLineData;
import com.tuya.shendeng.domain.operate.FileResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;
import com.tuya.shendeng.enums.FileType;
import com.tuya.shendeng.enums.OperateType;
import com.tuya.shendeng.handle.strategy.file.IFileHandleStrategy;
import com.tuya.shendeng.service.helper.MultiLanguageIotDataHelperService;
import com.tuya.shendeng.service.helper.MultiLanguageOperateHelperService;
import lombok.extern.slf4j.Slf4j;
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
public class DeviceFileHandleStrategy implements IFileHandleStrategy {

    @Resource
    private MultiLanguageIotDataHelperService iotDataHelperService;

    @Resource
    private MultiLanguageOperateHelperService operateHelperService;

    @Override
    public int type() {
        return FileType.DEVICE_RELATED.getType();
    }

    @Override
    public FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto) {
        // 获取文件path
        String filePath = operateData.getPath();
        // 获取文件名称
        String fileName = filePath.split("/")[2];
        // 获取sheet页数据
        List<ExcelSheetPage> sheetPages = operateData.getSheetPages();
        // 获取产品pid
        String productId = getProductId(fileName, sheetPages.size());
        // 获取此次操作的语言
        List<String> languages = operateConfigDto.getLanguages();

        List<ExcelSheetPage> deviceExcelSheetPage = new ArrayList<>();
        for (ExcelSheetPage sheetPage : sheetPages) {
            // 获取上传的Excel多语言数据
            List<SheetLineData> excelLanguageData = sheetPage.getSheetLineDataList();
            // 获取IoT平台多语言数据
            List<IotLanguageData> iotLanguageDataList =
                    iotDataHelperService.getDeviceIotLanguageDataBySheetName(productId, sheetPage.getSheetName());

            if (operate == OperateType.COMPARE.getType()) {
                deviceExcelSheetPage.addAll(
                        operateHelperService.compareLanguageDatas(operateData.getFileType().getType(), sheetPage, languages,
                                excelLanguageData, iotLanguageDataList));
            }

            if (operate == OperateType.MERGE.getType()) {
                deviceExcelSheetPage.addAll(
                        operateHelperService.mergeLanguageDatas(sheetPage, excelLanguageData, iotLanguageDataList));
            }
        }
        return operateHelperService.getFileResult(filePath, deviceExcelSheetPage);
    }

    /**
     * 根据文件名称获取产品pid
     *
     * @param fileName
     * @param size
     * @return
     */
    private String getProductId(String fileName, int size) {
        if (size > 1) {
            // 如果是多sheet页
            return fileName.split("_")[2];
        } else {
            return fileName.split("_")[3];
        }
    }
}
