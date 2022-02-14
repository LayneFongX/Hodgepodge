package com.laynefongx.hodgepodge.handle;

import com.tuya.shendeng.domain.languagedata.ExcelSheetPage;
import com.tuya.shendeng.domain.operate.LanguageOperateResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;
import com.tuya.shendeng.enums.FileType;
import com.tuya.shendeng.handle.strategy.operate.IOperateStrategy;
import com.tuya.shendeng.utils.ExcelUtils;
import com.tuya.shendeng.utils.FileUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Component
public class OperateSelector {

    public static final String PREFIX = "All";
    public static final String SPLIT_REGEX = "/";
    private final Map<Integer, IOperateStrategy> strategyMap;

    public OperateSelector(List<IOperateStrategy> strategies) {
        strategyMap = strategies.stream().collect(Collectors.toMap(IOperateStrategy::type, strategy -> strategy));
    }

    public LanguageOperateResult handle(int operate, Map<String, byte[]> map, OperateConfigDto operateConfigDto) {
        List<OperateData> dataList = map.keySet().stream()
            .filter(key -> Objects.nonNull(map.get(key)) && FileUtils.isExcel(key))
            .map(key -> convertOperateData(key, map.get(key)))
            .collect(Collectors.toList());

        IOperateStrategy strategy = strategyMap.get(operate);
        return Optional.ofNullable(strategy).map(s -> s.handle(dataList, operateConfigDto)).orElseGet(LanguageOperateResult::new);
    }

    private OperateData convertOperateData(String path, byte[] bytes) {
        OperateData operateData = new OperateData();
        operateData.setPath(path);

        List<ExcelSheetPage> sheetPageList = ExcelUtils.parseExcel(path, bytes);
        operateData.setSheetPages(sheetPageList);

        String type = getTypeFromPath(path);
        FileType fileType = FileType.transform(type);
        operateData.setFileType(fileType);

        return operateData;
    }

    private String getTypeFromPath(String key) {
        String[] array = key.split(SPLIT_REGEX);
        String type = array[0];
        if (key.startsWith(PREFIX)) {
            type = array[1];
        }
        return type;
    }
}
