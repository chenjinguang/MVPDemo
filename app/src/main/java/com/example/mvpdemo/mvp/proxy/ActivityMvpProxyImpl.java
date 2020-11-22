package com.example.mvpdemo.mvp.proxy;


import com.example.mvpdemo.mvp.base.BaseView;

public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V>
        implements ActivityMvpProxy {

    public ActivityMvpProxyImpl(V view) {
        super(view);
    }
}
