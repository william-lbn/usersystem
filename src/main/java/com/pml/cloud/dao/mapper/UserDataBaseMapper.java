package com.pml.cloud.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pml.cloud.dao.po.AppInfo;
import com.pml.cloud.dao.po.ApplicationAuthorization;
import com.pml.cloud.dao.po.UserDataBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/28
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
public interface UserDataBaseMapper extends BaseMapper<UserDataBase> {

    List<UserDataBase> getUserDataBaseList(@Param("page") Integer page, @Param("size") Integer size);

    List<UserDataBase> getUserDataBaseListByTeamId(@Param("teamId") Long teamId, @Param("page") Integer page, @Param("size") Integer size);

    List<UserDataBase> getUserDataBaseListByTeamIdAndDeveloper(@Param("teamId") Long teamId, @Param("createUser") String createUser, @Param("page") Integer page, @Param("size") Integer size);
    int getUserDataBaseCountByTeamId(@Param("teamId") Long teamId);

    int getUserDataBaseCountByTeamIdAndDeveloper(@Param("teamId") Long teamId, @Param("createUser") String createUser);

    int getUserDataBaseCount();

    int insert(UserDataBase userDataBase);

    int update(UserDataBase userDataBase);

    int updateApplicationAuthorization(ApplicationAuthorization applicationAuthorization);

    List<ApplicationAuthorization> getApplicationAuthorizationListByUserDatabaseId(@Param("userDatabaseId") Long userDatabaseId, @Param("page") Integer page, @Param("size") Integer size);

    int getApplicationAuthorizationCountByUserDatabaseId(@Param("userDatabaseId") Long userDatabaseId);

}
