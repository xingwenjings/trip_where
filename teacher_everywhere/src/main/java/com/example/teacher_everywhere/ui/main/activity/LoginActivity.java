package com.example.teacher_everywhere.ui.main.activity;

import android.support.v4.app.FragmentManager;

import com.example.teacher_everywhere.R;
import com.example.teacher_everywhere.base.BaseActivity;
import com.example.teacher_everywhere.presenter.LoginPresenter;
import com.example.teacher_everywhere.ui.main.fragment.LoginOrBindFragment;
import com.example.teacher_everywhere.view.main.LoginView;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @Override
    protected void initView() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_container,new LoginOrBindFragment()).commit();
    }
}
