package com.pml.cloud.utils.evaluate.update;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;


public interface Updater {

    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain);
}

