package com.pml.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pml.cloud.dao.mapper.AppInfoMapper;
import com.pml.cloud.dao.mapper.EnvInfoMapper;
import com.pml.cloud.dao.po.AppInfo;
import com.pml.cloud.dao.po.EnvInfo;
import com.pml.cloud.service.AppInfoService;
import com.pml.cloud.service.EnvInfoService;
import jakarta.annotation.Resource;
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
public class EnvInfoServiceImpl extends ServiceImpl<EnvInfoMapper, EnvInfo> implements EnvInfoService {


    @Resource
    private EnvInfoMapper envInfoMapper;

    @Override
    public List<EnvInfo> getEnvInfoAll(Long teamId) {


        return envInfoMapper.getEnvInfoAll(teamId);
    }
}
