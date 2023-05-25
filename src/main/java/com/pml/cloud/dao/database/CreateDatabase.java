package com.pml.cloud.dao.database;

import com.pml.cloud.config.UserSystemConfig;
import com.pml.cloud.exception.UserSystemException;
import com.pml.cloud.response.ResponseCode;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/24
 * @description：创建数据库（使用用户指定的用户名和密码创建一个数据库实例）
 * @modifiedBy：
 * @version: 1.0
 */
@Component
@Slf4j
public class CreateDatabase {

    private Statement statement;

//    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        Class.forName(UserSystemConfig.driverClassName);

        Connection conn = DriverManager.getConnection(UserSystemConfig.url, UserSystemConfig.username, UserSystemConfig.password);

        this.statement = conn.createStatement();
    }

    public void createDatabase(String user, String passwd) {
        String sql = "create user '" + user + "'@'%' identified by '" + passwd + "';";
        String privilegesSql =  "grant all privileges on *.* to '" + user +"'@'%' identified by '" + passwd + "';";
        String flushSql = "flush privileges;";

        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(privilegesSql);
            statement.executeUpdate(flushSql);
        } catch (SQLException e) {
            log.error("create database error, [{}]",e);
            throw new UserSystemException(ResponseCode.RC501.getCode(), ResponseCode.RC501.getMessage());
        }
    }
}
