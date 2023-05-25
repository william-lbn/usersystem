package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.*;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Math.*;
import static java.lang.Math.sin;


public class SpeedEvaluate extends Evaluate {
    private Double thresholdSpeed;

    private static final Double EARTH_RADIUS=6371.393;//千米

    public SpeedEvaluate(Double thresholdSpeed) {
        super(RiskFactor.SPEED);
        this.thresholdSpeed=thresholdSpeed;
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        evaluateReport.signReport(getRiskFactor(),doEval(evaluateData.getEvaluateTime(),evaluateData.getGeoPoint(),historyData.getLastLoginTime(),historyData.getLastLoginGeoPoint()));
        //驱动调用下一个评估
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    public boolean doEval(long evaluateTime, GeoPoint currentGeoPoint, long lastLoginTime, GeoPoint lastLoginGeoPoint){
        //判断如果没有上一次登陆的地理位置，则不进行评估
        if(lastLoginGeoPoint==null){
            return false;
        }else {
            //如果2点的经纬度，相同，则不进行验证
            if(lastLoginGeoPoint.getLatitude()==currentGeoPoint.getLatitude() && lastLoginGeoPoint.getLongtitude()==currentGeoPoint.getLongtitude()){
                System.out.println("没有发生位移，不进行位移验证");
                return false;
            }
            /**
             * 第一步，求2个时间段的时间差
             */
            //当前时间减去上一次的登录时间  单位时间 小时  3600*1000 ==>得到1小时的毫秒值
            Double time = (evaluateTime-lastLoginTime)*1.0/(3600*1000);
            /**
             * 第二步，计算地球上2个坐标之间的距离
             */
            Double distanc = geoDistance(currentGeoPoint,lastLoginGeoPoint);
            System.out.println("此次位移验证两地相距"+distanc+"\t两次时间差是"+time+"小时");
            /**
             * 第三步，计算位移的平均速度
             */
            Double speed = distanc/time ;
            System.out.println("speed = " + speed);
            return speed>thresholdSpeed;
        }
    }

    //提供一个计算球体上两点间距离的方法 S = R*arCos(COSw1*COSw2*COS(j2-ji)+SINw1*SINw2)
    public Double geoDistance(GeoPoint currentGeo,GeoPoint lastGeo){
        //将角速值转换为弧度
        //w - 表示 纬度 j - 表示经度
        Double Aw = toRadians(currentGeo.getLatitude());
        Double Aj = toRadians(currentGeo.getLongtitude());

        Double Bw = toRadians(lastGeo.getLatitude());
        Double Bj = toRadians(lastGeo.getLongtitude());

        //使用公式进行计算
        Double acos = acos(cos(Aw) * cos(Bw) * cos(Bj - Aj) + sin(Aw) * sin(Bw));
        return EARTH_RADIUS*acos;
    }

    public static void main(String[] args) throws ParseException {
        SpeedEvaluate speedEvaluate = new SpeedEvaluate(600.0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long evaluateTime=sdf.parse("2020-04-01 10:10:00").getTime();
        GeoPoint p1=new GeoPoint();
        p1.setLongtitude(116.2317);//北京
        p1.setLatitude(39.5427);

        long lastLoginTime=sdf.parse("2020-04-01 08:00:00").getTime();
        GeoPoint p2=new GeoPoint();
        p2.setLongtitude(114.14);//郑州
        p2.setLatitude(34.16);

        speedEvaluate.doEval(evaluateTime,p1,lastLoginTime,p2);
    }
}
/**

 if(lastLoginGeoPoint==null){
 return false;
 }
 double diffTime=(evaluateTime-lastLoginTime)*1.0/(3600*1000);//小时时差
 Double distance = calculateDistance(currentGeoPoint, lastLoginGeoPoint);
 double speed=distance/diffTime;
 System.out.println("distance:"+distance+",diffTime:"+diffTime+",speed:"+speed);
 return speed> thresholdSpeed;
 }

 private Double calculateDistance(GeoPoint point1,GeoPoint point2){
 Double wA=toRadians(point1.getLatitude());//将角度转换为弧度
 Double jA=toRadians(point1.getLongtitude());

 Double wB=toRadians(point2.getLatitude());
 Double jB=toRadians(point2.getLongtitude());

 return EARTH_RADIUS * acos(cos(wA)*cos(wB)*cos(jB-jA)+sin(wA)*sin(wB));
 */
