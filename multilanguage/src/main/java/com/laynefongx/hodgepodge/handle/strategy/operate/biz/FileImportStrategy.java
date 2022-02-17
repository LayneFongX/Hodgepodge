package com.laynefongx.hodgepodge.handle.strategy.operate.biz;


import com.laynefongx.hodgepodge.domain.operate.LanguageOperateResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;
import com.laynefongx.hodgepodge.handle.strategy.operate.IOperateStrategy;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/11
 */
public class FileImportStrategy implements IOperateStrategy {
    @Override
    public int type() {
        return 0;
    }

    @Override
    public LanguageOperateResult handle(List<OperateData> dataList, OperateConfigDto operateConfigDto) {
        return null;
    }
}
