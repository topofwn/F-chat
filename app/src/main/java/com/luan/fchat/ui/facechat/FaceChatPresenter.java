package com.luan.fchat.ui.facechat;

import com.luan.fchat.data.DataManager;
import com.luan.fchat.ui.base.BasePresenter;
import com.luan.fchat.utils.SchedulerProvider;

public class FaceChatPresenter<V extends FaceChatContract.View>extends BasePresenter<V> implements FaceChatContract.Presenter<V> {
    public FaceChatPresenter(SchedulerProvider schedulerProvider, DataManager dataManager){
        super(schedulerProvider,dataManager);
    }
    public FaceChatPresenter(){
        super();
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
    }

}
