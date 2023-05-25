package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TimeSlotEvaluate extends Evaluate {
    private int threshold;
    public TimeSlotEvaluate(int threshold) {
        super(RiskFactor.TIMESLOT);
        this.threshold=threshold;
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        evaluateReport.signReport(getRiskFactor(),doEval(evaluateData.getEvaluateTime(),historyData.getHistoryLoginTimeSlot(),this.threshold));
        //驱动调用下一个评估
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    /**
     * @param evaluateTime
     * @param historyLoginTimeSlot
     * @param threshold :设定多累计登录多少次以后再对用户进行评估
     * @return
     */
    public boolean doEval(long evaluateTime, Map<String, Map<String,Integer>> historyLoginTimeSlot, int threshold){
        String[] WEEKS={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(evaluateTime);

        String dayOfWeek = WEEKS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        DecimalFormat decimalFormat=new DecimalFormat("00");
        String hourOfDay=  decimalFormat.format(calendar.get(Calendar.HOUR_OF_DAY));//01 02 ... 24

        //用户第一次登录
        if(historyLoginTimeSlot==null || historyLoginTimeSlot.size()==0){
            return false;
        }
        //用户是否达到评估计算阈值标准,如果登录总次小于阈值，不做评估
        if(!historyLoginTimeSlot.containsKey(dayOfWeek)){

            Integer totalCount = historyLoginTimeSlot.entrySet()
                    .stream()// 星期几  Map<小时,次数>
                    .map(t -> t.getValue().entrySet().stream().map(v -> v.getValue()).reduce((v1, v2) -> v1 + v2).get()) // 每天登录总数
                    .reduce((v1, v2) -> v1 + v2)
                    .get();

            return totalCount >= threshold;
        }else{
            //获取当天的登录时段数据
            Map<String, Integer> historyTimeSlot = historyLoginTimeSlot.get(dayOfWeek);
            if(!historyTimeSlot.containsKey(hourOfDay)){//该天登录过，但是在该时段没有登录
                return true;
            }else{//该天登录过，但是在该时段也登录过

                //判断当前的登录时段是否使用户的登录习惯
                Integer currentHourLoginCount = historyTimeSlot.get(hourOfDay);

                //升序登录时间段集合
                List<Integer> sortedList = historyTimeSlot.entrySet()
                        .stream()
                        .map(t -> t.getValue()) //每个时段登录总和
                        .sorted().collect(Collectors.toList());

                //获取用户登录时段的阈值，大于或者等于该值都是习惯
                Integer thresholdTimeSlotCount=sortedList.get((sortedList.size()*2)/3);
                return  currentHourLoginCount<thresholdTimeSlotCount;
                /*
                //计算出所有登录总次数大于thresholdTimeSlotCount值所有hourOfDay集合-习惯时段
                List<String> habbitTimeSlot = historyTimeSlot.entrySet()
                        .stream()
                        .filter(t -> t.getValue() >= thresholdTimeSlotCount)
                        .map(t -> t.getKey())
                        .collect(Collectors.toList());

                return !habbitTimeSlot.contains(hourOfDay);
                */
            }
        }
    }
}

