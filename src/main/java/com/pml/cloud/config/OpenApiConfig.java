package com.pml.cloud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/23
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info() //
                .title("紫金山实验室未来网络课题一自研用户系统接口文档") //
                .description("接口文档描述") //
                .version("0.0.1"));
    }

}
