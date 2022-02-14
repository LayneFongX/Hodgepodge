package com.laynefongx.hodgepodge.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefongx.hodgepodge.constant.MultiLanguageConstant;
import com.laynefongx.hodgepodge.domain.config.MultiLanguageIotAccountBO;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelLanguageItem;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageData;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.enums.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            SheetLineData sheetLineData1 = new SheetLineData();
            sheetLineData1.setKey("app_1");
            sheetLineData1.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "app_1中文"), new ExcelLanguageItem("ENGLISH", "app_1英文"),
                            new ExcelLanguageItem("JAPANESE", "app_1日语"), new ExcelLanguageItem("FRENCH", "app_1法语")));

            SheetLineData sheetLineData2 = new SheetLineData();
            sheetLineData2.setKey("app_2");
            sheetLineData2.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "app_2中文"), new ExcelLanguageItem("ENGLISH", "app_2_rule_1英文"),
                            new ExcelLanguageItem("JAPANESE", "app_2日语"), new ExcelLanguageItem("FRENCH", "app_2法语")));

            SheetLineData sheetLineData3 = new SheetLineData();
            sheetLineData3.setKey("app_3");
            sheetLineData3.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "app_3中文"), new ExcelLanguageItem("ENGLISH", "app_3英文"),
                            new ExcelLanguageItem("JAPANESE", ""), new ExcelLanguageItem("FRENCH", "app_3法语")));

            SheetLineData sheetLineData4 = new SheetLineData();
            sheetLineData4.setKey("app_4");
            sheetLineData4.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", ""), new ExcelLanguageItem("ENGLISH", "app_4英文"),
                            new ExcelLanguageItem("JAPANESE", "app_4日语"), new ExcelLanguageItem("FRENCH", "app_4法语")));

            SheetLineData sheetLineData5 = new SheetLineData();
            sheetLineData5.setKey("app_5");
            sheetLineData5.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "app_5中文"), new ExcelLanguageItem("ENGLISH", ""),
                            new ExcelLanguageItem("JAPANESE", "app_5日语"), new ExcelLanguageItem("FRENCH", "app_5法语")));
            List<SheetLineData> sheetLineData =
                    Arrays.asList(sheetLineData1, sheetLineData2, sheetLineData3, sheetLineData4, sheetLineData5);

            return JSONObject.parseObject(JSONObject.toJSONString(sheetLineData), new TypeReference<>() {
            });

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
        // 对比规则1: 循环找出每个文件（A）与IOT 平台上当前文件(B)中，相同key, 英文不同的text。并在B中整行标记为绿色
        // 对比规则2: 循环找出IOT 平台上文件(B)中，英文不为空，选中国家语言对应部分为空的cell ，在B文件中标记为绿色。

        // 规则3和规则4是改变行的颜色
        // 对比规则3: 循环找出IOT 平台上新文件(B)，英文不为空，中文为空，复制到新生成D文件中整行标记为红色，并记录到log中。Log形式为（哪个app, 哪个类型文件，什么key, 中文为空）
        // 对比规则4: 循环找出IOT 平台上新文件(B)，中文不为空，英文为空，复制到新生成D文件中整行标记为红色，并记录到log中。Log形式为（哪个app, 哪个类型文件，什么key, 英文为空）
        if (sheetName.equals("Product Name")) {

        } else if (sheetName.equals("Data Point")) {
            // 产品功能
            // 产品名称
            SheetLineData sheetLineData1 = new SheetLineData();
            sheetLineData1.setKey("dp_1");
            sheetLineData1.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_1中文"), new ExcelLanguageItem("ENGLISH", "dp_1英文"),
                            new ExcelLanguageItem("JAPANESE", "dp_1日语"), new ExcelLanguageItem("FRENCH", "dp_1法语")));

            SheetLineData sheetLineData2 = new SheetLineData();
            sheetLineData2.setKey("dp_2");
            sheetLineData2.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_2中文"), new ExcelLanguageItem("ENGLISH", "dp_2_rule_1英文"),
                            new ExcelLanguageItem("JAPANESE", "dp_2日语"), new ExcelLanguageItem("FRENCH", "dp_2法语")));

            SheetLineData sheetLineData3 = new SheetLineData();
            sheetLineData3.setKey("dp_3");
            sheetLineData3.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_3中文"), new ExcelLanguageItem("ENGLISH", "dp_3英文"),
                            new ExcelLanguageItem("JAPANESE", ""), new ExcelLanguageItem("FRENCH", "dp_3法语")));

            SheetLineData sheetLineData4 = new SheetLineData();
            sheetLineData4.setKey("dp_4");
            sheetLineData4.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", ""), new ExcelLanguageItem("ENGLISH", "dp_4英文"),
                            new ExcelLanguageItem("JAPANESE", "dp_4日语"), new ExcelLanguageItem("FRENCH", "dp_4法语")));


            SheetLineData sheetLineData5 = new SheetLineData();
            sheetLineData5.setKey("dp_5");
            sheetLineData5.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_5中文"), new ExcelLanguageItem("ENGLISH", ""),
                            new ExcelLanguageItem("JAPANESE", "dp_5日语"), new ExcelLanguageItem("FRENCH", "dp_5法语")));

            List<SheetLineData> sheetLineData =
                    Arrays.asList(sheetLineData1, sheetLineData2, sheetLineData3, sheetLineData4, sheetLineData5);
            return JSONObject.parseObject(JSONObject.toJSONString(sheetLineData), new TypeReference<>() {
            });

        } else if (sheetName.equals("Device Panel")) {
            // 设备面板
            ExcelSheetPage excelSheetPage2 = new ExcelSheetPage();
            excelSheetPage2.setSheetName("Device Panel");

            SheetLineData sheetLineData6 = new SheetLineData();
            sheetLineData6.setKey("data panel_1");
            sheetLineData6.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_1中文"), new ExcelLanguageItem("ENGLISH", "data panel_1英文"),
                            new ExcelLanguageItem("JAPANESE", "data panel_1日语"), new ExcelLanguageItem("FRENCH", "data panel_1法语")));

            SheetLineData sheetLineData7 = new SheetLineData();
            sheetLineData7.setKey("data panel_2");
            sheetLineData7.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_2中文"),
                            new ExcelLanguageItem("ENGLISH", "data panel_2_rule_1英文"),
                            new ExcelLanguageItem("JAPANESE", "data panel_2日语"), new ExcelLanguageItem("FRENCH", "data panel_2法语")));

            SheetLineData sheetLineData8 = new SheetLineData();
            sheetLineData8.setKey("data panel_3");
            sheetLineData8.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_3中文"), new ExcelLanguageItem("ENGLISH", "data panel_3英文"),
                            new ExcelLanguageItem("JAPANESE", "data panel_3日语"), new ExcelLanguageItem("FRENCH", "")));

            SheetLineData sheetLineData9 = new SheetLineData();
            sheetLineData9.setKey("data panel_4");
            sheetLineData9.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", ""), new ExcelLanguageItem("ENGLISH", "data panel_4英文"),
                            new ExcelLanguageItem("JAPANESE", "data panel_4日语"), new ExcelLanguageItem("FRENCH", "data panel_4法语")));

            SheetLineData sheetLineData10 = new SheetLineData();
            sheetLineData10.setKey("data panel_5");
            sheetLineData10.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_5中文"), new ExcelLanguageItem("ENGLISH", ""),
                            new ExcelLanguageItem("JAPANESE", "data panel_5日语"), new ExcelLanguageItem("FRENCH", "data panel_5法语")));
            excelSheetPage2.setSheetLineDataList(
                    Arrays.asList(sheetLineData6, sheetLineData7, sheetLineData8, sheetLineData9, sheetLineData10));
            List<SheetLineData> sheetLineData =
                    Arrays.asList(sheetLineData6, sheetLineData7, sheetLineData8, sheetLineData9, sheetLineData10);
            return JSONObject.parseObject(JSONObject.toJSONString(sheetLineData), new TypeReference<>() {
            });
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
            SheetLineData sheetLineData1 = new SheetLineData();
            sheetLineData1.setKey("faq_1");
            sheetLineData1.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("Knowledge title(CHINESE)", "faq_1_title中文"),
                            new ExcelLanguageItem("Text Answer(CHINESE)", "faq_1_answer中文"),
                            new ExcelLanguageItem("Knowledge title(ENGLISH)", "faq_1_title英文"),
                            new ExcelLanguageItem("Text Answer(ENGLISH)", "faq_1_answer英文"),
                            new ExcelLanguageItem("Knowledge title(JAPANESE)", "faq_1_title日语"),
                            new ExcelLanguageItem("Text Answer(JAPANESE)", "faq_1_answer日语"),
                            new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_1_title法语"),
                            new ExcelLanguageItem("Text Answer(FRENCH)", "faq_1_answer法语")));

            SheetLineData sheetLineData2 = new SheetLineData();
            sheetLineData2.setKey("faq_2");
            sheetLineData2.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("Knowledge title(CHINESE)", "faq_2_title中文"),
                            new ExcelLanguageItem("Text Answer(CHINESE)", "faq_2_answer中文"),
                            new ExcelLanguageItem("Knowledge title(ENGLISH)", "faq_2_title_rules_1英文"),
                            new ExcelLanguageItem("Text Answer(ENGLISH)", "faq_2_answer英文"),
                            new ExcelLanguageItem("Knowledge title(JAPANESE)", "faq_2_title日语"),
                            new ExcelLanguageItem("Text Answer(JAPANESE)", "faq_2_answer日语"),
                            new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_2_title法语"),
                            new ExcelLanguageItem("Text Answer(FRENCH)", "faq_2_answer法语")));

            SheetLineData sheetLineData3 = new SheetLineData();
            sheetLineData3.setKey("faq_3");
            sheetLineData3.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("Knowledge title(CHINESE)", "faq_3_title中文"),
                            new ExcelLanguageItem("Text Answer(CHINESE)", "faq_3_answer中文"),
                            new ExcelLanguageItem("Knowledge title(ENGLISH)", "faq_3_title英文"),
                            new ExcelLanguageItem("Text Answer(ENGLISH)", "faq_3_answer英文"),
                            new ExcelLanguageItem("Knowledge title(JAPANESE)", "faq_3_title日语"),
                            new ExcelLanguageItem("Text Answer(JAPANESE)", ""),
                            new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_3_title法语"),
                            new ExcelLanguageItem("Text Answer(FRENCH)", "faq_3_answer法语")));

            SheetLineData sheetLineData4 = new SheetLineData();
            sheetLineData4.setKey("faq_4");
            sheetLineData4.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("Knowledge title(CHINESE)", ""),
                            new ExcelLanguageItem("Text Answer(CHINESE)", "faq_4_answer中文"),
                            new ExcelLanguageItem("Knowledge title(ENGLISH)", "faq_4_title英文"),
                            new ExcelLanguageItem("Text Answer(ENGLISH)", "faq_4_answer英文"),
                            new ExcelLanguageItem("Knowledge title(JAPANESE)", "faq_4_title日语"),
                            new ExcelLanguageItem("Text Answer(JAPANESE)", "faq_4_answer日语"),
                            new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_4_title法语"),
                            new ExcelLanguageItem("Text Answer(FRENCH)", "faq_4_answer法语")));

            SheetLineData sheetLineData5 = new SheetLineData();
            sheetLineData5.setKey("faq_5");
            sheetLineData5.setLanguageItems(
                    Arrays.asList(new ExcelLanguageItem("Knowledge title(CHINESE)", "faq_5_title中文"),
                            new ExcelLanguageItem("Text Answer(CHINESE)", "faq_5_answer中文"),
                            new ExcelLanguageItem("Knowledge title(ENGLISH)", "faq_5_title英文"),
                            new ExcelLanguageItem("Text Answer(ENGLISH)", ""),
                            new ExcelLanguageItem("Knowledge title(JAPANESE)", "faq_5_title日语"),
                            new ExcelLanguageItem("Text Answer(JAPANESE)", "faq_5_answer日语"),
                            new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_5_title法语"),
                            new ExcelLanguageItem("Text Answer(FRENCH)", "faq_5_answer法语")));

            List<SheetLineData> sheetLineData =
                    Arrays.asList(sheetLineData1, sheetLineData2, sheetLineData3, sheetLineData4, sheetLineData5);
            return JSONObject.parseObject(JSONObject.toJSONString(sheetLineData), new TypeReference<>() {
            });
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
