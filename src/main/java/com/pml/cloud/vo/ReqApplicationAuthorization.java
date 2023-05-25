package com.pml.cloud.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

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
@Tag(name="应用名称对象",description = "应用名称对象")
@Validated
public class ReqApplicationAuthorization {

    /**
     * 团队Id
     */

    @Parameter(name = "teamId", description = "团队Id", required = true)
    @NotBlank(message = "teamId is not blank.")
    @Schema(description = "团队Id", example = "123")
    private Long teamId;

    /**
     * 创建者名称
     */
    @Parameter(name = "userName", description = "开发人员", required = true)
    @NotBlank(message = "user name is not blank.")
    @Schema(description = "开发人员", example = "libin")
    private String userName;

    @Parameter(name = "appName", description = "应用名称", required = true)
    @NotBlank(message = "appName is not blank.")
    @Schema(description = "appName", example = "王者荣耀")
    private String appName;

    @Parameter(name = "appEnName", description = "appEnName", required = true)
    @NotBlank(message = "appEnName is not blank.")
    @Schema(description = "appEnName", example = "Honor of  Kings")
    private String appEnName;

}
