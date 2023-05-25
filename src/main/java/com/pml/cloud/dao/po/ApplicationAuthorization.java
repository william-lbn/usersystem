package com.pml.cloud.dao.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

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
public class ApplicationAuthorization {

    /**
     * 雪花算法，唯一Id
     */
    private Long id;

    /**
     * 关联t_os_user_database表中id
     */
    private Long userDatabaseId;

    /**
     * 已经授权的应用名称
     */
    private String appName;

    /**
     * 已经授权的应用英文名称，用于蓝图发布
     */
    private String appEnName;

    /**
     * 团队Id
     */
    private Long teamId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 数据库开户创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 数据库开户更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
