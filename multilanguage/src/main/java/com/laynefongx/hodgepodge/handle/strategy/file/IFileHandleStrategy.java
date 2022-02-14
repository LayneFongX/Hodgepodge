package com.laynefongx.hodgepodge.handle.strategy.file;

import com.tuya.shendeng.domain.operate.FileResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;

public interface IFileHandleStrategy {

    int type();

    FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto);

    // FileResult compare(OperateData operateData, OperateConfigDto operateConfigDto);
    //
    // FileResult merge(OperateData operateData, OperateConfigDto operateConfigDto);
    //
    // FileResult update(OperateData operateData, OperateConfigDto operateConfigDto);
}
