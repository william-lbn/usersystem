package com.pml.cloud.utils.evaluate.update.impl;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;
import com.pml.cloud.utils.evaluate.update.Updater;
import com.pml.cloud.utils.evaluate.update.UpdaterChain;

import java.util.ArrayList;
import java.util.List;


public class DeviceUpdates implements Updater {
    private Integer deviceCount=3;

    public DeviceUpdates(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    @Override
    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain) {
        doUpdate(loginSuccessData,historyData);
        updaterChain.doChain(loginSuccessData,historyData);
    }

    /**
     * 保留所有用户最近deviceCount个设备信息
     * @param loginSuccessData
     * @param historyData
     */
    private void doUpdate(LoginSuccessData loginSuccessData, HistoryData historyData){
        String deviceInformation = loginSuccessData.getDeviceInformation();
        List<String> deviceInformations = historyData.getHistoryDeviceInformations();
        if(deviceInformations==null){
            deviceInformations=new ArrayList<String>();
        }
        if(!deviceInformations.contains(deviceInformation)){
            deviceInformations.add(deviceInformation);
            //判断一下集合大小是否达到阈值
            if(deviceInformations.size()>deviceCount){
                deviceInformations.remove(0);
            }
        }
        historyData.setHistoryDeviceInformations(deviceInformations);
    }
}

