package com.laynefongx.hodgepodge.handle;

import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.handle.strategy.file.IFileHandleStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class FileHandleSelector {

    private final List<IFileHandleStrategy> strategies;

    public FileHandleSelector(List<IFileHandleStrategy> strategies) {
        this.strategies = strategies;
    }

    public FileResult handle(int fileType, int operateType, OperateData operateData, OperateConfigDto operateConfigDto) {
        Optional<IFileHandleStrategy> optional = strategies.stream()
                .filter(s -> s.type().contains(fileType)).findFirst();
        return optional.map(s -> s.handle(operateType, operateData, operateConfigDto)).orElseGet(FileResult::new);
    }
}
