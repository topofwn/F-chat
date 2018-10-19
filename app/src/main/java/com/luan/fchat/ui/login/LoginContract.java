package com.luan.fchat.ui.login;

import com.luan.fchat.ui.base.MVPPresenter;
import com.luan.fchat.ui.base.MVPView;

public interface LoginContract  {

    interface Presenter<V extends View & MVPView> extends MVPPresenter<V> {
        void doLogin();
    }

    interface View extends MVPView {

    }
}
