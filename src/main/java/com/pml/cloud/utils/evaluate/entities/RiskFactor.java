package com.pml.cloud.utils.evaluate.entities;

/**
 * 声明所有的风险元素
 */
public enum  RiskFactor {

    /**
     * 地址
     */
    AREA("area"),

    /**
     * 设备
     */
    DEVICE("device"),

    /**
     * 次数
     */
    TOTAL("total"),

    /**
     * 访问时段
     */
    TIMESLOT("timeslot"),

    /**
     * 密码相似度
     */
    SIMILARITY("similarity"),

    /**
     * 输入特征
     */
    INPUTFEATURE("inputfeature"),

    /**
     * 位移速度
     */
    SPEED("speed");



    private String name;
    //提供构造
    RiskFactor(String name){
        this.name=name;
    }

}
