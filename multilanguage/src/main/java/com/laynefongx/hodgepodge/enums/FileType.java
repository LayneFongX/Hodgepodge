package com.laynefongx.hodgepodge.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum FileType {
    WISER_APP(1, "Wiser App"),
    ELKO_APP(2, "Elko App"),
    DEVICE_RELATED(3, "Device Related"),
    WISER_APP_FAQ(4, "Wiser App FAQ"),
    ELKO_APP_FAQ(5, "ELKO App FAQ"),
    DEVICE_FAQ(6, "Device FAQ"),
    ;

    private int type;
    private String name;

    FileType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static boolean isFaq(int fileType) {
        List<Integer> list = Arrays.stream(FileType.values()).filter(type -> type.getName()
            .contains("FAQ")).map(FileType::getType).collect(Collectors.toList());
        return list.contains(fileType);
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static FileType transform(String name) {
        return Arrays.stream(FileType.values())
            .filter(type -> Objects.equals(type.getName(), name))
            .findFirst().orElse(null);
    }
}
