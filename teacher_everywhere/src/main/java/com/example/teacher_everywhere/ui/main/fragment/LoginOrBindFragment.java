package com.example.teacher_everywhere.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teacher_everywhere.R;
import com.example.teacher_everywhere.base.BaseFragment;
import com.example.teacher_everywhere.presenter.LoginOrBindPresenter;
import com.example.teacher_everywhere.ui.main.activity.WebActivity;
import com.example.teacher_everywhere.view.main.LoginOrBindView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginOrBindFragment extends BaseFragment<LoginOrBindView, LoginOrBindPresenter> implements LoginOrBindView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.services)
    TextView services;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.phone)
    ImageView phone;
    @BindView(R.id.country_code)
    TextView countryCode;
    @BindView(R.id.selects)
    ImageView selects;
    @BindView(R.id.send_code)
    Button sendCode;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.ll_view)
    View llView;
    @BindView(R.id.ll_or)
    LinearLayout llOr;
    @BindView(R.id.wechat)
    ImageView wechat;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.sina)
    ImageView sina;
    @BindView(R.id.logins)
    LinearLayout logins;
    @BindView(R.id.wachat_login)
    TextView wachatLogin;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.agree)
    TextView agree;



    @Override
    protected LoginOrBindPresenter initPresenter() {
        return new LoginOrBindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_or_bind;
    }

    @OnClick({R.id.send_code, R.id.wechat, R.id.qq, R.id.sina})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                addVerifyFragment();
                break;
            case R.id.wechat:
                break;
            case R.id.qq:
                break;
            case R.id.sina:
                break;
        }
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())) {
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fl_container, new VerifyFragment()).commit();
    }

    @Override
    protected void initListener() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void switchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)) {
            sendCode.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        } else {
            sendCode.setBackgroundResource(R.drawable.bg_btn_ea_aa_r15);
        }
    }


    @Override
    public String getPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    protected void initView() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                WebActivity.startAct(getContext());
            }
        };
        spannableStringBuilder.setSpan(clickableSpan,11,15,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableStringBuilder.setSpan(underlineSpan,11,15,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.c_FA6A13));
        spannableStringBuilder.setSpan(span,11,15,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //clickableSpan需要设置这个才有效果
        agree.setMovementMethod(LinkMovementMethod.getInstance());
        agree.setText(spannableStringBuilder);
    }


}
