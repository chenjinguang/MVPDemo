package com.example.mvpdemo.mvp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mvpdemo.R
import com.example.mvpdemo.mvp.base.BaseMVPActivity
import com.example.mvpdemo.mvp.inject.InjectPresenter
import com.example.mvpdemo.retrofit.UserInfo


class MainActivity : BaseMVPActivity<UserInfoPresenter>() ,UserInfoContract.UserInfoView{

    private var mUserInfoTv: TextView? = null


    @InjectPresenter
    lateinit var presenter1:UserInfoPresenter

    @InjectPresenter
    lateinit var presenter2: UserInfoPresenter

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun log(text: String) {
        Log.e("TAG->Result", text)
    }

    override fun onShowUserInfo(userInfo: UserInfo?) {
        mUserInfoTv?.text = userInfo?.text
    }

    override fun onLoading() {

    }

    override fun onError() {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun initData() {
        // 1. MVC 两个地方：个人主页，编辑资料，MVC意味着 ，这些代码是需要写很多份
        // 2. 如果团队协作，多人开发，那么这个页面（编辑资料）一般都是一个人在做，项目比较紧凑的时候，不好分配人
        // 3. 如果某些界面需求变更的情况下，不好定位，或者说出了 Bug 的情况下不怎么好修改（代码多）
        presenter.getUserInfo(1,1,"video")
    }

    override fun initView() {
        // OkHttp +RxJava + Retrofit 这样写代码行不行？ 1 ，2 ，
        mUserInfoTv = findViewById<View>(R.id.user_info_tv) as TextView
    }

    override fun createPresenter(): UserInfoPresenter {
        return UserInfoPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}