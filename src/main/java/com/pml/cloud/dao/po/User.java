package com.pml.cloud.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pml.cloud.customvalidator.EnumString;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：libin
 * @Package：com.pml.cloud.entity
 * @Project：usersystem
 * @name：User
 * @Date：2023/3/20 17:30
 * @Filename：User
 */
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @EnumString({"1", "2"})
    private String name;

    /**
     * 邮箱
     */
    private String email;

}
