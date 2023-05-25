package com.pml.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/24
 * @description：配置项
 * @modifiedBy：
 * @version: 1.0
 */
@Configuration
public class UserSystemConfig {


    public static String driverClassName;

    public static String url;
    public static String username;

    public static String password;

    @Value(value="${user.system.driver-class-name}")
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Value(value="${user.system.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value(value="${user.system.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    @Value(value="${user.system.password}")
    public void setPassword(String password) {
        this.password = password;
    }
}
