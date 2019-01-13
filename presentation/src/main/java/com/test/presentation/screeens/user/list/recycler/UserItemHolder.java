package com.test.presentation.screeens.user.list.recycler;

import android.test.com.testproject.databinding.ItemUserBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.domain.entity.User;
import com.test.presentation.base.recycler.BaseItemViewHolder;

public class UserItemHolder extends BaseItemViewHolder<User, UserItemModel, ItemUserBinding> {


    private UserItemHolder(UserItemModel viewModel, ItemUserBinding binding) {
        super(viewModel, binding);
    }

    public static UserItemHolder create(ViewGroup parent, UserItemModel viewModel) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new UserItemHolder(viewModel, binding);
    }
}
