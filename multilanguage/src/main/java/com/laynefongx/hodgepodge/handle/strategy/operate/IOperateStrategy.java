package com.laynefongx.hodgepodge.handle.strategy.operate;


import com.laynefongx.hodgepodge.domain.operate.LanguageOperateResult;
import com.laynefongx.hodgepodge.domain.operate.OperateData;
import com.laynefongx.hodgepodge.domain.request.OperateConfigDto;

import java.util.List;

/**
 * @author falcon
 * @since 2022/2/10
 */
public interface IOperateStrategy {

    int type();

    LanguageOperateResult handle(List<OperateData> dataList, OperateConfigDto operateConfigDto);
}
