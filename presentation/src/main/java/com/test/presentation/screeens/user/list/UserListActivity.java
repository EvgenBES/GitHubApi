package com.test.presentation.screeens.user.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.ActivityUserListBinding;
import android.widget.AbsListView;

import com.test.presentation.base.BaseMvvmActivity;

public class UserListActivity extends BaseMvvmActivity<UserListViewModel,
        ActivityUserListBinding, UserListRouter> {

    private int currentItems;
    private int totalItems;
    private int scrollOutItems;

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
        //SwipeRefreshing
        binding.swipeContainer.setOnRefreshListener(() -> {
            viewModel.initUserList();
            binding.swipeContainer.setRefreshing(false);
        });


        //Adapter
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(viewModel.adapter);


        //Pagination
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    viewModel.isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if (viewModel.isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    viewModel.isScrolling = false;
                    viewModel.addUsers();
                }

            }
        });

    }

}
