package com.laynefong.hodgepodge.domain.config;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MultiLanguagePreConfigurationVO implements Serializable {

    /**
     * 多语言列表
     */
    List<MultiLanguagePreConfigLanguageVO> languagesList;

    /**
     * 产品列表
     */
    List<MultiLanguagePreConfigProductsVO> productsList;

    /**
     * 多语言ID列表
     */
    List<Integer> languagesIdList;

    /**
     * 产品ID列表
     */
    List<String> productsIdList;

}
