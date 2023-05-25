package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InputFeatureEvaluate extends Evaluate {

    public InputFeatureEvaluate() {
        super(RiskFactor.INPUTFEATURE);
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        //填写报告
        evaluateReport.signReport(getRiskFactor(),doEval(evaluateData.getInputFeatures(),historyData.getLatestInputFeatures()));
        //路由请求s
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    /**
     * 1.计算圆心中点
     * 2.计算两两特征距离
     * 3.对距离进行排序（升序），取2/3处作为评估距离阈值 - threshold
     * 4.计算当前输入的特征距离中心点距离d
     * @param inputFeatures
     * @param historyInputFeatures
     * @return
     */
    public boolean doEval(Double[] inputFeatures, List<Double[]> historyInputFeatures){
        //如果特征不足2次，不进行验证
        if(historyInputFeatures==null || historyInputFeatures.size()<2){
            return false;
        }
        //已经足够2次
        /**
         * 第一步，计算圆心
         */

        //计算出圆心，所有点的同一轴下的坐标长度相加/点的数量
        Double[] centerOfCircle = historyInputFeatures.stream().reduce((x, y) -> {
            //创建一个double 数组，用于接收圆心数据,数组长度为历史数据的维度
            Double[] centerOfCircles = new Double[historyInputFeatures.get(0).length];
            //获取每个轴下对应数据的总和
            for (int i = 0; i < historyInputFeatures.get(0).length; i++) {
                if (centerOfCircles[i] == null) {
                    centerOfCircles[i] = 0.0;
                }
                centerOfCircles[i] += x[i] + y[i];
            }
            return centerOfCircles;
        }).get();
        //计算最终的圆心结果
        for (int i = 0; i < centerOfCircle.length; i++) {
            centerOfCircle[i] = centerOfCircle[i]/historyInputFeatures.size();
        }
        System.out.println("计算的圆心位置是\t"+ Arrays.stream(centerOfCircle).map(x->x+"").reduce((v1, v2)->v1+","+v2).get());
        /**
         * 第二步，进行半径的计算，求出所有2点之间的半径，排序后取2/3的位置作为半径阈值
         */
        //创建一个接收所有距离的数组,长度是 （设历史点个数为n）(n*n-1)/2
        //确定数组长度
        int size = ((historyInputFeatures.size())*(historyInputFeatures.size()-1))/2;
        List<Double> allDistance = new ArrayList<Double>();
        for (int i = 0; i < historyInputFeatures.size(); i++) {
            //每次，拿1个历史数据点，和其他的数据进行距离计算
            for (int j = i+1; j < historyInputFeatures.size(); j++) {
                //计算每2个点的距离，并存入集合中
                allDistance.add(getDistance(historyInputFeatures.get(i),historyInputFeatures.get(j)));
            }
        }
        //排序，求出2/3位置的长度，作为半径
        allDistance.sort((a,b)->{
            if(a==b){
                return 0;
            }else{
                return a>b?1:-1;
            }
        });
        //得出半径
        Double radius = allDistance.get((allDistance.size() * 2) / 3);
        System.out.println("计算的半径是\t"+radius);
        /**
         * 第三步，判断当前特征的模型点到圆心的距离是否小于半径，是就无风险，否则有风险
         */
        //获取当前的点的距离圆心的长度
        Double result = getDistance(inputFeatures,centerOfCircle);
        System.out.println("当前的点距离圆心的长度为"+result);
        return result > radius;


    }

    //提供一个计算2点之间距离的方法 ∑(Xi-Yi)^2
    private Double getDistance(Double[] point1,Double[] point2){
        //创建一个变量，用于接收，每次求和之后的值
        Double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i]-point2[i],2);
        }
        //最后返回计算结果
        return Math.sqrt(sum);

    }

//    public static void main(String[] args) {
//        InputFeatureEvaluate inputFeatureEvaluate = new InputFeatureEvaluate();
//        ArrayList<Double[]> latestInputFeatures = new ArrayList<>();
//        latestInputFeatures.add(new Double[]{1000.0,1100.0,1800.0});
//        latestInputFeatures.add(new Double[]{1100.0,1120.0,1750.0});
//        latestInputFeatures.add(new Double[]{950.0,1250.0,2000.0});
//        latestInputFeatures.add(new Double[]{1200.0,1050.0,1900.0});
//        latestInputFeatures.add(new Double[]{1400.0,800.0,2500.0});
//
//        inputFeatureEvaluate.doEval(new Double[]{1100.0,1000.0,1750.0},latestInputFeatures);
//
//    }
}
/**
 //至少保证历史记录有2条（含）以上
 if(latestInputFeatures==null || latestInputFeatures.size()<2){
 return false;
 }
 // 1.计算圆心中点
 Double[] sumInputs = latestInputFeatures.stream().reduce((v1, v2) -> {
 Double[] sumInputFeatures = new Double[v1.length];
 for (int i = 0; i < v1.length; i++) {
 if(sumInputFeatures[i]==null){
 sumInputFeatures[i]=0.0;
 }
 sumInputFeatures[i] += v1[i] + v2[i];
 }
 return sumInputFeatures;
 }).get();

 Double[] centerVector=new Double[sumInputs.length];
 for (int i = 0; i < sumInputs.length; i++) {
 centerVector[i]=sumInputs[i]/latestInputFeatures.size();
 }
 System.out.println("中点:"+ Arrays.stream(centerVector).map(i->i+"").reduce((v1,v2)->v1+","+v2).get());
 //2.计算两两特征距离
 List<Double> distances=new ArrayList<>();
 //一定可以拿到 n*(n-1)/2  【n= latestInputFeatures.size()】
 for (int i = 0; i < latestInputFeatures.size() ; i++) {
 Double[] currentFeature=latestInputFeatures.get(i);
 for (int j = i+1; j < latestInputFeatures.size(); j++) {
 Double[] nextFeature=latestInputFeatures.get(j);
 //计算向量距离
 Double distance= calculateDistance(currentFeature,nextFeature);
 distances.add(distance);
 }
 }
 //3.对距离进行排序（升序），取2/3处作为评估距离阈值 - threshold
 distances.sort(new Comparator<Double>() {
@Override
public int compare(Double o1, Double o2) {
if(o1==o2) {
return 0;
}else{
return o1>o2?1:-1;
}
}
});
 System.out.println("distances:"+distances);
 Integer n= latestInputFeatures.size();
 int position=(n*(n-1)/2)*2/3;
 Double thresholdDistance = distances.get(position);
 //4.计算当前输入的特征距离中心点距离d
 Double currentDistance = calculateDistance(inputFeatures, centerVector);
 System.out.println("threshold:"+thresholdDistance+","+"currentDistance："+currentDistance);

 return currentDistance> thresholdDistance;
 }
 private Double calculateDistance(Double[] v1,Double[] v2){
 Double sum=0.0;
 for (int i = 0; i < v1.length; i++) {
 sum+=(v1[i]-v2[i])*(v1[i]-v2[i]);
 }
 return Math.sqrt(sum);
 */
