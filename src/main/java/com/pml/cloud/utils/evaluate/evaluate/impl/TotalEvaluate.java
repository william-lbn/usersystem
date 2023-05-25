package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;


public class TotalEvaluate extends Evaluate {
    private Integer threshold=0;

    /**
     * 允许用户最大的登录次数
     * @param threshold
     */
    public TotalEvaluate(Integer threshold) {
        super(RiskFactor.TOTAL);
        this.threshold=threshold;
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        //改变报告属性
        evaluateReport.signReport(getRiskFactor(),doEval(historyData.getCurrentDayLoginCount()));
        //驱动调用下一个评估
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    //具体评估
    public boolean doEval(Integer currentDayLoginCount){
        if(currentDayLoginCount==null){//说明是第一次使用
            return false;
        }else{//如果登录超过限定次数
            return currentDayLoginCount >= threshold;
        }
    }
}

