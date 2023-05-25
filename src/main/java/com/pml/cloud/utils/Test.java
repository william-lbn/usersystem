package com.pml.cloud.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/15
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2023-05-01 00:00:00");
        long ts = date.getTime();
        System.out.println(ts);
        String res = String.valueOf(ts);  // 转化为字符串
        System.out.println(res);




    }
}
