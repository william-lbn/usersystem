package com.pml.cloud.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pml.cloud.dao.po.AppInfo;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
public interface AppInfoMapper extends BaseMapper<AppInfo> {

    List<AppInfo> getAppInfoAll(Long teamId);

}
