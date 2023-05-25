package com.pml.cloud.utils.evaluate.entities;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pml.cloud.dao.mapper.AppInfoMapper;
import com.pml.cloud.dao.mapper.UserDataBaseMapper;
import com.pml.cloud.dao.po.User;
import com.pml.cloud.dao.po.UserDataBase;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Component
public class HistoryData  {
    //历史登录城市
    private Set<String> historyCities;
    //历史设备信息
    private List<String> historyDeviceInformations;
    //登录次数
    private Integer currentDayLoginCount;
    //登录时段习惯
    private Map<String, Map<String,Integer>> historyLoginTimeSlot;
    //存储的乱序密码
    private Set<String> historyOrdernessPasswords;



    //历史的输入特征
    private List<Double[]> latestInputFeatures;
    //上次登录的时间和Geo坐标
    private long lastLoginTime;
    private GeoPoint lastLoginGeoPoint;

}
