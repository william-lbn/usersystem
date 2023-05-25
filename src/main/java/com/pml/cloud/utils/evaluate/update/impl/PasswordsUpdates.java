package com.pml.cloud.utils.evaluate.update.impl;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;
import com.pml.cloud.utils.evaluate.update.Updater;
import com.pml.cloud.utils.evaluate.update.UpdaterChain;

import java.util.HashSet;
import java.util.Set;


public class PasswordsUpdates implements Updater {

    @Override
    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain) {
        doUpdate(loginSuccessData,historyData);
        updaterChain.doChain(loginSuccessData,historyData);
    }

    /**
     * 保留所有用户正常登录过的密码
     * @param loginSuccessData
     * @param historyData
     */
    private void doUpdate(LoginSuccessData loginSuccessData, HistoryData historyData){
        String ordernessPassword = loginSuccessData.getOrdernessPassword();
        Set<String> historyOrdernessPasswords = historyData.getHistoryOrdernessPasswords();

        if(historyOrdernessPasswords==null){
            historyOrdernessPasswords=new HashSet<String>();
        }
        historyOrdernessPasswords.add(ordernessPassword);

        historyData.setHistoryOrdernessPasswords(historyOrdernessPasswords);
    }
}
