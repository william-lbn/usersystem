package com.pml.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pml.cloud.dao.mapper.AppInfoMapper;
import com.pml.cloud.dao.mapper.UserMapper;
import com.pml.cloud.dao.po.AppInfo;
import com.pml.cloud.dao.po.User;
import com.pml.cloud.service.AppInfoService;
import com.pml.cloud.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Service
public class AppInfoServiceImpl extends ServiceImpl<AppInfoMapper, AppInfo> implements AppInfoService {


    @Resource
    private AppInfoMapper appInfoMapper;

    @Override
    public List<AppInfo> getAppInfoAll(Long teamId) {

        return appInfoMapper.getAppInfoAll(teamId);
    }
}
