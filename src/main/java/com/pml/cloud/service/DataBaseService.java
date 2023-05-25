package com.pml.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pml.cloud.dao.po.User;
import com.pml.cloud.dao.po.UserDataBase;
import com.pml.cloud.vo.ReqApplicationAuthorization;
import com.pml.cloud.vo.ReqDataBase;
import com.pml.cloud.vo.RspApplicationAuthorization;
import com.pml.cloud.vo.RspDataBase;
import org.springframework.stereotype.Service;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/27
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Service
public interface DataBaseService {

    RspDataBase insert(ReqDataBase reqDataBase);

    RspDataBase update(ReqDataBase reqDataBase);

    Page<RspDataBase> getUserDataBaseListByTeamId(Long teamId, Integer pageNum, Integer pageSize);

    Page<RspDataBase> getUserDataBaseListByTeamIdAndDeveloper(Long teamId, String developer, Integer pageNum, Integer pageSize);

    void delete(Long id);

    void updateApplicationAuthorization(Long id, ReqApplicationAuthorization reqApplicationAuthorization);

    Page<RspApplicationAuthorization> getApplicationAuthorizationListById(Long id, Integer pageNum, Integer pageSize);
}
