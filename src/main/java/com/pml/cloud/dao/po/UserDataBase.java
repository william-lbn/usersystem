package com.pml.cloud.dao.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.ibatis.annotations.ConstructorArgs;

import java.util.Date;

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
public class UserDataBase {

    private Long id;

    private Long teamId;

    private String createUser;

    private String serviceType;

    private String sid;

    private String envName;

    private String mysqlUser;

    private String mysqlSecret;

    private String appName;

    private String appEnName;

    private int deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
