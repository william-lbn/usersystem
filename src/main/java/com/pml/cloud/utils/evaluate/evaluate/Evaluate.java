package com.pml.cloud.utils.evaluate.evaluate;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;

/**
 * 验证抽象类
 */
public abstract class Evaluate {
    //风险因子
    private RiskFactor riskFactor;

    //构造
    public Evaluate(RiskFactor riskFactor) {
        this.riskFactor = riskFactor;
    }

    //获取验证的当前风险
    public RiskFactor getRiskFactor() {
        return riskFactor;
    }

    /**
     *验证方法
     * @param evaluateData
     * @param historyData
     * @param evaluateReport
     * @param evaluateChain:驱动下一个Evaluate实例
     */
    public abstract  void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport,
                               EvaluateChain evaluateChain);
}
