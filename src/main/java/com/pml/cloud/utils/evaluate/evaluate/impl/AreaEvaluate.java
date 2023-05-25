package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;

import java.util.Set;


public class AreaEvaluate extends Evaluate {
    public AreaEvaluate() {
        super(RiskFactor.AREA);
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        evaluateReport.signReport(getRiskFactor(),doEval(evaluateData.getCityName(),historyData.getHistoryCities()));
        //驱动调用下一个评估
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    public boolean doEval(String cityName, Set<String> historyCities){
        if(historyCities==null || historyCities.size()==0){//说明是第一次使用
            return false;
        }else{//如果登录过该城市就没有风险
            //不包含，返回True
            return !historyCities.contains(cityName);
        }
    }
}

