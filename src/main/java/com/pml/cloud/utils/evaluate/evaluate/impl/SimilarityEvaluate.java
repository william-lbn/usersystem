package com.pml.cloud.utils.evaluate.evaluate.impl;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.EvaluateReport;
import com.pml.cloud.utils.evaluate.entities.HistoryData;
import com.pml.cloud.utils.evaluate.entities.RiskFactor;
import com.pml.cloud.utils.evaluate.evaluate.Evaluate;
import com.pml.cloud.utils.evaluate.evaluate.EvaluateChain;

import java.util.*;
import java.util.stream.Collectors;


public class SimilarityEvaluate extends Evaluate {
    private Double thresholdSimilarity;

    public SimilarityEvaluate(Double thresholdSimilarity) {
        super(RiskFactor.SIMILARITY);
        this.thresholdSimilarity=thresholdSimilarity;
    }

    @Override
    public void eval(EvaluateData evaluateData, HistoryData historyData, EvaluateReport evaluateReport, EvaluateChain evaluateChain) {
        evaluateReport.signReport(getRiskFactor(),doEval(evaluateData.getOrdernessPassword(),historyData.getHistoryOrdernessPasswords()));
        //驱动调用下一个评估
        evaluateChain.doChain(evaluateData,historyData,evaluateReport);
    }

    //作评估
    public boolean doEval(String ordernessPassword, Set<String> historyOrdernessPasswords){
        //如果历史密码没有，则证明第一次登陆，不校验
        if(  historyOrdernessPasswords==null || historyOrdernessPasswords.size()==0){
            return false;
        }
        //获取包含所有历史密码的词袋
        Set<Character> wordBag = new HashSet<Character>();
        //获取历史密码流
        List<char[]> collect = historyOrdernessPasswords.stream().map(histroyt -> histroyt.toCharArray()).collect(Collectors.toList());
        for (char[] chars : collect) {
            for (int i = 0; i < chars.length; i++) {
                //词袋获取所有历史的单词
                wordBag.add(chars[i]);
            }
        }
        //将当前输入的密码加入词袋
        char[] chars = ordernessPassword.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            wordBag.add(chars[i]);
        }
        //对词袋进行排序,排序后的最终词袋
        List<Character> bag = wordBag.stream().sorted().collect(Collectors.toList());
        //创建一个历史密码的特征向量集合
        List<Integer[]> histroryVictorList = new ArrayList<>();
        //计算历史密码的特征
        List<String> history = historyOrdernessPasswords.stream().collect(Collectors.toList());
        for (int i = 0; i < history.size(); i++) {
            Integer[] passwordVector = getPasswordVector(bag, history.get(i));
            //向集合中存下一个特性
            histroryVictorList.add(passwordVector);
        }

        //计算当前输入密码的特征向量
        Integer[] tempVictor = getPasswordVector(bag, ordernessPassword);
        //拿当前的密码特征向量与历史的特征向量进行余弦相似比较
        /*for (int i = 0; i < histroryVictorList.size(); i++) {
            Double result = getSimilarity(tempVictor, histroryVictorList.get(i));
            //如果相似度小于阈值，则判定有风险
            if(result<thresholdSimilarity){
                return true;
            }
        }*/
        List<Double> result = histroryVictorList.stream().map(x -> {
            Double similarity = getSimilarity(tempVictor, x);
            System.out.println("当前密码相似度：" + similarity);
            return similarity;
        }).filter(similarity -> similarity > thresholdSimilarity).collect(Collectors.toList());


