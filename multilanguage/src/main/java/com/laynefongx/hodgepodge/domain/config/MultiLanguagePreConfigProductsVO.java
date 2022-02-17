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
public class MultiLanguagePreConfigProductsVO implements Serializable {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品id
     */
    private String pid;

}
