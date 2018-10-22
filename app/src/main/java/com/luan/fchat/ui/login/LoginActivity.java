package com.luan.fchat.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.luan.fchat.R;
import com.luan.fchat.ui.base.BaseActivity;
import com.luan.fchat.ui.facechat.FaceChatActivity;
import com.luan.fchat.ui.register.RegisterActivity;
import com.luan.fchat.utils.ActivityUtils;
import com.luan.fchat.utils.Injections;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private String usr,pw;
    private EditText usrName,pwd;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenter(Injections.provideSchedulerProvider(), Injections.provideAppDataManager(getApplicationContext()));
       initView();
       mPresenter.onAttach(this);
       mPresenter.onViewInitialized();
       initData();
    }


    @Override
    protected void initView() {
        usrName = findViewById(R.id.edtUsrName);
        pwd = findViewById(R.id.edtPw);
        Button btnSU = findViewById(R.id.btnSignUp);
        btnSU.setOnClickListener(this);
        Button btnSI = findViewById(R.id.btnSignIn);
        btnSI.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSignIn){
            ActivityUtils.startActivity(LoginActivity.this, FaceChatActivity.class,true,true);
        }else if(v.getId() == R.id.btnSignUp){
            ActivityUtils.startActivity(LoginActivity.this, RegisterActivity.class,false,true);
        }
    }
}
