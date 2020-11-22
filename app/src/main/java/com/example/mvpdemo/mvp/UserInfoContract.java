package com.example.mvpdemo.mvp;

import com.example.mvpdemo.mvp.base.BasePresenter;
import com.example.mvpdemo.mvp.base.BaseView;
import com.example.mvpdemo.retrofit.UserInfo;

import java.util.List;

import rx.Observable;

public interface UserInfoContract {

    interface UserInfoView extends BaseView{
        void onLoading();
        void onError();
        void onShowUserInfo(UserInfo userInfo);
    }

    interface UserInfoPresenter {
        void getUserInfo(int page, int count, String type);
    }

    interface UserInfoModel{
        Observable<List<UserInfo>> getUsers(int page, int count, String type);
    }
}
