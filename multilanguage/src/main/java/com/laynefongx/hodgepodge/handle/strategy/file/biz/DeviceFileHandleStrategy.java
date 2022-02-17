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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Slf4j
@Component
public class DeviceFileHandleStrategy implements IFileHandleStrategy {


    @Resource
    private MultiLanguageOperateHelperService operateHelperService;

    @Resource
    private MultiLanguageIotDataHelperService iotDataHelperService;

    @Override
    public List<Integer> type() {
        return Collections.singletonList(FileType.DEVICE_RELATED.getType());
    }

    @Override
    public FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto) {
        // 获取文件path
        String filePath = operateData.getPath();
        // 获取文件名称
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // 获取sheet页数据
        List<ExcelSheetPage> sheetPages = operateData.getSheetPages();
        // 获取产品pid
        String productId = getProductId(fileName, sheetPages.size());
        // 获取此次操作的语言
        List<Integer> languageIds = operateConfigDto.getLanguageIds();

        OperateDetail operateDetail =
                operateHelperService.getOperateDetail(operateData.getOperateId(), operateData.getFileType().getType(), filePath, fileName);

        List<ExcelSheetPage> deviceExcelSheetPage = new ArrayList<>();
        for (ExcelSheetPage sheetPage : sheetPages) {
            operateDetail.setSheetName(sheetPage.getSheetName());
            // 获取上传的Excel多语言数据
            List<SheetLineData> excelLanguageData = sheetPage.getSheetLineDataList();
            Set<String> itemCodesSet = iotDataHelperService.getItemCodesSet(excelLanguageData);
            // 获取IoT平台多语言数据
            List<IotLanguageData> iotLanguageDataList =
                    iotDataHelperService.getDeviceIotLanguageDataBySheetName(operateDetail, productId, languageIds, itemCodesSet);

            log.info("DeviceFileHandleStrategy handle operate = {},operateDetail = {},operateConfigDto = {},sheetName = {}," +
                            "itemCodesSet = {},iotLanguageDataList = {}", operate, JSONObject.toJSONString(operateDetail),
                    JSONObject.toJSONString(operateConfigDto), sheetPage.getSheetName(),
                    JSONObject.toJSONString(itemCodesSet), JSONObject.toJSONString(iotLanguageDataList));

            if (operate == OperateType.COMPARE.getType()) {
                List<ExcelSheetPage> compareExcelSheetPages =
                        operateHelperService.compareLanguageDatas(operateDetail, sheetPage, languageIds, excelLanguageData,
                                iotLanguageDataList);
                log.info("DeviceFileHandleStrategy compare compareExcelSheetPages = {}", JSONObject.toJSONString(compareExcelSheetPages));
                deviceExcelSheetPage.addAll(compareExcelSheetPages);
            }

            if (operate == OperateType.MERGE.getType()) {
                List<ExcelSheetPage> mergeExcelSheetPages =
                        operateHelperService.mergeLanguageDatas(operateDetail, sheetPage, excelLanguageData, iotLanguageDataList);
                log.info("DeviceFileHandleStrategy merge mergeExcelSheetPages = {}", JSONObject.toJSONString(mergeExcelSheetPages));
                deviceExcelSheetPage.addAll(mergeExcelSheetPages);
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
