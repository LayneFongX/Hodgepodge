package com.laynefongx.hodgepodge.service;

import com.laynefongx.hodgepodge.constant.MultiLanguageConstant;
import com.laynefongx.hodgepodge.domain.config.MultiLanguageIotAccountBO;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageData;
import com.laynefongx.hodgepodge.enums.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 根据文件名称获取IoT平台多语言数据
     *
     * @param appFileType 对应的是FileType中的app枚举type
     * @param fileName    对应的上传的文件名称 目前需要根据文件名称获取要查询app的多语言数据方式
     * @return
     */
    public List<IotLanguageData> getAppIotLanguageDataByFileName(FileType appFileType, String fileName) {
        // TODO 此处会将apptype传入
        // Integer appId = FileType.WISER_APP_FAQ.getType();
        if (fileName.startsWith("DeviceList")) {
            // 对应设备配网列表

        } else if (fileName.startsWith("AppLanguagePack")) {
            // 对应的是App语言包

        } else if (fileName.startsWith("SystemPrompt")) {
            // 对应的是系统提醒

        }
        return Collections.emptyList();
    }

    /**
     * 根据设备pid和查询类型获取Iot平台多语言数据
     *
     * @param productId
     * @param sheetName
     * @return
     */
    public List<IotLanguageData> getDeviceIotLanguageDataBySheetName(String productId, String sheetName) {
        if (sheetName.equals("Product Name")) {
            // 产品名称

        } else if (sheetName.equals("Data Point")) {
            // 产品功能

        } else if (sheetName.equals("Device Panel")) {
            // 设备面板

        } else if (sheetName.equals("Pairing Guide")) {
            // 配网指导

        } else if (sheetName.equals("Push")) {
            // 设备消息推送

        }
        return Collections.emptyList();
    }

    /**
     * 获取FAQ多语言
     *
     * @param fileType
     * @param env
     * @param typeName
     * @return
     */
    public List<IotLanguageData> getFAQIotLanguageDataByType(FileType fileType, String env, String typeName) {
        int type = fileType.getType();
        if (type == FileType.WISER_APP_FAQ.getType() || type == FileType.ELKO_APP_FAQ.getType()) {
            // Wiser app FAQ // Elko app FAQ
            return Collections.emptyList();
        } else if (type == FileType.DEVICE_FAQ.getType()) {
            String pid = getProductId(env, typeName);
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    /**
     * 根据产品名称和账号获取产品pid
     *
     * @param env
     * @param deviceName
     * @return
     */
    private String getProductId(String env, String deviceName) {
        Map<String, String> productName2PidMap = new HashMap<>();
        return productName2PidMap.getOrDefault(deviceName, "");
    }

    /**
     * 根据环境获取对应的IoT账号
     *
     * @param env
     * @return
     */
    private String getIotAccountByEnv(String env) {
        Map<String, String> multiLanguageIotAccountsMap =
                MultiLanguageConstant.multiLanguageIotAccounts.stream().collect(Collectors.toMap(MultiLanguageIotAccountBO::getEnv,
                        MultiLanguageIotAccountBO::getAccount, (key1, key2) -> key1));
        if (!multiLanguageIotAccountsMap.containsKey(env)) {
            throw new RuntimeException("Env: " + env + " is invalid , please check it");
        }
        return multiLanguageIotAccountsMap.get(env);
    }

}
