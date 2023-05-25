package com.pml.cloud.utils.evaluate.evaluate;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;

import java.util.List;


public class EvaluateChain {
    //验证位置
    private int position=0;
    //评估链（责任链）
    private List<Evaluate> evaluates;

    //构造
    public EvaluateChain(List<Evaluate> evaluates) {
        this.evaluates = evaluates;
    }

    //向下执行
    public void doChain(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport){
        if(position < evaluates.size()){
            //获取一个责任
            Evaluate evaluate = evaluates.get(position);
            position +=1;
            evaluate.eval(evaluateData,historyData,evaluateReport,this);
        }
    }
}
