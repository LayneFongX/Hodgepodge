package com.laynefongx.hodgepodge.handle.strategy.operate.biz;

import com.tuya.shendeng.domain.operate.LanguageOperateResult;
import com.tuya.shendeng.domain.operate.OperateData;
import com.tuya.shendeng.domain.request.OperateConfigDto;
import com.tuya.shendeng.enums.OperateType;
import com.tuya.shendeng.handle.strategy.operate.IOperateStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Component
public class FileMergeStrategy implements IOperateStrategy {
    @Override
    public int type() {
        return OperateType.MERGE.getType();
    }

    @Override
    public LanguageOperateResult handle(List<OperateData> dataList, OperateConfigDto operateConfigDto) {
        return null;
    }
}
