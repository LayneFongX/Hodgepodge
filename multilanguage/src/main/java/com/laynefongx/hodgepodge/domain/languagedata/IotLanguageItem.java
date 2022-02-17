package com.laynefongx.hodgepodge.domain.languagedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author falcon
 * @since 2022/2/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IotLanguageItem {

    /**
     * 语种
     */
    private String lang;

    /**
     * 语种内容
     */
    private String value;

    // TODO 此处应新加一个isChange 标识数据是否更新

    /**
     * 标记颜色
     */
    private IndexedColors cellColor = IndexedColors.WHITE;

}
