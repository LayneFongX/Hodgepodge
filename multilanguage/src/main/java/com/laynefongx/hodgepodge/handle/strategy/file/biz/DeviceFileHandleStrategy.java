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
            // List<IotLanguageData> iotLanguageDataList = JSONObject.parseObject(
            //         "[{\"key\":\"offlineHelp\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_link\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"obstructions\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"openBle\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_pleaseCheck\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"bluetoothShareTip\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"retest\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"internetAccess\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"checkHelp\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"appoffline\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_textLinkMore\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"detectPlease\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_textLinkAfter\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_checkHelps\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"alreadyKnow\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_alreadyOffline\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"openBleShareStep\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"deviceOfflineHelpNew\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_textLinkBefore\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"deviceOffline\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_linkFront\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"offline_moreHelp\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"bluetoothShare\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"openBleShare\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"backToHome\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"bluetoothOfflineTip\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"location\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"wifiBadTitle\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"openBlueTooth\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"},{\"key\":\"deviceOfflineHelp\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\"}],\"lineColor\":\"WHITE\"}]",
            //         new TypeReference<>() {
            //         });

            if (operate == OperateType.COMPARE.getType()) {
                List<ExcelSheetPage> compareExcelSheetPages =
                        operateHelperService.compareLanguageDatas(operateDetail, sheetPage, languageIds, excelLanguageData,
                                iotLanguageDataList);
                deviceExcelSheetPage.addAll(compareExcelSheetPages);
            }

            if (operate == OperateType.MERGE.getType()) {
                List<ExcelSheetPage> mergeExcelSheetPages =
                        operateHelperService.mergeLanguageDatas(operateDetail, sheetPage, excelLanguageData, iotLanguageDataList);
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
