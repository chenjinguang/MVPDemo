package com.example.mvpdemo.mvp.proxy;

public interface IMvpProxy {

    void bindAndCreatePresenter();//创建和绑定
    void unbindPresenter();//解绑

}
