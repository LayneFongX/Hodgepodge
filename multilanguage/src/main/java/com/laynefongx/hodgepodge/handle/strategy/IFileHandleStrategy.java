package com.laynefongx.hodgepodge.handle.strategy;


import com.laynefongx.hodgepodge.domain.operate.FileResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;

public interface IFileHandleStrategy {

    int type();

    FileResult handle(int operate, OperateData operateData, OperateConfigDto operateConfigDto);
}
