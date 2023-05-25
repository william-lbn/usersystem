package com.pml.cloud.utils.evaluate.update.impl;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;
import com.pml.cloud.utils.evaluate.update.Updater;
import com.pml.cloud.utils.evaluate.update.UpdaterChain;


public class LastLoginGeoPoint implements Updater {
    @Override
    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain) {
        doUpdate( loginSuccessData,  historyData);
        updaterChain.doChain(loginSuccessData,historyData);
    }
    public void doUpdate(LoginSuccessData loginSuccessData, HistoryData historyData) {
        historyData.setLastLoginGeoPoint(loginSuccessData.getGeoPoint());
    }
}
