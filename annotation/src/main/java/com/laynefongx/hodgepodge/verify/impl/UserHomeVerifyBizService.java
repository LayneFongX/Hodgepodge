package com.laynefongx.hodgepodge.verify.impl;

import com.laynefongx.hodgepodge.verify.VerifyBizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userHomeVerifyBizService")
public class UserHomeVerifyBizService implements VerifyBizService {

    @Override
    public void verifyParmas(String code) {
        // 此处校验参数满不满足条件
    }

    @Override
    public void verifyPermission(String code) {
        // 此处校验权限
    }
}
