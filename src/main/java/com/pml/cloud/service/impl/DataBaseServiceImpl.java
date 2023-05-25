package com.pml.cloud.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pml.cloud.config.UserSystemConfig;
import com.pml.cloud.dao.mapper.UserDataBaseMapper;
import com.pml.cloud.dao.po.ApplicationAuthorization;
import com.pml.cloud.dao.po.User;
import com.pml.cloud.dao.po.UserDataBase;
import com.pml.cloud.exception.UserSystemException;
import com.pml.cloud.response.ResponseCode;
import com.pml.cloud.service.DataBaseService;
import com.pml.cloud.utils.generateid.SnowflakeIdWorker;
import com.pml.cloud.vo.ReqApplicationAuthorization;
import com.pml.cloud.vo.ReqDataBase;
import com.pml.cloud.vo.RspApplicationAuthorization;
import com.pml.cloud.vo.RspDataBase;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/4
 * @description：数据库创建
 * @modifiedBy：
 * @version: 1.0
 */
@Service
@Slf4j
public class DataBaseServiceImpl implements DataBaseService {


    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private UserDataBaseMapper userDataBaseMapper;

    /**
     * 创建数据库
     * @param reqDataBase 数据库传入请求
     * @return 用户数据库对象
     */
    @Override
    public RspDataBase insert(ReqDataBase reqDataBase) {

        long id = snowflakeIdWorker.getId();

        Date dateTime = new Date();

        UserDataBase userDataBase = UserDataBase.builder().id(id)
                .teamId(reqDataBase.getTeamId())
                .createUser(reqDataBase.getUserName())
                .sid(reqDataBase.getSid())
                .serviceType(reqDataBase.getServiceType())
                .envName(reqDataBase.getEnv())
                .mysqlUser(reqDataBase.getDataBaseUser())
                .mysqlSecret(reqDataBase.getDataBaseSecret())
                .deleteFlag(0)
                .createTime(dateTime)
                .updateTime(dateTime)
                .build();

        userDataBaseMapper.insert(userDataBase);

        return RspDataBase.builder()
                .id(id)
                .teamId(reqDataBase.getTeamId())
                .userName(reqDataBase.getUserName())
                .sid(reqDataBase.getSid())
                .serviceType(reqDataBase.getServiceType())
                .env(reqDataBase.getEnv())
                .dataBaseUser(reqDataBase.getDataBaseUser())
                .dataBaseSecret(reqDataBase.getDataBaseSecret())
                .createTime(dateTime)
                .updateTime(dateTime)
                .build();
    }

    @Override
    public RspDataBase update(ReqDataBase reqDataBase) {

        Date dateTime = new Date();

        UserDataBase userDataBase = UserDataBase.builder()
                .teamId(reqDataBase.getTeamId())
                .createUser(reqDataBase.getUserName())
                .sid(reqDataBase.getSid())
                .serviceType(reqDataBase.getServiceType())
                .envName(reqDataBase.getEnv())
                .mysqlUser(reqDataBase.getDataBaseUser())
                .mysqlSecret(reqDataBase.getDataBaseSecret())
                .updateTime(dateTime)
                .build();

        userDataBaseMapper.update(userDataBase);

        return null;
    }


    @Override
    public Page<RspDataBase> getUserDataBaseListByTeamId(Long teamId, Integer page, Integer size) {
        Page<RspDataBase> orderPage = initPage(page, size);
        page = (page - 1) * size;
        List<UserDataBase> userDataBaseList = userDataBaseMapper.getUserDataBaseListByTeamId(teamId, page, size);

        List<RspDataBase> rspDataBaseList = getRspDataBases(userDataBaseList);
        int userDataBaseCount = userDataBaseMapper.getUserDataBaseCountByTeamId(teamId);

        orderPage.setTotal(userDataBaseCount);
        orderPage.setRecords(rspDataBaseList);

        return orderPage;

    }

    @Override
    public Page<RspDataBase> getUserDataBaseListByTeamIdAndDeveloper(Long teamId, String developer, Integer page, Integer size) {
        Page<RspDataBase> orderPage = initPage(page, size);
        page = (page - 1) * size;
        List<UserDataBase> userDataBaseList = userDataBaseMapper.getUserDataBaseListByTeamIdAndDeveloper(teamId,developer, page, size);

        List<RspDataBase> rspDataBaseList = getRspDataBases(userDataBaseList);
        int userDataBaseCount = userDataBaseMapper.getUserDataBaseCountByTeamIdAndDeveloper(teamId, developer);

        orderPage.setTotal(userDataBaseCount);
        orderPage.setRecords(rspDataBaseList);

        return orderPage;
    }

    @Override
    public void delete(Long id) {

        //1、删除蓝图
        //2、记录删除标志
        //3、field数据库删除
        //4、数据库信息更新

    }