        return result.size()==0;
    }

    //创建一个计算特征向量的方法
    private Integer[] getPasswordVector(List<Character> bag,String ordernessPassword){
        //创建一个Map存储当前密码的特性
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = ordernessPassword.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //如过集合中已经存在这个的key,则加一
            if(map.containsKey(chars[i])){
                map.put(chars[i],map.get(chars[i])+1);
            }else{
                //如果不存在，则存1
                map.put(chars[i],1);
            }
        }
        //创建一返回值,长度即为词袋长度
        Integer[] restult = new Integer[bag.size()];
        //对词袋数值进行升序排列

        //对比词袋，进行特性抽取
        for (int i = 0; i < bag.size(); i++) {
            //如果这个位置的key,存在于map中，则，返回map的数值，否则，返回0
            if(map.containsKey(bag.get(i))){
                restult[i]=map.get(bag.get(i));
            }else{
                restult[i]=0;
            }
        }
        //最后返回Integer数组
        return restult;
    }

    //创建一个求余弦相似的方法
    private Double getSimilarity(Integer[] passwordVictor,Integer[] historyVictor){
        /**
         * 根据余弦求相似公式得
         计算分子部分 (首先可以确定的是，两个密码的特征向量的长度一致)
         */
        //给出一个接收分子数值的参数
        Double element = 0.0;
        for (int i = 0; i < passwordVictor.length; i++) {
            element += passwordVictor[i]*historyVictor[i];
        }
        //计算分母
        Integer integer1 = Arrays.stream(passwordVictor).map(i -> (i * i)).reduce((x, y) -> x + y).get();
        Integer integer2 = Arrays.stream(historyVictor).map(i -> (i * i)).reduce((x, y) -> x + y).get();
        //按照公式得出最终的分母部分
        Double denominator = Math.sqrt(integer1)*Math.sqrt(integer2);
        //得出最终结果
        return element/denominator;

    }


    public static void main(String[] args) {

        SimilarityEvaluate similarityEvaluate = new SimilarityEvaluate(0.98);

        HashSet<String> pwds = new HashSet<>();
        pwds.add("456vcfg");
        pwds.add("ct12345");
        pwds.add("hjk8901");
        System.out.println(similarityEvaluate.doEval("hjk8901", pwds));

    }
}
/**
 *
 *   if(historyOrdernessPasswords==null || historyOrdernessPasswords.size()==0){
 *           return false;
 *       }
 *       //构建词袋-密码维度
 *       Set<Character> chars=new HashSet<>();
 *        historyOrdernessPasswords.stream().forEach(historyOrdernessPassword-> {
 *            for (char c : historyOrdernessPassword.toCharArray()) {
 *                chars.add(c);
 *            }
 *        });
 *        //将现行密码加入词袋，富化维度
 *         for (char c : ordernessPassword.toCharArray()) {
 *             chars.add(c);
 *         }
 *         //对词袋进行排序
 *         List<Character> wordBag = chars.stream().sorted().collect(Collectors.toList());
 *         System.out.println("wordBag:"+wordBag);
 *
 *         //将所有的历史密码装转为特征向量
 *         List<Integer[]> verctors=historyOrdernessPasswords.stream()
 *                                                           .map(historyOrdernessPassword-> converString2Vector(wordBag,historyOrdernessPassword))
 *                                                           .collect(Collectors.toList());
 *         //当前输入密码的特征向量
 *         Integer[] currentVector = converString2Vector(wordBag, ordernessPassword);
 *
 *         List<Double> resultList = verctors.stream()
 *                 .map(v1 -> {
 *                     double similarity = calculateSimilarity(v1, currentVector);
 *                     System.out.println(similarity);
 *                     return similarity;
 *                 })
 *                 .filter(similarity -> similarity >= thresholdSimilarity)
 *                 .collect(Collectors.toList());
 *
 *         return resultList.size()==0;
 *     }
 *
 *     private double calculateSimilarity(Integer[] v1,Integer[] v2){
 *         Double sum=0.0;
 *         for (int i = 0; i < v1.length; i++) {
 *             sum+=v1[i]*v2[i];
 *         }
 *         //计算平方和
 *         Integer powSum1 = Arrays.stream(v1).map(i -> i * i).reduce((i1, i2) -> i1 + i2).get();
 *         Integer powSum2 = Arrays.stream(v2).map(i -> i * i).reduce((i1, i2) -> i1 + i2).get();
 *         return sum/(Math.sqrt(powSum1)*Math.sqrt(powSum2));
 *     }
 *
 *     private Integer[] converString2Vector(List<Character> wordBag,String ordernessPassword){
 *         Integer[] vector=new Integer[wordBag.size()];
 *         HashMap<Character, Integer> charMap = new HashMap<>();
 *         for (Character c : ordernessPassword.toCharArray()) {
 *             Integer count=1;
 *             if(charMap.containsKey(c)){
 *                 count+=charMap.get(c);
 *             }
 *             charMap.put(c,count);
 *         }
 *         for (int i = 0; i < wordBag.size(); i++) {
 *             Character c = wordBag.get(i);
 *             vector[i]= charMap.containsKey(c)?charMap.get(c):0;
 *         }
 *         return vector;
 * */
