package com.laynefongx.hodgepodge.domain.config;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MultiLanguagePreConfigurationVO implements Serializable {

    /**
     * 多语言列表
     */
    List<MultiLanguagePreConfigLanguageVO> languageList;

    /**
     * 产品列表
     */
    List<MultiLanguagePreConfigProductsVO> productList;

}