    @Override
    public void updateApplicationAuthorization(Long id, ReqApplicationAuthorization reqApplicationAuthorization) {

        long snowflakeId = snowflakeIdWorker.getId();
        Date dateTime = new Date();

        ApplicationAuthorization applicationAuthorization = ApplicationAuthorization.builder()
                .id(snowflakeId)
                .userDatabaseId(id)
                .appName(reqApplicationAuthorization.getAppName())
                .appEnName(reqApplicationAuthorization.getAppEnName())
                .teamId(reqApplicationAuthorization.getTeamId())
                .createUser(reqApplicationAuthorization.getUserName())
                .createTime(dateTime)
                .updateTime(dateTime)
                .build();

        userDataBaseMapper.updateApplicationAuthorization(applicationAuthorization);
    }

    @Override
    public Page<RspApplicationAuthorization> getApplicationAuthorizationListById(Long id, Integer page, Integer size) {

        Page<RspApplicationAuthorization> applicationAuthorizationPage = new Page<>();
        applicationAuthorizationPage.setPages(page);
        applicationAuthorizationPage.setSize(size);
        page = (page - 1) * size;

        List<ApplicationAuthorization> applicationAuthorizationList = userDataBaseMapper.getApplicationAuthorizationListByUserDatabaseId(id, page, size);
        List<RspApplicationAuthorization> rspApplicationAuthorizationList = new ArrayList<>();
        for (ApplicationAuthorization applicationAuthorization : applicationAuthorizationList){

            RspApplicationAuthorization rspApplicationAuthorization = RspApplicationAuthorization.builder()
                    .AppName(applicationAuthorization.getAppName())
                    .AppEnName(applicationAuthorization.getAppEnName())
                    .build();
            rspApplicationAuthorizationList.add(rspApplicationAuthorization);
        }

        int applicationAuthorizationCount = userDataBaseMapper.getApplicationAuthorizationCountByUserDatabaseId(id);

        applicationAuthorizationPage.setTotal(applicationAuthorizationCount);
        applicationAuthorizationPage.setRecords(rspApplicationAuthorizationList);

        return applicationAuthorizationPage;

    }

    /**
     * 响应消息体转换
     * @param userDataBaseList userDataBaseList
     * @return List<RspDataBase>
     */
    private static List<RspDataBase> getRspDataBases(List<UserDataBase> userDataBaseList) {
        List<RspDataBase> rspDataBaseList = new ArrayList<>();
        for (UserDataBase userDataBase: userDataBaseList){
            RspDataBase rspDataBase= RspDataBase.builder()
                    .id(userDataBase.getId())
                    .teamId(userDataBase.getTeamId())
                    .userName(userDataBase.getCreateUser())
                    .sid(userDataBase.getSid())
                    .serviceType(userDataBase.getServiceType())
                    .env(userDataBase.getEnvName())
                    .dataBaseUser(userDataBase.getMysqlUser())
                    .dataBaseSecret(userDataBase.getMysqlSecret())
                    .createTime(userDataBase.getCreateTime())
                    .updateTime(userDataBase.getUpdateTime())
                    .build();
            rspDataBaseList.add(rspDataBase);
        }
        return rspDataBaseList;
    }


    private Page<RspDataBase> initPage(Integer page, Integer size){
        Page<RspDataBase> initPage = new Page<>();
        initPage.setPages(page);
        initPage.setSize(size);
        return initPage;
    }


    public void CreateUserDatabase(String user, String passwd){
        //1、查询gaia调度结果
        //2、去资源管理组织获取field地址
        //3、创建数据库连接
        //4、去对应的field数据库地址开户
    }



    private String getSchedulingResult(){


        return "";
    }

    private String getFieldUrl(){


        return "";
    }

    private Statement createDataBaseConnection(String fieldUrl) {
        try {
            Class.forName(UserSystemConfig.driverClassName);

            Connection conn = DriverManager.getConnection(fieldUrl, UserSystemConfig.username, UserSystemConfig.password);

            return conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            log.error("createDataBaseConnection error.",e);
            throw new UserSystemException(ResponseCode.RC501.getCode(), ResponseCode.RC501.getMessage());
        }
    }

    private void createDatabase(Statement statement, String user, String passwd) {
        String sql = "create user '" + user + "'@'%' identified by '" + passwd + "';";
        String privilegesSql =  "grant all privileges on *.* to '" + user +"'@'%' identified by '" + passwd + "';";
        String flushSql = "flush privileges;";

        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(privilegesSql);
            statement.executeUpdate(flushSql);
        } catch (SQLException e) {
            log.error("create database error.",e);
            throw new UserSystemException(ResponseCode.RC501.getCode(), ResponseCode.RC501.getMessage());
        }
    }


}
