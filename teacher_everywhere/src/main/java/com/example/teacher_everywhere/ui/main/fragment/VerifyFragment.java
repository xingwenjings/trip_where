package com.example.teacher_everywhere.ui.main.fragment;

import com.example.teacher_everywhere.R;
import com.example.teacher_everywhere.base.BaseFragment;
import com.example.teacher_everywhere.presenter.VerifyPresenter;
import com.example.teacher_everywhere.view.main.VerifyView;

public class VerifyFragment extends BaseFragment<VerifyView,VerifyPresenter> implements VerifyView {
    @Override
    protected VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.verify_fragment;
    }
}
