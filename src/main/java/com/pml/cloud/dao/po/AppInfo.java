package com.pml.cloud.dao.po;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
@Schema(description = "应用响应消息体")
@ApiResponse
public class AppInfo {


    /**
     * 应用Id
     */
    @Parameter(name = "AppId", description = "应用Id", required = true)
    @Schema(description = "AppId", example = "123")
    private Long AppId;

    /**
     * 应用名称
     */
    @Parameter(name = "AppName", description = "应用名称", required = true)
    @Schema(description = "应用名称", example = "123")
    private String AppName;

    /**
     * 应用英文名称，用于蓝图发布
     */
    @Parameter(name = "AppEnName", description = "应用英文名称", required = true)
    @Schema(description = "AppEnName", example = "123")
    private String AppEnName;

    /**
     * 应用类型，根据代码库技术栈
     */
    @Parameter(name = "AppType", description = "应用类型", required = true)
    @Schema(description = "应用类型", example = "123")
    private String AppType;

    /**
     * 应用图标，base64
     */
    @Parameter(name = "AppIcon", description = "应用图标", required = true)
    @Schema(description = "应用图标", example = "123")
    private String AppIcon;

}
