package com.laynefongx.hodgepodge.handle;

import com.tuya.shendeng.domain.operate.FileResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;
import com.tuya.shendeng.handle.strategy.file.IFileHandleStrategy;
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

    // public FileResult compare(int fileType, OperateData operateData, OperateConfigDto operateConfigDto) {
    //     IFileHandleStrategy strategy = strategyMap.get(fileType);
    //     return Optional.ofNullable(strategy).map(s -> s.compare(operateData, operateConfigDto))
    //             .orElseGet(FileResult::new);
    // }
    //
    // public FileResult merge(int fileType, OperateData operateData, OperateConfigDto operateConfigDto) {
    //     IFileHandleStrategy strategy = strategyMap.get(fileType);
    //     return Optional.ofNullable(strategy).map(s -> s.merge(operateData, operateConfigDto))
    //             .orElseGet(FileResult::new);
    // }
    //
    // public FileResult update(int fileType, OperateData operateData, OperateConfigDto operateConfigDto) {
    //     IFileHandleStrategy strategy = strategyMap.get(fileType);
    //     return Optional.ofNullable(strategy).map(s -> s.update(operateData, operateConfigDto))
    //             .orElseGet(FileResult::new);
    // }
}
