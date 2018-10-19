package com.luan.fchat.ui.facechat;

import com.luan.fchat.ui.base.MVPPresenter;
import com.luan.fchat.ui.base.MVPView;

public interface FaceChatContract {

    interface Presenter<V extends FaceChatContract.View> extends MVPPresenter<V> {

    }

    interface View extends MVPView {

    }
}
