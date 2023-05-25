package com.pml.cloud.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/23
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
public class ReqUserList implements Serializable {


    @Serial
    private static final long serialVersionUID = 1492754157084367339L;


    /**
     * 用户列表
     */
    @NotEmpty(message = "user list is not empty.")
    private List<ReqUser>  reqUserList;

}
