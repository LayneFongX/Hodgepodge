package com.laynefongx.hodgepodge.domain.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiLanguagePreConfigLanguageVO implements Serializable {

    /**
     * 多语言名称
     */
    private String languageName;

    /**
     * 多语言Id
     */
    private Integer languageId;

}
