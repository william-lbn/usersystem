package com.pml.cloud.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface EnvInfoMapper extends BaseMapper<EnvInfo> {

    List<EnvInfo> getEnvInfoAll(Long teamId);

}
