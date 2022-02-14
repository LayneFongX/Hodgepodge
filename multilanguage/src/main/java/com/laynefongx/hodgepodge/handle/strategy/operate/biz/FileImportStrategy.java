package com.laynefongx.hodgepodge.handle.strategy.operate.biz;

import com.tuya.shendeng.domain.operate.LanguageOperateResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;
import com.tuya.shendeng.handle.strategy.operate.IOperateStrategy;

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
