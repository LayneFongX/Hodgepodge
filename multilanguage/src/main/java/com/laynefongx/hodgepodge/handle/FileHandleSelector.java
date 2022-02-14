package com.laynefongx.hodgepodge.handle;


import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.handle.strategy.IFileHandleStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class FileHandleSelector {

    private final Map<Integer, IFileHandleStrategy> strategyMap;

    public FileHandleSelector(List<IFileHandleStrategy> strategies) {
        strategyMap = strategies.stream().collect(Collectors.toMap(IFileHandleStrategy::type, strategy -> strategy));
    }

    public FileResult handle(int fileType, int operateType, OperateData operateData, OperateConfigDto operateConfigDto) {
        IFileHandleStrategy strategy = strategyMap.get(fileType);
        return Optional.ofNullable(strategy).map(s -> s.handle(operateType, operateData, operateConfigDto))
                .orElseGet(FileResult::new);
    }
}
