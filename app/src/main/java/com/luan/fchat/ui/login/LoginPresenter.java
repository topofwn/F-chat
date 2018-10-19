package com.luan.fchat.ui.login;

import com.luan.fchat.data.DataManager;
import com.luan.fchat.ui.base.BasePresenter;
import com.luan.fchat.utils.SchedulerProvider;

public class LoginPresenter<V extends LoginContract.View>extends BasePresenter<V> implements LoginContract.Presenter<V> {
    public LoginPresenter(SchedulerProvider schedulerProvider, DataManager dataManager){
        super(schedulerProvider,dataManager);
    }

    public LoginPresenter(){
        super();
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
    }


    @Override
    public void doLogin() {

    }
}
