package com.laynefongx.hodgepodge.service;

import com.laynefongx.hodgepodge.constant.MultiLanguageConstant;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageData;
import com.laynefongx.hodgepodge.domain.languagedata.IotLanguageItem;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.enums.RosettaLangType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MultiLanguageCompareHelperService {

    /**
     * 数据对比
     *
     * @param key
     * @param languages
     * @param excelKey2LanguageValueMap
     * @param iotLanguageDataMap
     * @param iotKey2LanguageValueMap
     * @param iotKey2LanguageItemsMap
     */
    public void compareRules(int fileType, String key, List<String> languages, Map<String, Map<String, String>> excelKey2LanguageValueMap,
                             Map<String, IotLanguageData> iotLanguageDataMap,
                             Map<String, Map<String, String>> iotKey2LanguageValueMap,
                             Map<String, Map<String, IotLanguageItem>> iotKey2LanguageItemsMap) {
        Map<String, String> excelLanguageItemValueMap = excelKey2LanguageValueMap.get(key);
        Map<String, String> iotLanguageItemValueMap = iotKey2LanguageValueMap.get(key);
        if (!isContinue(fileType, excelLanguageItemValueMap, iotLanguageItemValueMap)) {
            return;
        }

        // 对比规则1: 循环找出每个文件（A）与IOT 平台上当前文件(B)中，相同key, 英文不同的text。并在B中整行标记为绿色
        compareRule1(fileType, key, excelLanguageItemValueMap, iotLanguageItemValueMap, iotKey2LanguageItemsMap);
        // 对比规则2: 循环找出IOT 平台上文件(B)中，英文不为空，选中国家语言对应部分为空的cell ，在B文件中标记为绿色。
        compareRule2(fileType, key, iotLanguageItemValueMap, languages, iotKey2LanguageValueMap, iotKey2LanguageItemsMap);

        // 规则1和规则2是改变语种对应的值的单元格的颜色 因此规则1和规则2处理后 需要更新下iotLanguageData
        updateIotLanguageDataMap(key, iotLanguageDataMap, iotKey2LanguageItemsMap);

        // 规则3和规则4是改变行的颜色
        // 对比规则3: 循环找出IOT 平台上新文件(B)，英文不为空，中文为空，复制到新生成D文件中整行标记为红色，并记录到log中。Log形式为（哪个app, 哪个类型文件，什么key, 中文为空）
        compareRule3(fileType, key, iotLanguageItemValueMap, iotLanguageDataMap);
        // 对比规则4: 循环找出IOT 平台上新文件(B)，中文不为空，英文为空，复制到新生成D文件中整行标记为红色，并记录到log中。Log形式为（哪个app, 哪个类型文件，什么key, 英文为空）
        compareRule4(fileType, key, iotLanguageItemValueMap, iotLanguageDataMap);
    }

    private Boolean isContinue(int fileType, Map<String, String> excelLanguageItemValueMap, Map<String, String> iotLanguageItemValueMap) {
        if (FileType.isFaq(fileType)) {
            if (!excelLanguageItemValueMap.containsKey(getTitleByLanguage(RosettaLangType.ENGLISH.getName())) ||
                    !excelLanguageItemValueMap.containsKey(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()))) {
                return Boolean.FALSE;
            }

            if (!iotLanguageItemValueMap.containsKey(getTitleByLanguage(RosettaLangType.ENGLISH.getName())) ||
                    !iotLanguageItemValueMap.containsKey(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()))) {
                return Boolean.FALSE;
            }

        } else {
            if (!excelLanguageItemValueMap.containsKey(RosettaLangType.ENGLISH.getName())) {
                return Boolean.FALSE;
            }

            if (!iotLanguageItemValueMap.containsKey(RosettaLangType.ENGLISH.getName())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }

    private String getTitleByLanguage(String language) {
        return MultiLanguageConstant.MULTI_LANGUAGE_FAQ_TITLE + "(" + language + ")";
    }

    private String getAnswerByLanguage(String language) {
        return MultiLanguageConstant.MULTI_LANGUAGE_FAQ_ANSWER + "(" + language + ")";
    }

    /**
     * 对比规则1: 循环找出每个文件（A）与IOT 平台上当前文件(B)中，相同key, 英文不同的text。并在B中整行标记为绿色
     *
     * @param key
     * @param excelLanguageItemValueMap
     * @param iotLanguageItemValueMap
     * @param iotKey2LanguageItemsMap
     */
    private void compareRule1(int fileType, String key, Map<String, String> excelLanguageItemValueMap,
                              Map<String, String> iotLanguageItemValueMap,
                              Map<String, Map<String, IotLanguageItem>> iotKey2LanguageItemsMap) {
        if (FileType.isFaq(fileType)) {
            String excelEnglishTitleValue = excelLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
            String excelEnglishAnswerValue = excelLanguageItemValueMap.get(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()));

            String iotEnglishTitleValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
            String iotEnglishAnswerValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));

            if (StringUtils.isBlank(excelEnglishTitleValue) || StringUtils.isBlank(excelEnglishAnswerValue)
                    || StringUtils.isBlank(iotEnglishTitleValue) || StringUtils.isBlank(iotEnglishAnswerValue)) {
                return;
            }

            if (!excelEnglishTitleValue.equals(excelEnglishAnswerValue)) {
                // 需要在数据中标记为绿色
                IotLanguageItem iotLanguageItem =
                        iotKey2LanguageItemsMap.get(key).get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
                iotLanguageItem.setCellColor(IndexedColors.GREEN);
                iotKey2LanguageItemsMap.get(key).put(getTitleByLanguage(RosettaLangType.ENGLISH.getName()), iotLanguageItem);
            }

            if (!excelEnglishAnswerValue.equals(iotEnglishAnswerValue)) {
                // 需要在数据中标记为绿色
                IotLanguageItem iotLanguageItem =
                        iotKey2LanguageItemsMap.get(key).get(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()));
                iotLanguageItem.setCellColor(IndexedColors.GREEN);
                iotKey2LanguageItemsMap.get(key).put(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()), iotLanguageItem);
            }
        } else {
            String excelEnglishValue = excelLanguageItemValueMap.get(RosettaLangType.ENGLISH.getName());
            String iotEnglishValue = iotLanguageItemValueMap.get(RosettaLangType.ENGLISH.getName());
            if (StringUtils.isBlank(iotEnglishValue) || StringUtils.isBlank(excelEnglishValue)) {
                return;
            }

            if (!excelEnglishValue.equals(iotEnglishValue)) {
                // 需要在数据中标记为绿色
                IotLanguageItem iotLanguageItem = iotKey2LanguageItemsMap.get(key).get(RosettaLangType.ENGLISH.getName());
                iotLanguageItem.setCellColor(IndexedColors.GREEN);
                iotKey2LanguageItemsMap.get(key).put(RosettaLangType.ENGLISH.getName(), iotLanguageItem);
            }
        }
    }

    /**
     * 对比规则2: 循环找出IOT 平台上文件(B)中，英文不为空，选中国家语言对应部分为空的cell ，在B文件中标记为绿色。
     *
     * @param fileType
     * @param key
     * @param iotLanguageItemValueMap
     * @param languages
     * @param iotKey2LanguageValueMap
     * @param iotKey2LanguageItemsMap
     */
    private void compareRule2(int fileType, String key, Map<String, String> iotLanguageItemValueMap, List<String> languages,
                              Map<String, Map<String, String>> iotKey2LanguageValueMap,
                              Map<String, Map<String, IotLanguageItem>> iotKey2LanguageItemsMap) {
        if (FileType.isFaq(fileType)) {
            String iotEnglishTitleValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
            String iotEnglishAnswerValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
            if (StringUtils.isBlank(iotEnglishTitleValue) || StringUtils.isBlank(iotEnglishAnswerValue)) {
                return;
            }
            // 获取所有的语种和值的数据集合
            Map<String, String> iotKeyLanguageItemsMap = iotKey2LanguageValueMap.get(key);
            // 根据选中的语种进行判断
            for (String selectLanguage : languages) {
                String titleLanguage = getTitleByLanguage(selectLanguage);
                String answerLanguage = getAnswerByLanguage(selectLanguage);

                // 选中国家语言对应部分为空的cell
                if (StringUtils.isNotBlank(iotKeyLanguageItemsMap.get(titleLanguage)) &&
                        StringUtils.isNotBlank(iotKeyLanguageItemsMap.get(answerLanguage))) {
                    continue;
                }
                IotLanguageItem iotLanguageTitleItem = iotKey2LanguageItemsMap.get(key).get(titleLanguage);
                iotLanguageTitleItem.setCellColor(IndexedColors.GREEN);
                iotKey2LanguageItemsMap.get(key).put(titleLanguage, iotLanguageTitleItem);

                IotLanguageItem iotLanguageAnswerItem = iotKey2LanguageItemsMap.get(key).get(answerLanguage);
                iotLanguageAnswerItem.setCellColor(IndexedColors.GREEN);
                iotKey2LanguageItemsMap.get(key).put(answerLanguage, iotLanguageAnswerItem);
            }

        } else {
            String iotEnglishValue = iotLanguageItemValueMap.get(RosettaLangType.ENGLISH.getName());
            // 平台英文不为空
            if (StringUtils.isBlank(iotEnglishValue)) {
                return;
            }
            // 获取所有的语种和值的数据集合
            Map<String, String> iotKeyLanguageItemsMap = iotKey2LanguageValueMap.get(key);
            // 根据选中的语种进行判断
            for (String selectLanguage : languages) {
                // 选中国家语言对应部分为空的cell
                if (StringUtils.isNotBlank(iotKeyLanguageItemsMap.get(selectLanguage))) {
                    continue;
                }
                IotLanguageItem iotLanguageItem = iotKey2LanguageItemsMap.get(key).get(selectLanguage);
                iotLanguageItem.setCellColor(IndexedColors.GREEN);
                iotKey2LanguageItemsMap.get(key).put(selectLanguage, iotLanguageItem);
            }
        }
    }

    /**
     * 对比规则3: 循环找出IOT 平台上新文件(B)，英文不为空，中文为空，复制到新生成D文件中整行标记为红色，并记录到log中。Log形式为（哪个app, 哪个类型文件，什么key, 中文为空）
     *
     * @param fileType
     * @param key
     * @param iotLanguageItemValueMap
     * @param iotLanguageDataMap
     */
    private void compareRule3(int fileType, String key, Map<String, String> iotLanguageItemValueMap,
                              Map<String, IotLanguageData> iotLanguageDataMap) {
        if (FileType.isFaq(fileType)) {
            String iotChineseTitleValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.CHINESE.getName()));
            String iotChineseAnswerValue = iotLanguageItemValueMap.get(getAnswerByLanguage(RosettaLangType.CHINESE.getName()));

            String iotEnglishTitleValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
            String iotEnglishAnswerValue = iotLanguageItemValueMap.get(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()));

            if ((StringUtils.isBlank(iotChineseTitleValue) && StringUtils.isNotBlank(iotEnglishTitleValue)) ||
                    StringUtils.isBlank(iotChineseAnswerValue) && StringUtils.isNotBlank(iotEnglishAnswerValue)) {
                iotLanguageDataMap.get(key).setLineColor(IndexedColors.RED);
            }
        } else {
            String iotChineseValue = iotLanguageItemValueMap.get(RosettaLangType.CHINESE.getName());
            String iotEnglishValue = iotLanguageItemValueMap.get(RosettaLangType.ENGLISH.getName());
            if (StringUtils.isBlank(iotChineseValue) && StringUtils.isNotBlank(iotEnglishValue)) {
                iotLanguageDataMap.get(key).setLineColor(IndexedColors.RED);
            }
        }
    }


    /**
     * 对比规则4: 循环找出IOT 平台上新文件(B)，中文不为空，英文为空，复制到新生成D文件中整行标记为红色，并记录到log中。Log形式为（哪个app, 哪个类型文件，什么key, 英文为空）
     *
     * @param fileType
     * @param key
     * @param iotLanguageItemValueMap
     * @param iotLanguageDataMap
     */
    private void compareRule4(int fileType, String key, Map<String, String> iotLanguageItemValueMap,
                              Map<String, IotLanguageData> iotLanguageDataMap) {

        if (FileType.isFaq(fileType)) {
            String iotChineseTitleValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.CHINESE.getName()));
            String iotChineseAnswerValue = iotLanguageItemValueMap.get(getAnswerByLanguage(RosettaLangType.CHINESE.getName()));

            String iotEnglishTitleValue = iotLanguageItemValueMap.get(getTitleByLanguage(RosettaLangType.ENGLISH.getName()));
            String iotEnglishAnswerValue = iotLanguageItemValueMap.get(getAnswerByLanguage(RosettaLangType.ENGLISH.getName()));

            if ((StringUtils.isBlank(iotEnglishTitleValue) && StringUtils.isNotBlank(iotChineseTitleValue)) ||
                    StringUtils.isBlank(iotEnglishAnswerValue) && StringUtils.isNotBlank(iotChineseAnswerValue)) {
                iotLanguageDataMap.get(key).setLineColor(IndexedColors.RED);
            }
        } else {
            String iotChineseValue = iotLanguageItemValueMap.get(RosettaLangType.CHINESE.getName());
            String iotEnglishValue = iotLanguageItemValueMap.get(RosettaLangType.ENGLISH.getName());
            if (StringUtils.isBlank(iotEnglishValue) && StringUtils.isNotBlank(iotChineseValue)) {
                iotLanguageDataMap.get(key).setLineColor(IndexedColors.RED);
            }
        }
    }

    /**
     * 更新处理后的LanguageItems
     *
     * @param key
     * @param iotLanguageDataMap
     * @param iotKey2LanguageItemsMap
     */
    private void updateIotLanguageDataMap(String key, Map<String, IotLanguageData> iotLanguageDataMap,
                                          Map<String, Map<String, IotLanguageItem>> iotKey2LanguageItemsMap) {
        IotLanguageData iotLanguageData = iotLanguageDataMap.get(key);
        iotLanguageData.setLanguageItems(new ArrayList<>(iotKey2LanguageItemsMap.get(key).values()));
    }
}
