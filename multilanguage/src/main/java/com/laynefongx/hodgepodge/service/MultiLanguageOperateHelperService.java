package com.laynefongx.hodgepodge.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefongx.hodgepodge.domain.languagedata.*;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
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

    /**
     * 多语言数据对比
     *
     * @param sheetPage
     * @param languages
     * @param excelLanguageData
     * @param iotLanguageDataList
     * @return
     */
    public List<ExcelSheetPage> compareLanguageDatas(int fileType, ExcelSheetPage sheetPage, List<String> languages,
                                                     List<SheetLineData> excelLanguageData, List<IotLanguageData> iotLanguageDataList) {

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
            // 数据对比规则
            compareHelperService.compareRules(fileType, key, languages, excelKey2LanguageValueMap, iotLanguageDataMap,
                    iotKey2LanguageValueMap,
                    iotKey2LanguageItemsMap);

        }
        List<SheetLineData> sheetLineDataList =
                JSONObject.parseObject(JSONObject.toJSONString(iotLanguageDataMap.values()), new TypeReference<>() {
                });
        sheetPage.setSheetLineDataList(sheetLineDataList);
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
    public List<ExcelSheetPage> mergeLanguageDatas(ExcelSheetPage sheetPage, List<SheetLineData> excelLanguageData,
                                                   List<IotLanguageData> iotLanguageDataList) {
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
        sheetPage.setSheetLineDataList(sheetLineDataList);
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
                    iotLanguageData.getLanguageItems().stream()
                            .collect(Collectors.toMap(IotLanguageItem::getLang, IotLanguageItem::getValue));

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
}
