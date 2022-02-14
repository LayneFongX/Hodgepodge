package com.laynefongx.hodgepodge.service;

import com.laynefongx.hodgepodge.domain.languagedata.ExcelLanguageItem;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.enums.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Slf4j
@Service
public class MultiLanguageGetOperateDataHelperService {

    public OperateData getWiserOperateData() {
        OperateData operateData = new OperateData();
        operateData.setPath("ALL/Wiser App/AppLanguagePack_1");
        operateData.setFileType(FileType.WISER_APP);

        ExcelSheetPage excelSheetPage = new ExcelSheetPage();
        excelSheetPage.setSheetName("AppLanguagePack");

        SheetLineData sheetLineData1 = new SheetLineData();
        sheetLineData1.setKey("app_1");
        sheetLineData1.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "app_1中文"), new ExcelLanguageItem("ENGLISH", "app_1英文"),
                        new ExcelLanguageItem("JAPANESE", "app_1日语"), new ExcelLanguageItem("FRENCH", "app_1法语")));

        SheetLineData sheetLineData2 = new SheetLineData();
        sheetLineData2.setKey("app_2");
        sheetLineData2.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "app_2中文"), new ExcelLanguageItem("ENGLISH", "app_2英文"),
                        new ExcelLanguageItem("JAPANESE", "app_2日语"), new ExcelLanguageItem("FRENCH", "app_2法语")));

        SheetLineData sheetLineData3 = new SheetLineData();
        sheetLineData3.setKey("app_3");
        sheetLineData3.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "app_3中文"), new ExcelLanguageItem("ENGLISH", "app_3英文"),
                        new ExcelLanguageItem("JAPANESE", "app_3日语"), new ExcelLanguageItem("FRENCH", "app_3法语")));

        SheetLineData sheetLineData4 = new SheetLineData();
        sheetLineData4.setKey("app_4");
        sheetLineData4.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "app_4中文"), new ExcelLanguageItem("ENGLISH", "app_4英文"),
                        new ExcelLanguageItem("JAPANESE", "app_4日语"), new ExcelLanguageItem("FRENCH", "app_4法语")));

        SheetLineData sheetLineData5 = new SheetLineData();
        sheetLineData5.setKey("app_5");
        sheetLineData5.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "app_5中文"), new ExcelLanguageItem("ENGLISH", "app_5英文"),
                        new ExcelLanguageItem("JAPANESE", "app_5日语"), new ExcelLanguageItem("FRENCH", "app_5法语")));
        excelSheetPage.setSheetLineDataList(Arrays.asList(sheetLineData1, sheetLineData2, sheetLineData3, sheetLineData4, sheetLineData5));

        operateData.setSheetPages(Arrays.asList(excelSheetPage));
        return operateData;
    }

    public OperateData getDeviceOperateData() {
        OperateData operateData = new OperateData();
        operateData.setPath("ALL/Device Relate/export_i18n_r6ujohuyq1k4hf2h_1629863333425");
        operateData.setFileType(FileType.WISER_APP);

        ExcelSheetPage excelSheetPage1 = new ExcelSheetPage();
        excelSheetPage1.setSheetName("Data Point");

        SheetLineData sheetLineData1 = new SheetLineData();
        sheetLineData1.setKey("dp_1");
        sheetLineData1.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_1中文"), new ExcelLanguageItem("ENGLISH", "dp_1英文"),
                        new ExcelLanguageItem("JAPANESE", "dp_1日语"), new ExcelLanguageItem("FRENCH", "dp_1法语")));

        SheetLineData sheetLineData2 = new SheetLineData();
        sheetLineData2.setKey("dp_2");
        sheetLineData2.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_2中文"), new ExcelLanguageItem("ENGLISH", "dp_2英文"),
                        new ExcelLanguageItem("JAPANESE", "dp_2日语"), new ExcelLanguageItem("FRENCH", "dp_2法语")));

        SheetLineData sheetLineData3 = new SheetLineData();
        sheetLineData3.setKey("dp_3");
        sheetLineData3.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_3中文"), new ExcelLanguageItem("ENGLISH", "dp_3英文"),
                        new ExcelLanguageItem("JAPANESE", "dp_3日语"), new ExcelLanguageItem("FRENCH", "dp_3法语")));

        SheetLineData sheetLineData4 = new SheetLineData();
        sheetLineData4.setKey("dp_4");
        sheetLineData4.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_4中文"), new ExcelLanguageItem("ENGLISH", "dp_4英文"),
                        new ExcelLanguageItem("JAPANESE", "dp_4日语"), new ExcelLanguageItem("FRENCH", "dp_4法语")));


        SheetLineData sheetLineData5 = new SheetLineData();
        sheetLineData5.setKey("dp_5");
        sheetLineData5.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "dp_5中文"), new ExcelLanguageItem("ENGLISH", "dp_5英文"),
                        new ExcelLanguageItem("JAPANESE", "dp_5日语"), new ExcelLanguageItem("FRENCH", "dp_5法语")));
        excelSheetPage1.setSheetLineDataList(Arrays.asList(sheetLineData1, sheetLineData2, sheetLineData3, sheetLineData4, sheetLineData5));

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
                Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_2中文"), new ExcelLanguageItem("ENGLISH", "data panel_2英文"),
                        new ExcelLanguageItem("JAPANESE", "data panel_2日语"), new ExcelLanguageItem("FRENCH", "data panel_2法语")));

        SheetLineData sheetLineData8 = new SheetLineData();
        sheetLineData8.setKey("data panel_3");
        sheetLineData8.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_3中文"), new ExcelLanguageItem("ENGLISH", "data panel_3英文"),
                        new ExcelLanguageItem("JAPANESE", "data panel_3日语"), new ExcelLanguageItem("FRENCH", "data panel_3法语")));

        SheetLineData sheetLineData9 = new SheetLineData();
        sheetLineData9.setKey("data panel_4");
        sheetLineData9.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_4中文"), new ExcelLanguageItem("ENGLISH", "data panel_4英文"),
                        new ExcelLanguageItem("JAPANESE", "data panel_4日语"), new ExcelLanguageItem("FRENCH", "data panel_4法语")));

        SheetLineData sheetLineData10 = new SheetLineData();
        sheetLineData10.setKey("data panel_5");
        sheetLineData10.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("CHINESE", "data panel_5中文"), new ExcelLanguageItem("ENGLISH", "data panel_5英文"),
                        new ExcelLanguageItem("JAPANESE", "data panel_5日语"), new ExcelLanguageItem("FRENCH", "data panel_5法语")));
        excelSheetPage2.setSheetLineDataList(
                Arrays.asList(sheetLineData6, sheetLineData7, sheetLineData8, sheetLineData9, sheetLineData10));

        operateData.setSheetPages(Arrays.asList(excelSheetPage1, excelSheetPage2));
        return operateData;
    }

    public OperateData getFAQOperateData() {
        OperateData operateData = new OperateData();
        operateData.setPath("ALL/FAQ/WiserApp_1");
        operateData.setFileType(FileType.WISER_APP_FAQ);

        ExcelSheetPage excelSheetPage = new ExcelSheetPage();
        excelSheetPage.setSheetName("Wiser app Faq");

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
                        new ExcelLanguageItem("Knowledge title(ENGLISH)", "faq_2_title英文"),
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
                        new ExcelLanguageItem("Text Answer(JAPANESE)", "faq_3_answer日语"),
                        new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_3_title法语"),
                        new ExcelLanguageItem("Text Answer(FRENCH)", "faq_3_answer法语")));

        SheetLineData sheetLineData4 = new SheetLineData();
        sheetLineData4.setKey("faq_4");
        sheetLineData4.setLanguageItems(
                Arrays.asList(new ExcelLanguageItem("Knowledge title(CHINESE)", "faq_4_title中文"),
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
                        new ExcelLanguageItem("Text Answer(ENGLISH)", "faq_5_answer英文"),
                        new ExcelLanguageItem("Knowledge title(JAPANESE)", "faq_5_title日语"),
                        new ExcelLanguageItem("Text Answer(JAPANESE)", "faq_5_answer日语"),
                        new ExcelLanguageItem("Knowledge title(FRENCH)", "faq_5_title法语"),
                        new ExcelLanguageItem("Text Answer(FRENCH)", "faq_5_answer法语")));

        excelSheetPage.setSheetLineDataList(Arrays.asList(sheetLineData1, sheetLineData2, sheetLineData3, sheetLineData4, sheetLineData5));
        operateData.setSheetPages(Arrays.asList(excelSheetPage));
        return operateData;
    }
}
