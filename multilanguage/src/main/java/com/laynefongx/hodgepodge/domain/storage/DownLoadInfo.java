package com.laynefongx.hodgepodge.domain.storage;

import lombok.Builder;
import lombok.Data;

/**
 * @author falcon
 * @since 2022/2/14
 */
@Data
@Builder
public class DownLoadInfo {

    /**
     * 下载链接
     * */
    private String downloadUrl;


}
