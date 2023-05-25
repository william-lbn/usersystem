package com.pml.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pml.cloud.dao.po.EnvInfo;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
public interface EnvInfoService extends IService<EnvInfo> {

    List<EnvInfo> getEnvInfoAll(Long teamId);

}
