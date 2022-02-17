package com.laynefongx.hodgepodge.handle.strategy.file;


import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;

import java.util.List;

public interface IFileHandleStrategy {

    List<Integer> type();

    FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto);
}
