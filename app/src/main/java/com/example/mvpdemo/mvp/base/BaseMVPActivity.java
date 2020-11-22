package com.example.mvpdemo.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpdemo.mvp.inject.InjectPresenter;
import com.example.mvpdemo.mvp.proxy.ActivityMvpProxy;
import com.example.mvpdemo.mvp.proxy.ActivityMvpProxyImpl;

import java.lang.reflect.Field;
import java.util.List;

public abstract class BaseMVPActivity<P extends BasePresenter> extends
        AppCompatActivity implements BaseView{

    protected P presenter;

    private ActivityMvpProxy mMvpProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

//        presenter = createPresenter();
//        presenter.attach(this);

        mMvpProxy = createMvpProxy();
        mMvpProxy.bindAndCreatePresenter();

        initView();
        
        initData();
    }

    private ActivityMvpProxy createMvpProxy() {
        if(mMvpProxy == null){
            mMvpProxy = new ActivityMvpProxyImpl<>(this);
        }
        return mMvpProxy;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        mMvpProxy.unbindPresenter();
    }
}
