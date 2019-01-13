package com.test.presentation.screeens.user.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.ActivityUserListBinding;

import com.test.presentation.base.BaseMvvmActivity;

public class UserListActivity extends BaseMvvmActivity<UserListViewModel,
        ActivityUserListBinding, UserListRouter> {

    @Override
    protected UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.swipeContainer.setOnRefreshListener(() -> {
            viewModel.initUserList();
            binding.swipeContainer.setRefreshing(false);
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(viewModel.adapter);

    }

}
