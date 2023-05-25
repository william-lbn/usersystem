package com.pml.cloud.utils.evaluate.update;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;

import java.util.List;


public class UpdaterChain {
    //提供一个位置属性
    private int position=0;
    //提供一个评估内容属性
    private List<Updater> updaters;

    //提供一个传递更新内容的构造
    public UpdaterChain(List<Updater> updaters) {
        this.updaters = updaters;

    }

    //提供一个作更新历史数据的方法
    public void doChain(LoginSuccessData loginSuccessData, HistoryData historyData){

        //如果，位置没有达到最后位置
        if(position<updaters.size()){
            Updater updater = updaters.get(position);
            position++;
            updater.update(loginSuccessData,historyData,this);
        }

    }

}
