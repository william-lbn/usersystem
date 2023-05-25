package com.pml.cloud.utils.http;

import lombok.Data;

import java.io.Serializable;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/5/4
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
@Data
public class Client5Resp<T> implements Serializable {
    private int code;
    private String raw;
    private T data;

    public Client5Resp(int code, String raw, T data) {
        this.code = code;
        this.raw = raw;
        this.data = data;
    }
}
