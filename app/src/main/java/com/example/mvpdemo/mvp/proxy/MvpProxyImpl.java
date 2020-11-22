package com.example.mvpdemo.mvp.proxy;

import com.example.mvpdemo.mvp.base.BasePresenter;
import com.example.mvpdemo.mvp.base.BaseView;
import com.example.mvpdemo.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MvpProxyImpl<V extends BaseView> implements IMvpProxy{

    private V view;
    private List<BasePresenter> mPresenters;

    public MvpProxyImpl(V view) {
        this.view = view;
        mPresenters = new ArrayList<>();
    }

    @Override
    public void bindAndCreatePresenter() {
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if(injectPresenter != null){
                Class<? extends BasePresenter> presenterClazz = null;
                try {
                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
                }catch (Exception e){
                    throw new RuntimeException("No support inject presenter on type:" + field.getType().getName());
                }

                try {
                    BasePresenter presenter = presenterClazz.newInstance();
                    presenter.attach(view);

                    field.setAccessible(true);
                    field.set(this,presenter);
                    mPresenters.add(presenter);

                    checkView(presenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //检查我们的view层是否实现了BasePresenter的View接口
    private void checkView(BasePresenter basePresenter) {
        //1.Presenter的View接口
        Type[] params = basePresenter.getClass().getGenericInterfaces();
        Class viewClazz = (Class) params[0];
        //2.要拿到View层的所有接口
        Class[] viewClasses = view.getClass().getInterfaces();
        //3.View层没有实现就抛异常
        boolean isImplementView = false;
        for (Class viewClass : viewClasses) {
            if(viewClass.isAssignableFrom(viewClazz)){
                isImplementView = true;
                break;
            }
        }
        if(!isImplementView){
            throw new RuntimeException(view.getClass().getSimpleName() + " must implement " + viewClazz.getName());
        }
    }

    @Override
    public void unbindPresenter() {
        for (BasePresenter basePresenter : mPresenters) {
            basePresenter.detach();
        }
        view = null;
    }
}
