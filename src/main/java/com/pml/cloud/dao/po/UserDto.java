package com.pml.cloud.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/24
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
@EqualsAndHashCode
public class UserDto extends User{

    /**
     * 分页：当前页
     */
    private long current;
    /**
     * 分页：每页数量
     */
    private long size;

}
