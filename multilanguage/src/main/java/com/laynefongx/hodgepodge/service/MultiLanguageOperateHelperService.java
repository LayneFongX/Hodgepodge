package com.laynefongx.hodgepodge.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefongx.hodgepodge.domain.languagedata.*;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateDetail;
import com.laynefongx.hodgepodge.enums.ErrorType;
import com.laynefongx.hodgepodge.handle.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 从IOT平台获取多语言词条都在此处处理
 *
 * @author falcon
 * @since 2022/2/10
 */
@Slf4j
@Service
@SuppressWarnings({"unchecked"})
public class MultiLanguageOperateHelperService {


    @Resource
    private MultiLanguageCompareHelperService compareHelperService;

    @Resource
    private OperateLogService logService;

    /**
     * 多语言数据对比
     *
     * @param sheetPage
     * @param languageIds
     * @param excelLanguageData
     * @param iotLanguageDataList
     * @return
     */
    public List<ExcelSheetPage> compareLanguageDatas(OperateDetail operateDetail, ExcelSheetPage sheetPage, List<Integer> languageIds,
                                                     List<SheetLineData> excelLanguageData, List<IotLanguageData> iotLanguageDataList) {

        try {
            // 封装Excel文件中的数据
            Map<String, Map<String, String>> excelKey2LanguageValueMap = getExcelKey2LanguageValueMap(excelLanguageData);
            // 封装IoT平台的文件数据
            Map<String, Object> iotLanguageMap = getIotLanguageMap(iotLanguageDataList);
            Map<String, IotLanguageData> iotLanguageDataMap = (Map<String, IotLanguageData>) iotLanguageMap.get("iotLanguageData");
            Map<String, Map<String, String>> iotKey2LanguageValueMap =
                    (Map<String, Map<String, String>>) iotLanguageMap.get("iotKey2LanguageValue");
            Map<String, Map<String, IotLanguageItem>> iotKey2LanguageItemsMap =
                    (Map<String, Map<String, IotLanguageItem>>) iotLanguageMap.get("iotKey2LanguageItems");

            // 进行对比
            for (String key : excelKey2LanguageValueMap.keySet()) {
                if (!iotKey2LanguageValueMap.containsKey(key)) {
                    continue;
                }
                operateDetail.setKey(key);
                // 数据对比规则
                compareHelperService.compareRules(operateDetail, languageIds, excelKey2LanguageValueMap, iotLanguageDataMap,
                        iotKey2LanguageValueMap, iotKey2LanguageItemsMap);

            }
            List<SheetLineData> sheetLineDataList =
                    JSONObject.parseObject(JSONObject.toJSONString(iotLanguageDataMap.values()), new TypeReference<>() {
                    });
            // 需要排序 根据key进行升序排序
            sheetLineDataList.sort(Comparator.comparing(SheetLineData::getKey));
            sheetPage.setSheetLineDataList(sheetLineDataList);
        } catch (Exception e) {
            log.info("MultiLanguageOperateHelperService compareLanguageDatas exception : ", e);
            operateDetail.setErrorCode(ErrorType.COMPARE_LANG_DATAS_ERROR.getError());
            operateDetail.setReason(ErrorType.COMPARE_LANG_DATAS_ERROR.getMessage());
            logService.saveLog(operateDetail);
        }
        return Collections.singletonList(sheetPage);
    }

