package com.pml.cloud.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pml.cloud.dao.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：libin
 * @Package：com.pml.cloud.mapper
 * @Project：usersystem
 * @name：UserMapper
 * @Date：2023/3/20 17:35
 * @Filename：UserMapper
 */
public interface UserMapper extends BaseMapper<User> {

    int insert(User user);

    void update(User user);

    void delete(Long id);

    User getUser(Long id);

    List<User> getUserAll();
    List<User> getUserList(@Param("page") Integer page, @Param("size") Integer size);

    int getCurrentUserCount();

}
