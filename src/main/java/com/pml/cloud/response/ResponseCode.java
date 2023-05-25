package com.pml.cloud.response;

import lombok.Getter;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/21
 * @description：业务响应码语义
 * @modifiedBy：
 * @version: 1.0
 */
public enum ResponseCode {
    /**
     * 操作成功
     **/
    RC100(100, "Succeed."),

    /**
     * 数据库密码入库服务异常
     **/
    RC101(101, "The database password is inserted into the database service exception."),

    /**
     * 数据库密码更新插入数据库服务异常
     **/
    RC102(102, "Database password update insert database service exception."),

    /**
     * 数据库根据teamId查询数据库服务异常
     **/
    RC103(103, "Database query database service exception based on teamId."),

    /**
     * 数据库根据teamId、developer 查询数据库服务异常
     **/
    RC104(104, "Database query database service exception by teamId and developer."),

    /**
     * 数据库应用授权异常
     **/
    RC105(105, "Database application authorization exception."),

    /**
     * 数据库查询应用授权异常
     **/
    RC106(106, "Database query application authorization exception."),


    /**
     * 操作失败
     **/
    RC999(999, "Failed."),


    /**
     * access_denied
     **/
    RC403(403, "No access rights, please contact the administrator to grant permissions."),

    /**
     * access_denied
     **/
    RC401(401, "Exception when anonymous users access resources without permissions."),

    /**
     * 服务异常
     **/
    RC500(500, "The system is abnormal. Please try again later."),

    /**
     * 创建数据库服务异常
     **/
    RC501(501, "The system is abnormal. Please try again later."),



    ILLEGAL_ARGUMENT(3001, "Illegal parameter."),

    INVALID_TOKEN(2001, "The access token is illegal."),

    ACCESS_DENIED(2003, "You do not have permission to access this resource.");

    /**
     * 自定义状态码
     **/
    @Getter
    private final int code;
    /**
     * 自定义描述
     **/
    @Getter
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
