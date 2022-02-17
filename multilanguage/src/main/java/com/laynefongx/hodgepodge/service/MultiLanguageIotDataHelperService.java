package com.laynefongx.hodgepodge.service;

import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageData;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageItem;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.domain.operate.OperateDetail;
import com.laynefongx.hodgepodge.enums.ErrorType;
import com.laynefongx.hodgepodge.enums.MultiLangI18nType;
import com.laynefongx.hodgepodge.enums.MultiLangType;
import com.laynefongx.hodgepodge.enums.ProductIotLanguage;
import com.laynefongx.hodgepodge.handle.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 从IOT平台获取多语言词条都在此处处理
 *
 * @author falcon
 * @since 2022/2/10
 */
@Slf4j
@Service
public class MultiLanguageIotDataHelperService {


    @Resource
    private OperateLogService logService;

    /**
     * 根据文件名称获取IoT平台多语言数据
     *
     * @param operateDetail
     * @return
     */
    public List<IotLanguageData> getAppIotLanguageDataByFileName(OperateDetail operateDetail, String env, List<Integer> languageIds,
                                                                 Set<String> itemCodesSet) {
        try {
            String fileName = operateDetail.getFileName();
            if (fileName.startsWith("export_i18n_app")) {
                // 对应设备配网列表
                return Collections.emptyList();
            } else if (fileName.startsWith("App")) {
                return Collections.emptyList();
            } else if (fileName.startsWith("System")) {
                // 对应的是App服务端语言包
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.info("MultiLanguageIotDataHelperService getAppIotLanguageDataByFileName exception : ", e);
            operateDetail.setErrorCode(ErrorType.GET_IOT_LANG_ERROR.getError());
            operateDetail.setReason(ErrorType.GET_IOT_LANG_ERROR.getMessage());
            logService.saveLog(operateDetail);
        }
        return Collections.emptyList();
    }

    /**
     * 根据设备pid和查询类型获取Iot平台多语言数据
     *
     * @param operateDetail
     * @param productId
     * @return
     */
    public List<IotLanguageData> getDeviceIotLanguageDataBySheetName(OperateDetail operateDetail, String productId,
                                                                     List<Integer> languageIds,
                                                                     Set<String> itemCodesSet) {
        try {
            String sheetName = operateDetail.getSheetName();
            if (!ProductIotLanguage.getEnglishNames().contains(sheetName)) {
                operateDetail.setErrorCode(ErrorType.DEVICE_SHEETNAME_ERROR.getError());
                operateDetail.setReason(ErrorType.DEVICE_SHEETNAME_ERROR.getMessage());
                logService.saveLog(operateDetail);
                return Collections.emptyList();
            }

            if (sheetName.equals(ProductIotLanguage.ALARM_INFO.getEnglishName())) {
                return getIotDevicePushNotificationLanguageDataMap(languageIds, itemCodesSet);
            }
            Map<String, Map<String, String>> iotDeviceLanguageDataMap =
                    getIotDeviceLanguageDataMap(sheetName, productId, languageIds, itemCodesSet);
            List<IotLanguageData> iotLanguageDataList = new ArrayList<>();
            for (String key : itemCodesSet) {
                IotLanguageData languageData = new IotLanguageData();
                languageData.setKey(key);
                List<IotLanguageItem> languageItems = new ArrayList<>();
                for (Map.Entry<String, Map<String, String>> entry : iotDeviceLanguageDataMap.entrySet()) {
                    String englishName = MultiLangType.getLangType(entry.getKey()).getEnglishName();
                    String languageValue = entry.getValue().get(key);
                    languageItems.add(new IotLanguageItem(englishName, languageValue, IndexedColors.WHITE));
                }
                languageData.setLanguageItems(languageItems);
                iotLanguageDataList.add(languageData);
            }
            return iotLanguageDataList;
        } catch (Exception e) {
            log.info("MultiLanguageIotDataHelperService getDeviceIotLanguageDataBySheetName exception : ", e);
            operateDetail.setErrorCode(ErrorType.GET_IOT_LANG_ERROR.getError());
            operateDetail.setReason(ErrorType.GET_IOT_LANG_ERROR.getMessage());
            logService.saveLog(operateDetail);
        }
        return Collections.emptyList();
    }

    /**
     * 获取IoT平台设备多语言数据
     *
     * @param sheetName
     * @param productId
     * @param languageIds
     * @param itemCodesSet
     * @return
     */
    private Map<String, Map<String, String>> getIotDeviceLanguageDataMap(String sheetName, String productId, List<Integer> languageIds,
                                                                         Set<String> itemCodesSet) {
        Map<String, Map<String, String>> iotDeviceLanguageDataMap = new HashMap<>();
        return iotDeviceLanguageDataMap;
    }

    /**
     * 获取IoT平台设备推送消息多语言数据
     *
     * @param languageIds
     * @param itemCodesSet
     * @return
     */
    private List<IotLanguageData> getIotDevicePushNotificationLanguageDataMap(List<Integer> languageIds, Set<String> itemCodesSet) {
        Map<String, Set<String>> itemCodesMap = new HashMap<>();
        return Collections.emptyList();
    }

    /**
     * 获取FAQ多语言
     *
     * @param operateDetail
     * @param env
     * @param typeName
     * @return
     */
    public List<IotLanguageData> getFAQIotLanguageDataByType(OperateDetail operateDetail, String env, String typeName) {
        int type = operateDetail.getFileType();
        return Collections.emptyList();
    }

    /**
     * 获取App多语言(设备列表)业务数据
     *
     * @return
     */
    private List<IotLanguageData> getAppRelateDeviceListIotLanguageData(OperateDetail operateDetail, String uid, Integer appId,
                                                                        List<Integer> languageIds) {
        List<IotLanguageData> iotLanguageDataList = new ArrayList<>();

        Map<String, Map<String, Map<String, String>>> iotLanguageData = getAppRelateDeviceListIotLanguageData(operateDetail, uid, appId);
        for (Integer multilangId : languageIds) {
            for (Map.Entry<String, Map<String, Map<String, String>>> iotLanguageEntry : iotLanguageData.entrySet()) {
                IotLanguageData languageData = new IotLanguageData();
                languageData.setKey(iotLanguageEntry.getKey());
                Map<String, String> languageDataMap = iotLanguageEntry.getValue().get("name");
                // 需要将新的语言id转为老的语言ID
                String i18n2LangId = MultiLangI18nType.getCompatibleLangEnum(MultiLangType.get(multilangId).getCode()).getId() + "";
                if (!languageDataMap.containsKey(i18n2LangId)) {
                    languageDataMap.put(i18n2LangId, "");
                }
                List<IotLanguageItem> languageItems = languageDataMap.entrySet().stream().map(x -> {
                    String i18nEnglishName = MultiLangI18nType.get(Integer.parseInt(x.getKey())).getShortName();
                    IotLanguageItem item = new IotLanguageItem();
                    item.setLang(i18nEnglishName);
                    item.setValue(x.getValue());
                    return item;
                }).collect(Collectors.toList());
                languageData.setLanguageItems(languageItems);
                iotLanguageDataList.add(languageData);
            }
        }
        return iotLanguageDataList;
    }

    /**
     * 获取App多语言(设备列表)源数据
     *
     * @param operateDetail
     * @param uid
     * @param appId
     * @return
     */
    private Map<String, Map<String, Map<String, String>>> getAppRelateDeviceListIotLanguageData(OperateDetail operateDetail, String uid,
                                                                                                Integer appId) {
        return new HashMap<>();
    }

    /**
     * 根据app Id获取客户端语言包的bucketId
     *
     * @param appId
     * @return
     */
    private long getAppClientBucketIdByAppId(Integer appId) {
        return 0l;
    }

    /**
     * 获取多语言词条
     *
     * @param sheetLineData
     * @return
     */
    public Set<String> getItemCodesSet(List<SheetLineData> sheetLineData) {
        return sheetLineData.stream().map(SheetLineData::getKey).collect(Collectors.toSet());
    }

}