    /**
     * 多语言数据合并
     *
     * @param sheetPage
     * @param excelLanguageData
     * @param iotLanguageDataList
     * @return
     */
    public List<ExcelSheetPage> mergeLanguageDatas(OperateDetail operateDetail, ExcelSheetPage sheetPage,
                                                   List<SheetLineData> excelLanguageData,
                                                   List<IotLanguageData> iotLanguageDataList) {
        try {
            // 封装Excel文件中的数据
            Map<String, Map<String, String>> excelKey2LanguageValueMap = getExcelKey2LanguageValueMap(excelLanguageData);
            // 封装IoT平台的文件数据
            Map<String, Object> iotLanguageMap = getIotLanguageMap(iotLanguageDataList);
            Map<String, IotLanguageData> iotLanguageDataMap = (Map<String, IotLanguageData>) iotLanguageMap.get("iotLanguageData");
            Map<String, Map<String, String>> iotKey2LanguageValueMap =
                    (Map<String, Map<String, String>>) iotLanguageMap.get("iotKey2LanguageValue");

            // 进行合并
            for (Map.Entry<String, Map<String, String>> excelEntry : excelKey2LanguageValueMap.entrySet()) {
                String excelKey = excelEntry.getKey();
                // 当前平台不存在的key不做处理
                if (!iotKey2LanguageValueMap.containsKey(excelKey)) {
                    continue;
                }

                Map<String, String> excelLanguageItemValueMap = excelEntry.getValue();
                Map<String, String> iotLanguageItemValueMap = iotKey2LanguageValueMap.get(excelKey);

                // 合并数据 数据不同的情况下 以excel文件中的数据覆盖iot平台的数据
                Map<String, String> mergeLanguageItemValueMap =
                        Stream.of(iotLanguageItemValueMap, excelLanguageItemValueMap).flatMap(map -> map.entrySet().stream())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key2));
                List<IotLanguageItem> mergeLanguageItemList = new ArrayList<>();
                mergeLanguageItemValueMap.forEach(
                        (key, value) -> mergeLanguageItemList.add(new IotLanguageItem(key, value, IndexedColors.WHITE)));
                iotLanguageDataMap.get(excelKey).setLanguageItems(mergeLanguageItemList);
            }
            List<SheetLineData> sheetLineDataList =
                    JSONObject.parseObject(JSONObject.toJSONString(iotLanguageDataMap.values()), new TypeReference<>() {
                    });
            // 根据key进行升序排序
            sheetLineDataList.sort(Comparator.comparing(SheetLineData::getKey));
            sheetPage.setSheetLineDataList(sheetLineDataList);
        } catch (Exception e) {
            log.info("MultiLanguageOperateHelperService mergeLanguageDatas exception : ", e);
            operateDetail.setErrorCode(ErrorType.MERGE_LANG_DATAS_ERROR.getError());
            operateDetail.setReason(ErrorType.MERGE_LANG_DATAS_ERROR.getMessage());
            logService.saveLog(operateDetail);
        }
        return Collections.singletonList(sheetPage);
    }


    /**
     * 处理Excel文件中的数据 Map的key为文件的key Map的value为Map<语种,语种对应的值>
     *
     * @param excelLanguageData
     * @return
     */
    public Map<String, Map<String, String>> getExcelKey2LanguageValueMap(List<SheetLineData> excelLanguageData) {
        Map<String, Map<String, String>> excelKey2LanguageValueMap = new LinkedHashMap<>();
        log.info("MultiLanguageOperateHelperService getExcelKey2LanguageValueMap excelLanguageData = {}",
                JSONObject.toJSONString(excelLanguageData)
        );
        for (SheetLineData excelData : excelLanguageData) {
            String uploadExcelKey = excelData.getKey();
            Map<String, String> iotLanguageItemsMap =
                    excelData.getLanguageItems().stream()
                            .collect(Collectors.toMap(ExcelLanguageItem::getLang, ExcelLanguageItem::getValue));
            excelKey2LanguageValueMap.put(uploadExcelKey, iotLanguageItemsMap);
        }
        return excelKey2LanguageValueMap;
    }

    /**
     * 处理IoT平台数据
     *
     * @param iotLanguageDataList
     * @return
     */
    public Map<String, Object> getIotLanguageMap(List<IotLanguageData> iotLanguageDataList) {
        Map<String, Object> iotLanguageMap = new HashMap<>();

        Map<String, IotLanguageData> iotLanguageDataMap = new LinkedHashMap<>();
        Map<String, Map<String, String>> iotKey2LanguageValueMap = new LinkedHashMap<>();
        Map<String, Map<String, IotLanguageItem>> iotKey2LanguageItemsMap = new LinkedHashMap<>();
        for (IotLanguageData iotLanguageData : iotLanguageDataList) {
            String iotKey = iotLanguageData.getKey();
            Map<String, String> iotLanguage2ValueMap =
                    iotLanguageData.getLanguageItems().stream().collect(Collectors.toMap(x -> x.getLang(), x -> x.getValue()));

            Map<String, IotLanguageItem> iotLanguageItemsMap =
                    iotLanguageData.getLanguageItems().stream()
                            .collect(Collectors.toMap(IotLanguageItem::getLang, Function.identity()));

            iotLanguageDataMap.put(iotKey, iotLanguageData);
            iotKey2LanguageValueMap.put(iotKey, iotLanguage2ValueMap);
            iotKey2LanguageItemsMap.put(iotKey, iotLanguageItemsMap);
        }
        iotLanguageMap.put("iotLanguageData", iotLanguageDataMap);
        iotLanguageMap.put("iotKey2LanguageValue", iotKey2LanguageValueMap);
        iotLanguageMap.put("iotKey2LanguageItems", iotKey2LanguageItemsMap);
        return iotLanguageMap;
    }


    /**
     * 获取数据处理结果
     *
     * @param path
     * @param resultItems
     * @return
     */
    public FileResult getFileResult(String path, List<ExcelSheetPage> resultItems) {
        FileResult fileResult = new FileResult();
        fileResult.setPath(path);
        fileResult.setResultItems(resultItems);
        return fileResult;
    }

    public OperateDetail getOperateDetail(String operateId, Integer fileType, String path, String fileName, String sheetName) {
        return OperateDetail.builder().operateId(operateId).fileType(fileType).path(path).fileName(fileName).sheetName(sheetName).build();
    }

    public OperateDetail getOperateDetail(String operateId, Integer fileType, String path, String fileName) {
        return OperateDetail.builder().operateId(operateId).fileType(fileType).path(path).fileName(fileName).build();
    }
}
