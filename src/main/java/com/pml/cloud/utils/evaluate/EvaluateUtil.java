package com.pml.cloud.utils.evaluate;

import com.pml.cloud.utils.evaluate.entities.EvaluateData;
import com.pml.cloud.utils.evaluate.entities.GeoPoint;
import com.pml.cloud.utils.evaluate.entities.LoginSuccessData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EvaluateUtil {
    public static final String LEGAL_REGEX="^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s([a-z0-9\\u4e00-\\u9fa5]*)\\s(EVALUATE|SUCCESS)\\s\\[([a-z0-9\\u4e00-\\u9fa5]*)\\]\\s([a-z0-9]{32})\\s\\\"([a-z0-9\\.\\-\\,]{6,12})\\\"\\s([a-z\\u4e00-\\u9fa5]*)\\s\\\"([0-9\\.\\,]*)\\\"\\s\\[([0-9\\,\\.]*)\\]\\s\\\"(.*)\\\"";
    public static final String EVALUATE_REGEX="^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s([a-z0-9\\u4e00-\\u9fa5]*)\\s(EVALUATE)\\s\\[([a-z0-9\\u4e00-\\u9fa5]*)\\]\\s([a-z0-9]{32})\\s\\\"([a-z0-9\\.\\-\\,]{6,12})\\\"\\s([a-z\\u4e00-\\u9fa5]*)\\s\\\"([0-9\\.\\,]*)\\\"\\s\\[([0-9\\,\\.]*)\\]\\s\\\"(.*)\\\"";
    public static final String SUCCESS_REGEX="^INFO\\s(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s([a-z0-9\\u4e00-\\u9fa5]*)\\s(SUCCESS)\\s\\[([a-z0-9\\u4e00-\\u9fa5]*)\\]\\s([a-z0-9]{32})\\s\\\"([a-z0-9\\.\\-\\,]{6,12})\\\"\\s([a-z\\u4e00-\\u9fa5]*)\\s\\\"([0-9\\.\\,]*)\\\"\\s\\[([0-9\\,\\.]*)\\]\\s\\\"(.*)\\\"";
    public static final Pattern LEGAL_PATTERN = Pattern.compile(LEGAL_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    public static final Pattern EVALUATE_PATTERN = Pattern.compile(EVALUATE_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    public static final Pattern SUCCESS_PATTERN = Pattern.compile(SUCCESS_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    public static Boolean isLegal(String input){
        Matcher matcher = LEGAL_PATTERN.matcher(input);
        return matcher.matches();
    }

    /**
     * 判断是否是评估数据
     * @param input
     * @return
     */
    public static Boolean isEvaluate(String input){
        Matcher matcher = EVALUATE_PATTERN.matcher(input);
        return matcher.matches();
    }

    /**
     * 判断是否是成功登录的数据
     * @param input
     * @return
     */
    public static Boolean isLoginSuccess(String input){
        Matcher matcher = SUCCESS_PATTERN.matcher(input);
        return matcher.matches();
    }

    /**
     * 解析日志文件，得到待评估数据
     * @param input
     * @return
     * @throws ParseException
     */
    public static EvaluateData parseEvaluateData(String input) throws ParseException {
        //指定一个验证数据对象
        EvaluateData evaluateData = new EvaluateData();
        //获取匹配体
        Matcher matcher = EvaluateUtil.EVALUATE_PATTERN.matcher(input);
        //如果配配到了
        if(matcher.find()){
            //遍历
            for (int i = 0; i <= matcher.groupCount(); i++) {

                switch (i){
                    case 1:
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = simpleDateFormat.parse(matcher.group(i));
                        //传递时间参数
                        evaluateData.setEvaluateTime(date.getTime());
                        break;
                    case 2:
                        //设置应用名
                        evaluateData.setApplicationName(matcher.group(i));
                        break;
                    case 4:
                        //设置用户标识
                        evaluateData.setUserIdentify(matcher.group(i));
                        break;
                    case 5:
                        //设置应用序列
                        evaluateData.setLoginSequence(matcher.group(i));
                        break;
                    case 6:
                        //设置密码
                        evaluateData.setOrdernessPassword(matcher.group(i));
                        break;
                    case 7:
                        //设置城市
                        evaluateData.setCityName(matcher.group(i));
                        break;
                    case 8:
                        //设置经纬度
                        String geoparams = matcher.group(i);
                        String[] geos = geoparams.split(",");
                        //指定一个经纬度对象
                        GeoPoint geoPoint = new GeoPoint(Double.parseDouble(geos[0]),Double.parseDouble(geos[1]));
                        //设置位置对象
                        evaluateData.setGeoPoint(geoPoint);
                        break;
                    case 9:
                        //设置输入特性
                        String featrues = matcher.group(i);
                        String[] featureGroup = featrues.split(",");
                        Double[] doubleFertrue = {Double.parseDouble(featureGroup[0]),Double.parseDouble(featureGroup[1]),Double.parseDouble(featureGroup[2])};
                        evaluateData.setInputFeatures(doubleFertrue);
                        break;
                    case 10:
                        //设置设备信息
                        evaluateData.setDeviceInformation(matcher.group(i));
                        break;
                }
            }

        }
        return  evaluateData;
    }

    /**
     * 解析成功的登录数据
     * @param input
     * @return
     * @throws ParseException
     */
    public static LoginSuccessData parseLoginSuccessData(String input) throws ParseException {

        //指定一个验证数据对象
        LoginSuccessData loginSuccessData = new LoginSuccessData();
        //获取匹配体
        Matcher matcher = EvaluateUtil.SUCCESS_PATTERN.matcher(input);
        //如果配配到了
        if(matcher.find()){
            //遍历
            for (int i = 0; i <= matcher.groupCount(); i++) {

                switch (i){
                    case 1:
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = simpleDateFormat.parse(matcher.group(i));
                        //传递时间参数
                        loginSuccessData.setEvaluateTime(date.getTime());
                        break;
                    case 2:
                        //设置应用名
                        loginSuccessData.setApplicationName(matcher.group(i));
                        break;
                    case 4:
                        //设置用户标识
                        loginSuccessData.setUserIdentify(matcher.group(i));
                        break;
                    case 5:
                        //设置应用序列
                        loginSuccessData.setLoginSequence(matcher.group(i));
                        break;
                    case 6:
                        //设置密码
                        loginSuccessData.setOrdernessPassword(matcher.group(i));
                        break;
                    case 7:
                        //设置城市
                        loginSuccessData.setCityName(matcher.group(i));
                        break;
                    case 8:
                        //设置经纬度
                        String geoparams = matcher.group(i);
                        String[] geos = geoparams.split(",");
                        //指定一个经纬度对象
                        GeoPoint geoPoint = new GeoPoint(Double.parseDouble(geos[0]),Double.parseDouble(geos[1]));
                        //设置位置对象
                        loginSuccessData.setGeoPoint(geoPoint);
                        break;
                    case 9:
                        //设置输入特性
                        String featrues = matcher.group(i);
                        String[] featureGroup = featrues.split(",");
                        Double[] doubleFertrue = {Double.parseDouble(featureGroup[0]),Double.parseDouble(featureGroup[1]),Double.parseDouble(featureGroup[2])};
                        loginSuccessData.setInputFeatures(doubleFertrue);
                        break;
                    case 10:
                        //设置设备信息
                        loginSuccessData.setDeviceInformation(matcher.group(i));
                        break;
                }
            }

        }
        return  loginSuccessData;

    }

    /**获取应用名
     * 解析合法的的登录数据
     * @param input
     */
    public static String getApplicationName(String input) throws ParseException {
        //指定一个验证数据对象
        EvaluateData evaluateData = new EvaluateData();
        //获取匹配体
        Matcher matcher = EvaluateUtil.LEGAL_PATTERN.matcher(input);
        //构建返回值
        String applicationName = null;
        //如果配配到了
        if(matcher.find()){
            applicationName  = matcher.group(2);
        }
        return  applicationName;
    }

    /**获取用户的名字
     * 解析合法的的登录数据
     * @param input
     */
    public static String getUserIdentify(String input) throws ParseException {
        //指定一个验证数据对象
        EvaluateData evaluateData = new EvaluateData();
        //获取匹配体
        Matcher matcher = EvaluateUtil.LEGAL_PATTERN.matcher(input);
        //构建返回值
        String userIdentify = null;
        //如果配配到了
        if(matcher.find()){
            userIdentify  = matcher.group(4);
        }
        return  userIdentify;
    }
    /**
     * 测试方法
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        String input="INFO 2020-03-31 10:12:00 Q1Q应用1 evaluate [张三] 6ebaf4ac780f40f486359f3ea6934620 \"123456\" Beijing \"116.4,39.5\" [1200,15000,2100] \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36\"";
        /*  System.out.println(EvaluateUtil.isLegal(input));*/
        //获取匹配体
        Matcher matcher = EVALUATE_PATTERN.matcher(input);
        //如果配配到了
        if(matcher.find()){

            //指定一个验证数据对象
            EvaluateData evaluateData = new EvaluateData();
            //遍历
            for (int i = 0; i <= matcher.groupCount(); i++) {

                switch (i){
                    case 1:
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = simpleDateFormat.parse(matcher.group(i));
                        //传递时间参数
                        evaluateData.setEvaluateTime(date.getTime());
                        break;
                    case 2:
                        //设置应用名
                        evaluateData.setApplicationName(matcher.group(i));
                        break;
                    case 4:
                        //设置用户标识
                        evaluateData.setUserIdentify(matcher.group(i));
                        break;
                    case 5:
                        //设置应用序列
                        evaluateData.setLoginSequence(matcher.group(i));
                        break;
                    case 6:
                        //设置密码
                        evaluateData.setOrdernessPassword(matcher.group(i));
                        break;
                    case 7:
                        //设置城市
                        evaluateData.setCityName(matcher.group(i));
                        break;
                    case 8:
                        //设置经纬度
                        String geoparams = matcher.group(i);
                        String[] geos = geoparams.split(",");
                        //指定一个经纬度对象
                        GeoPoint geoPoint = new GeoPoint(Double.parseDouble(geos[0]),Double.parseDouble(geos[1]));
                        //设置位置对象
                        evaluateData.setGeoPoint(geoPoint);
                        break;
                    case 9:
                        //设置输入特性
                        String featrues = matcher.group(i);
                        String[] featureGroup = featrues.split(",");
                        Double[] doubleFertrue = {Double.parseDouble(featureGroup[0]),Double.parseDouble(featureGroup[1]),Double.parseDouble(featureGroup[2])};
                        evaluateData.setInputFeatures(doubleFertrue);
                        break;

                    case 10:
                        //设置设备信息
                        evaluateData.setDeviceInformation(matcher.group(i));
                        break;
                }

            }
            System.out.println(evaluateData);
        }

    }
}
