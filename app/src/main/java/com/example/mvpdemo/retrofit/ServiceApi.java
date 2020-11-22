package com.example.mvpdemo.retrofit;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hcDarren on 2017/12/16.
 * 请求后台访问数据的 接口类
 */
public interface ServiceApi {
    // 接口涉及到解耦，userLogin 方法是没有任何实现代码的
    // 如果有一天要换 GoogleHttp

    @POST("getJoke")// 登录接口 GET(相对路径)
    @FormUrlEncoded
    Observable<Result<List<UserInfo>>> queryUserInfo(
            // @Query(后台需要解析的字段)
            @Field("page") String page,
            @Field("count") String count,
            @Field("type") String type);
}
