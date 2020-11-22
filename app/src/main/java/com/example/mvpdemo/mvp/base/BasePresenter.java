package com.example.mvpdemo.mvp.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BasePresenter<V extends BaseView> {

    private V view;

    private V proxyView;

    public void attach(V view){
        this.view = view;
        proxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(view == null){
                            return null;
                        }
                        return method.invoke(view,args);
                    }
                });
    }

    public void detach(){
        view = null;
    }

    public V getView(){
        return proxyView;
    }
}
