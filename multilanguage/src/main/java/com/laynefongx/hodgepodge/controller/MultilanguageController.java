package com.laynefongx.hodgepodge.controller;


import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.enums.OperateType;
import com.laynefongx.hodgepodge.handle.FileHandleSelector;
import com.laynefongx.hodgepodge.service.MultiLanguageGetOperateDataHelperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/multilanguage")
public class MultilanguageController {

    @Resource
    private FileHandleSelector fileHandleSelector;

    @Resource
    private MultiLanguageGetOperateDataHelperService operateDataHelperService;


    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/getWiserAppCompareResult")
    public FileResult getWiserAppCompareResult() {
        return fileHandleSelector.handle(FileType.WISER_APP.getType(), OperateType.COMPARE.getType(),
                operateDataHelperService.getWiserOperateData(), getOperateConfigDto());
    }

    @GetMapping("/getDeviceCompareResult")
    public FileResult getDeviceCompareResult() {
        return fileHandleSelector.handle(FileType.DEVICE_RELATED.getType(), OperateType.COMPARE.getType(),
                operateDataHelperService.getDeviceOperateData(), getOperateConfigDto());
    }

    @GetMapping("/getFAQCompareResult")
    public FileResult getFAQCompareResult() {
        return fileHandleSelector.handle(FileType.WISER_APP_FAQ.getType(), OperateType.COMPARE.getType(),
                operateDataHelperService.getFAQOperateData(), getOperateConfigDto());
    }

    @GetMapping("/getWiserAppMergeResult")
    public FileResult getWiserAppMergeResult() {
        return fileHandleSelector.handle(FileType.WISER_APP.getType(), OperateType.MERGE.getType(),
                operateDataHelperService.getWiserOperateData(), getOperateConfigDto());
    }

    @GetMapping("/getDeviceMergeResult")
    public FileResult getDeviceMergeResult() {
        return fileHandleSelector.handle(FileType.DEVICE_RELATED.getType(), OperateType.MERGE.getType(),
                operateDataHelperService.getDeviceOperateData(), getOperateConfigDto());
    }

    @GetMapping("/getFAQMergeResult")
    public FileResult getFAQMergeResult() {
        return fileHandleSelector.handle(FileType.WISER_APP_FAQ.getType(), OperateType.MERGE.getType(),
                operateDataHelperService.getFAQOperateData(), getOperateConfigDto());
    }

    private OperateConfigDto getOperateConfigDto() {
        OperateConfigDto operateConfig = new OperateConfigDto();
        operateConfig.setEnv("Relase");
        operateConfig.setEditType(1);
        operateConfig.setLanguages(Arrays.asList("CHINESE", "ENGLISH", "JAPANESE", "FRENCH"));
        operateConfig.setFileTypes(Arrays.asList("1", "3", "4"));
        operateConfig.setProducts(Arrays.asList("device1", "device2"));
        return operateConfig;
    }
}
