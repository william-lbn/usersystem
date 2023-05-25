package com.pml.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pml.cloud.dao.po.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：libin
 * @Package：com.pml.cloud.service
 * @Project：usersystem
 * @name：UserService
 * @Date：2023/3/20 21:42
 * @Filename：UserService
 */
@Service
public interface UserService extends IService<User> {

    User insert(User user);

    void update(User user);

    void delete(Long id);

    User getUser(Long id);

    List<User> getUserAll();

    Page<User> getUserList(Integer pageNum, Integer pageSize);

}
