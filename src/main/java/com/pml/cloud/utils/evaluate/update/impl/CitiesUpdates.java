package com.pml.cloud.utils.evaluate.update.impl;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;
import com.pml.cloud.utils.evaluate.update.Updater;
import com.pml.cloud.utils.evaluate.update.UpdaterChain;

import java.util.HashSet;
import java.util.Set;


public class CitiesUpdates implements Updater {

    @Override
    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain) {
        doUpdate(loginSuccessData,historyData);
        updaterChain.doChain(loginSuccessData,historyData);
    }

    /**
     * 保留所有用户登录过的城市
     * @param loginSuccessData
     * @param historyData
     */
    private void doUpdate(LoginSuccessData loginSuccessData, HistoryData historyData){
        String cityName = loginSuccessData.getCityName();
        Set<String> historyCities = historyData.getHistoryCities();
        if(historyCities==null){
            historyCities=new HashSet<String>();
        }
        historyCities.add(cityName);
        historyData.setHistoryCities(historyCities);
    }
}

