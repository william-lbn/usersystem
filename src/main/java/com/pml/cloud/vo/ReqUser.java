package com.pml.cloud.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/21
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
public class ReqUser implements Serializable {


    @Serial
    private static final long serialVersionUID = 3766421012610586186L;


    /**
     * 姓名
     */
    @NotEmpty(message = "name is not empty.")
    private String name;

    /**
     * 邮箱
     */
    @NotBlank(message = "Email is required.")
    private String email;


}
