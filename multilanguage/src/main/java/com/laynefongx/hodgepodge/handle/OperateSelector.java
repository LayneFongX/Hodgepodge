package com.laynefongx.hodgepodge.handle;

import com.laynefongx.hodgepodge.domain.languagedata.ExcelSheetPage;
import com.laynefongx.hodgepodge.domain.operate.ExecuteTask;
import com.laynefongx.hodgepodge.domain.operate.LanguageOperateResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.enums.FileType;
import com.laynefongx.hodgepodge.handle.strategy.operate.IOperateStrategy;
import com.laynefongx.hodgepodge.utils.ExcelUtils;
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

    public static final String PREFIX_ALL = "All";
    public static final String SPLIT_REGEX = "/";
    public static final String PREFIX_MULTI = "Multilanguage Files";
    private final Map<Integer, IOperateStrategy> strategyMap;

    public OperateSelector(List<IOperateStrategy> strategies) {
        strategyMap = strategies.stream().collect(Collectors.toMap(IOperateStrategy::type, strategy -> strategy));
    }

    public LanguageOperateResult handle(ExecuteTask executeTask) {
        Map<String, byte[]> map = executeTask.getUnZipMap();
        List<OperateData> dataList = map.keySet().stream()
                .filter(key -> Objects.nonNull(map.get(key)) && Boolean.TRUE)
                .map(key -> convertOperateData(executeTask.getOperateId(), key, map.get(key)))
                .collect(Collectors.toList());

        IOperateStrategy strategy = strategyMap.get(executeTask.getOperate());
        return Optional.ofNullable(strategy).map(s -> s.handle(dataList, executeTask.getConfigDto())).orElseGet(LanguageOperateResult::new);
    }

    private OperateData convertOperateData(String operateId, String path, byte[] bytes) {
        OperateData operateData = new OperateData();
        operateData.setPath(path);
        operateData.setOperateId(operateId);

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
        if (key.startsWith(PREFIX_ALL) || key.startsWith(PREFIX_MULTI)) {
            type = array[1];
        }
        return type;
    }
}
