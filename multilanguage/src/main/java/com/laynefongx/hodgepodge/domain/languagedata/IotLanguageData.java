package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.Data;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.List;

@Data
public class IotLanguageData {

    private String key;

    private IndexedColors lineColor = IndexedColors.WHITE;

    List<IotLanguageItem> languageItems;

}
