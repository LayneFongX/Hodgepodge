package com.laynefongx.hodgepodge.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ValidateParamsUtils {

    public static void validateBlankParam(String param, String value) {
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException("param name" + param + "is blank!");
        }
    }

    public static void validateNullParam(String param, Object value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("param name" + param + "is null!");
        }
    }
}
