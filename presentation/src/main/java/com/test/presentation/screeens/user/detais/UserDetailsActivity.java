package com.test.presentation.screeens.user.detais;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.ActivityUserDetailsBinding;
import android.test.com.testproject.databinding.ActivityUserListBinding;

import com.test.presentation.base.BaseMvvmActivity;

public class UserDetailsActivity extends BaseMvvmActivity<UserDetailsViewModel,
        ActivityUserDetailsBinding, UserDetailsRouter> {


    @Override
    protected UserDetailsViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserDetailsViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_details;
    }

    @Override
    protected UserDetailsRouter provideRouter() {
        return new UserDetailsRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getUser(getIntent().getStringExtra("GET_LOGIN"));
    }
}
