package com.example.mvpdemo.mvp;

import com.example.mvpdemo.mvp.base.BasePresenter;
import com.example.mvpdemo.retrofit.BaseSubscriber;
import com.example.mvpdemo.retrofit.UserInfo;

import java.util.List;

public class UserInfoPresenter extends BasePresenter<UserInfoContract.UserInfoView> implements UserInfoContract.UserInfoPresenter {

    private UserInfoModel userInfoModel;
    private UserInfoContract.UserInfoView userInfoView;

    public UserInfoPresenter(UserInfoContract.UserInfoView userInfoView) {
        this.userInfoView = userInfoView;
        userInfoModel = new UserInfoModel();
    }

    public void attach(UserInfoContract.UserInfoView view ){
        userInfoView = view;
    }

    public void detach(){
        userInfoModel = null;
    }

    @Override
    public void getUserInfo(int page, int count, String type) {
        userInfoView.onLoading();

        userInfoModel.getUsers(page,count,type).subscribe(new BaseSubscriber<List<UserInfo>>() {
            @Override
            protected void onError(String errorCode, String errorMessage) {
                userInfoView.onError();
            }

            @Override
            public void onNext(List<UserInfo> userInfos) {
                userInfoView.onShowUserInfo(userInfos.get(0));
            }
        });
    }

}
