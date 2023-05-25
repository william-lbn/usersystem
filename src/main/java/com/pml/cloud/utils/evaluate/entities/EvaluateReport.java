package com.pml.cloud.utils.evaluate.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


//评估报告
public class EvaluateReport implements Serializable {
    private String applicationName;
    private String userIdentify;
    private String loginSequence;
    private long evaluateTime;
    private String cityName;
    private GeoPoint geoPoint;

    //map集合，存储所有的风险因子属性
    private Map<RiskFactor,Boolean> metrics=new HashMap<RiskFactor,Boolean>();

    //定下特定风险因子的报告（改变属性的特定值）
    public void signReport(RiskFactor riskFactor,boolean flag){
        metrics.put(riskFactor,flag);
    }

    public EvaluateReport(String applicationName, String userIdentify, String loginSequence, long evaluateTime, String cityName, GeoPoint geoPoint) {
        this.applicationName = applicationName;
        this.userIdentify = userIdentify;
        this.loginSequence = loginSequence;
        this.evaluateTime = evaluateTime;
        this.cityName = cityName;
        this.geoPoint = geoPoint;

        //初始化所有风险因子都是false
        metrics.put(RiskFactor.AREA,false);
        metrics.put(RiskFactor.DEVICE,false);
        metrics.put(RiskFactor.SIMILARITY,false);
        metrics.put(RiskFactor.SPEED,false);
        metrics.put(RiskFactor.TIMESLOT,false);
        metrics.put(RiskFactor.INPUTFEATURE,false);
        metrics.put(RiskFactor.TOTAL,false);
    }


    @Override
    public String toString(){
        String report=metrics.keySet()
                .stream()
                .sorted((RiskFactor o1, RiskFactor o2) -> o1.name().compareTo(o2.name()))
                .map(riskFactor -> metrics.get(riskFactor)+"")
                .reduce((v1,v2)->v1+" "+v2)
                .get();

        return applicationName+" "+userIdentify+" "+loginSequence+" "+evaluateTime+" "+cityName+ " "+geoPoint.getLongtitude()+","+geoPoint.getLatitude()+" "+report;

    }


    public String getApplicationName() {
        return applicationName;
    }

    public String getUserIdentify() {
        return userIdentify;
    }
}

