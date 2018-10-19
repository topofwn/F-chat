package com.luan.fchat.ui.facechat;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.luan.fchat.R;
import com.luan.fchat.ui.base.BaseActivity;
import com.luan.fchat.utils.Injections;

public class FaceChatActivity extends BaseActivity implements View.OnClickListener{
    private FaceChatPresenter mPresenter;
    private GLSurfaceView faceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facechat);
        initView();
        mPresenter = new FaceChatPresenter(Injections.provideSchedulerProvider(), Injections.provideAppDataManager(getApplicationContext()));
        mPresenter.onAttach(this);
        mPresenter.onViewInitialized();
        initData();
    }


    @Override
    protected void initView() {
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(this);
        Button btnDisconnect = findViewById(R.id.btnDisConnect);
        btnDisconnect.setOnClickListener(this);
        faceView = findViewById(R.id.chatView);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
