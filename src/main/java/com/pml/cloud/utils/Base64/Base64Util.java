package com.pml.cloud.utils.Base64;

import org.apache.hc.client5.http.utils.Base64;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/17
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
public class Base64Util {

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    public static void main(String[] args) {
        String string = "pmllmprt1@3";
        //编码
        String encode = encode(string.getBytes());
        System.out.println(string + "\t编码后的字符串为：" + encode);
        //解码
        String decode = decode(encode.getBytes());
        System.out.println(encode + "\t字符串解码后为：" + decode);
    }


}
