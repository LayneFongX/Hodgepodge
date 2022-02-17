package com.laynefongx.hodgepodge.handle;


import com.laynefongx.hodgepodge.domain.operate.OperateDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author falcon
 * @since 2022/2/14
 */
@Slf4j
@Component
public class OperateLogService {

    public static final int TIMEOUT = 60 * 60 * 2;

    public void saveLog(OperateDetail operateDetail) {

    }


}
