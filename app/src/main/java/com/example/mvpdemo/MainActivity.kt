package com.example.mvpdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.retrofit.BaseSubscriber
import com.example.mvpdemo.retrofit.RetrofitClient
import com.example.mvpdemo.retrofit.UserInfo


class MainActivity : AppCompatActivity() {
    private var mUserInfoTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // OkHttp +RxJava + Retrofit 这样写代码行不行？ 1 ，2 ，
        mUserInfoTv = findViewById<View>(R.id.user_info_tv) as TextView
        // 1. MVC 两个地方：个人主页，编辑资料，MVC意味着 ，这些代码是需要写很多份
        // 2. 如果团队协作，多人开发，那么这个页面（编辑资料）一般都是一个人在做，项目比较紧凑的时候，不好分配人
        // 3. 如果某些界面需求变更的情况下，不好定位，或者说出了 Bug 的情况下不怎么好修改（代码多）
        RetrofitClient.getServiceApi()
                .queryUserInfo("1","2","video") // .subscribeOn().observeOn().subscribe()
                // Subscriber 封装一下
                // 第二个坑 , 坑我们 返回值都是一个泛型，转换返回值泛型
                .compose(RetrofitClient.transformer()) // 注册完了要登录
                .subscribe(object: BaseSubscriber<List<UserInfo>>() {
                    override fun onError(errorCode: String, errorMessage: String) {
                        toast(errorMessage)
                    }

                    override fun onNext(userInfo: List<UserInfo>?) {
                        // 这个处理代码不一样
                        mUserInfoTv!!.text = userInfo?.get(0).toString()
                    }
                })
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun log(text: String) {
        Log.e("TAG->Result", text)
    }
}