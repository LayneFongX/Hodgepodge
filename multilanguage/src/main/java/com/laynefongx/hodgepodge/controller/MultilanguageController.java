package com.laynefongx.hodgepodge.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.languagedata.SheetLineData;
import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.enums.OperateType;
import com.laynefongx.hodgepodge.handle.FileHandleSelector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/multilanguage")
public class MultilanguageController {

    @Resource
    private FileHandleSelector fileHandleSelector;


    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }


    @GetMapping("/getDeviceCompareResult")
    public FileResult getDeviceCompareResult() {
        return fileHandleSelector.handle(FileType.DEVICE_RELATED.getType(), OperateType.COMPARE.getType(),
                getOperateData(), getOperateConfigDto());
    }

    private OperateConfigDto getOperateConfigDto() {
        String _a =
                "{\"env\":\"release\",\"fileTypes\":[\"Device Related\"],\"languageIds\":[1],\"products\":[\"IP Indoor Camera\",\"IP Outdoor Camera\"]}";
        return JSONObject.parseObject(_a, OperateConfigDto.class);
    }

    private OperateData getOperateData() {
        OperateData operateData = new OperateData();
        operateData.setPath("Multilanguage Files/Device Related/2G Switch/export_i18n_cnq6tb6p_1645421206617.xlsx");
        operateData.setFileType(FileType.DEVICE_RELATED);
        operateData.setOperateId("1");
        ExcelSheetPage sheetPage = new ExcelSheetPage();
        sheetPage.setSheetName("Data Point");
        sheetPage.setTitleMap(new HashMap<>());

        List<SheetLineData> sheetLineDataList = JSONObject.parseObject(
                "[{\"key\":\"dp_switch_1\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开关 1\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Switch 1\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สวิตช์ 1\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_switch_1_on\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开启\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"On\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"เปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_switch_1_off\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"关闭\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Off\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_switch_2\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开关 2\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Switch 2\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สวิตช์ 2\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_switch_2_on\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开启\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"On\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"เปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_switch_2_off\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"关闭\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Off\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_light_mode\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"背景灯模式\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"LED indicator Mode\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"โหมด Backlight\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_light_mode_reverse\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"与负载相反\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Reverse with load\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ย้อนกลับด้วยโหลด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_light_mode_consistent\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"与负载相同\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Consistent with load\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"พอดีกับโหลด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_light_mode_alwaysoff\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"常关\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Always off\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ปิดตลอด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_light_mode_alwayson\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"常亮\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Always on\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"เปิดตลอด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_luminance_level\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"背景灯亮度\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"LED indicator Brightness Level\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ระดับความสว่าง Backlight\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_luminance_level_40\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"亮\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"High\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"40%\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_luminance_level_20\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"微亮\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Low\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"20%\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_luminance_level_0\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"关闭\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Off\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"0%\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_indicator_color\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"背景灯颜色\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"LED indicator Color\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สี Backlight\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_indicator_color_white\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"白色\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"White\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สีขาว\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_indicator_color_blue\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"蓝色\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Blue\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สีฟ้า\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_hw_version\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"0.0\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"1.0\"}],\"lineColor\":\"WHITE\"},{\"key\":\"dp_hw_version_unit\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Danish\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Finnish\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Hungarian\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Romanian\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Svenska\",\"value\":\"\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"\"}],\"lineColor\":\"WHITE\"},{\"key\":\"quickop_dp_switch_2_on\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开启\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"On\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"เปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"quickop_dp_switch_1_on\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开启\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"On\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"เปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"quickop_dp_switch_2_off\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"关闭\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Off\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"quickop_dp_switch_1_off\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"关闭\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Off\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"ปิด\"}],\"lineColor\":\"WHITE\"},{\"key\":\"quickop_dp_switch_1\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开关 1\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Switch 1\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สวิตช์ 1\"}],\"lineColor\":\"WHITE\"},{\"key\":\"quickop_dp_switch_2\",\"languageItems\":[{\"cellColor\":\"WHITE\",\"lang\":\"Chinese\",\"value\":\"开关 2\"},{\"cellColor\":\"WHITE\",\"lang\":\"English\",\"value\":\"Switch 2\"},{\"cellColor\":\"WHITE\",\"lang\":\"Thai\",\"value\":\"สวิตช์ 2\"}],\"lineColor\":\"WHITE\"}]",
                new TypeReference<>() {
                });
        sheetPage.setSheetLineDataList(sheetLineDataList);
        operateData.setSheetPages(Collections.singletonList(sheetPage));
        return operateData;
    }
}
