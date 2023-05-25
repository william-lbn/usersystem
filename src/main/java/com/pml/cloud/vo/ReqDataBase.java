package com.pml.cloud.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

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
@Builder
@Tag(name="数据库开户对象",description = "数据库开户对象描述")
@Validated
public class ReqDataBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 295496130172555400L;

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

    /**
     * 服务标识
     */
    @Parameter(name = "sid", description = "服务标识", required = true)
    @NotBlank(message = "sid is not blank.")
    @Schema(description = "服务标识", example = "/hyperos/MySQL")
    private String sid;

    /**
     * 服务类型
     */
    @Parameter(name = "serviceType", description = "服务类型", required = true)
    @NotBlank(message = "service type is not blank.")
    @Schema(description = "服务类型 00Mysql 01Redis", allowableValues = {"00", "01"})
    private String serviceType;

    /**
     * 使用环境
     */
    @Parameter(name = "env", description = "使用环境", required = true)
    @NotBlank(message = "env is not blank.")
    @Schema(description = "使用环境 ", allowableValues = {"SIT", "PRD", "DEV"})
    private String env;

    /**
     * 数据库用户名
     */
    @Parameter(name = "dataBaseUser", description = "数据库用户名", required = true)
    @NotBlank(message = "dataBase user is not blank.")
    @Schema(description = "数据库开户名称", example = "test")
    private String dataBaseUser;

    /**
     * 数据库用户密码
     */
    @Parameter(name = "dataBaseSecret", description = "数据库密码", required = true)
    @NotBlank(message = "dataBase secret is not blank.")
    @Schema(description = "数据库开户密码，注意需要BASE64加密", example = "cG1sMTIzNDU2")
    private String dataBaseSecret;

}
