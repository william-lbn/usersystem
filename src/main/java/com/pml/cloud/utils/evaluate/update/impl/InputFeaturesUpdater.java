package com.pml.cloud.utils.evaluate.update.impl;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;
import com.pml.cloud.utils.evaluate.update.Updater;
import com.pml.cloud.utils.evaluate.update.UpdaterChain;

import java.util.ArrayList;
import java.util.List;


public class InputFeaturesUpdater implements Updater {
    private Integer numCount=10;

    public void setNumCount(Integer numCount) {
        this.numCount = numCount;
    }

    @Override
    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain) {
        doUpdate(loginSuccessData,historyData);
        updaterChain.doChain(loginSuccessData,historyData);
    }
    private void doUpdate(LoginSuccessData loginSuccessData, HistoryData historyData){
        Double[] inputFeatures = loginSuccessData.getInputFeatures();
        List<Double[]> latestInputFeatures = historyData.getLatestInputFeatures();
        if(latestInputFeatures==null){
            latestInputFeatures=new ArrayList<Double[]>();
        }
        latestInputFeatures.add(inputFeatures);
        if(latestInputFeatures.size()>numCount){
            latestInputFeatures.remove(0);
        }

        historyData.setLatestInputFeatures(latestInputFeatures);
    }
}

