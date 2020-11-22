package com.example.mvpdemo.mvp;

import com.example.mvpdemo.retrofit.RetrofitClient;
import com.example.mvpdemo.retrofit.UserInfo;

import java.util.List;

import rx.Observable;

public class UserInfoModel implements UserInfoContract.UserInfoModel{
    @Override
    public Observable<List<UserInfo>> getUsers(int page, int count, String type) {
        return RetrofitClient.getServiceApi()
                .queryUserInfo("1", "2", "video") // .subscribeOn().observeOn().subscribe()
                // Subscriber 封装一下
                // 第二个坑 , 坑我们 返回值都是一个泛型，转换返回值泛型
                .compose(RetrofitClient.transformer()); // 注册完了要登录

    }
}
