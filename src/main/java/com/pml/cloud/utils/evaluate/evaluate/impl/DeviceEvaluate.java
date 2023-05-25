package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;

import java.util.List;


public class DeviceEvaluate extends Evaluate {
    public DeviceEvaluate() {
        super(RiskFactor.DEVICE);
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        evaluateReport.signReport(getRiskFactor(),doEval(evaluateData.getDeviceInformation(),historyData.getHistoryDeviceInformations()));
        //驱动调用下一个评估
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    public boolean doEval(String deviceInformation, List<String> historyDeviceInformations){
        if(historyDeviceInformations==null || historyDeviceInformations.size()==0){//说明是第一次使用
            return false;
        }else{//如果没有使用该设备,返回true 有风险
            return !historyDeviceInformations.contains(deviceInformation);
        }
    }
}
