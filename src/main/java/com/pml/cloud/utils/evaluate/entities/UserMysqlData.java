package com.pml.cloud.utils.evaluate.entities;

import com.pml.cloud.dao.mapper.UserDataBaseMapper;
import com.pml.cloud.dao.po.UserDataBase;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/16
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
@Component
public class UserMysqlData implements InitializingBean {


    private ConcurrentHashMap <Long, ConcurrentHashMap<String, Set<String>>> mysqlUserPasswd;

    @Resource
    private UserDataBaseMapper userDataBaseMapper;
    @Override
    public void afterPropertiesSet() throws Exception {
        int page = 0;
        int size = 10;
        int userDataBaseCount = userDataBaseMapper.getUserDataBaseCount();

        int pageSize = 0;

        while (pageSize < userDataBaseCount) {
            List<UserDataBase> userDataBaseList = userDataBaseMapper.getUserDataBaseList(page, size);

            for (UserDataBase userDataBase : userDataBaseList) {

                Long teamId = userDataBase.getTeamId();

                if (null == mysqlUserPasswd || mysqlUserPasswd.isEmpty()){
                    mysqlUserPasswd = new ConcurrentHashMap<>();
                }

                if (mysqlUserPasswd.containsKey(teamId)) {

                    ConcurrentMap<String, Set<String>> createUserPasswd = mysqlUserPasswd.get(teamId);

                    if (createUserPasswd.containsKey(userDataBase.getCreateUser())) {
                        createUserPasswd.get(userDataBase.getCreateUser()).add(userDataBase.getMysqlSecret());
                    } else {
                        createUserPasswd.put(userDataBase.getCreateUser(), Stream.of(userDataBase.getMysqlSecret()).collect(Collectors.toSet()));
                    }

                }

                ConcurrentHashMap<String, Set<String>> concurrentHashMap = new ConcurrentHashMap<>();
                concurrentHashMap.put(userDataBase.getCreateUser(), Stream.of(userDataBase.getMysqlSecret()).collect(Collectors.toSet()));
                mysqlUserPasswd.put(teamId, concurrentHashMap);

            }

            //查询下一页
            pageSize = (page++) * size;
        }
    }
}
