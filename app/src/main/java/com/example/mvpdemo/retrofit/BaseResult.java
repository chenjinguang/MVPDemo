package com.example.mvpdemo.retrofit;

/**
 * Created by hcDarren on 2017/12/16.
 */

public class BaseResult {
    String code;
    String message;

    public String getMsg() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public boolean isOk(){
        return "200".equals(code);
    }
}
