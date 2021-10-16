package com.laynefongx.hodgepodge.service.impl;

import com.laynefongx.hodgepodge.service.IAnnotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnnotationService implements IAnnotationService {

    @Override
    public String sayHello() {
        return "Hello Layne";
    }

}
