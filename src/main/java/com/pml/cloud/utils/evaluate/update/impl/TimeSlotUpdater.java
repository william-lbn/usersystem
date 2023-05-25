package com.pml.cloud.utils.evaluate.update.impl;

import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;
import com.pml.cloud.utils.evaluate.update.Updater;
import com.pml.cloud.utils.evaluate.update.UpdaterChain;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class TimeSlotUpdater implements Updater {
    @Override
    public void update(LoginSuccessData loginSuccessData, HistoryData historyData, UpdaterChain updaterChain) {
        doUpdate(loginSuccessData,historyData);
        updaterChain.doChain(loginSuccessData,historyData);
    }
    public void doUpdate(LoginSuccessData loginSuccessData, HistoryData historyData) {
        long loginTime = loginSuccessData.getEvaluateTime();

        String[] WEEKS={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(loginTime);

        String dayOfWeek = WEEKS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        DecimalFormat decimalFormat=new DecimalFormat("00");
        String hourOfDay=  decimalFormat.format(calendar.get(Calendar.HOUR_OF_DAY));//01 02 ... 24

        Map<String, Map<String, Integer>> historyLoginTimeSlot = historyData.getHistoryLoginTimeSlot();
        if(historyLoginTimeSlot==null){
            historyLoginTimeSlot=new HashMap<String, Map<String, Integer>>();
        }

        //更新用户的登录习惯
        if(!historyLoginTimeSlot.containsKey(dayOfWeek)){
            HashMap<String, Integer> timeSlot = new HashMap<String, Integer>();
            timeSlot.put(hourOfDay,1);
            historyLoginTimeSlot.put(dayOfWeek,timeSlot);
        }else{//包含dayOfWeek
            Map<String, Integer> timeSlot = historyLoginTimeSlot.get(dayOfWeek);
            Integer count=0;
            if(timeSlot.containsKey(hourOfDay)){//含有时段
                count=timeSlot.get(hourOfDay);
            }
            timeSlot.put(hourOfDay,count+1);
        }
        historyData.setHistoryLoginTimeSlot(historyLoginTimeSlot);
    }

}
