package com.pml.cloud.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/16
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class RspDataBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 295496130172555400L;

    /**
     * id
     */
    private Long id;

    /**
     * 团队Id
     */
    private Long teamId;

    /**
     * 创建者名称
     */
    private String userName;

    /**
     * 服务标识
     */
    private String sid;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 使用环境
     */
    private String env;

    /**
     * 数据库用户名
     */
    private String dataBaseUser;

    /**
     * 数据库用户密码
     */
    private String dataBaseSecret;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
