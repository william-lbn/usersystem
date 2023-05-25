package com.pml.cloud.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pml.cloud.dao.mapper.UserMapper;
import com.pml.cloud.dao.po.User;
import com.pml.cloud.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：libin
 * @Package：com.pml.cloud.service.impl
 * @Project：usersystem
 * @name：UserServiceImpl
 * @Date：2023/3/20 21:45
 * @Filename：UserServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User insert(User user) {
        userMapper.insert(user);
        return null;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> getUserAll() {
        return userMapper.getUserAll();
    }

    @Override
    public Page<User> getUserList(Integer page, Integer size) {

        Page<User> orderPage = initPage(page, size);
        page = (page - 1) * size;
        List<User> orderList = userMapper.getUserList(page, size);

        int userCount = userMapper.getCurrentUserCount();

        orderPage.setTotal(userCount);
        orderPage.setRecords(orderList);
        return orderPage;
    }


    private Page<User> initPage(Integer page, Integer size){
        Page<User> initPage = new Page<>();
        initPage.setPages(page);
        initPage.setSize(size);
        return initPage;
    }

}



