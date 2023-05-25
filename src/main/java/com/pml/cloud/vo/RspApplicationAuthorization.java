package com.pml.cloud.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/23
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class RspApplicationAuthorization {
    /**
     * 应用名称
     */

    private String AppName;

    /**
     * 应用英文名称，用于蓝图发布
     */

    private String AppEnName;
}
