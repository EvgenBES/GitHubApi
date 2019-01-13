package com.test.presentation.screeens.user.list.recycler;


import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.test.domain.entity.User;
import com.test.presentation.base.recycler.BaseRecyclerViewAdapter;
import com.test.presentation.base.recycler.ClickedItemModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class UserListAdapter extends BaseRecyclerViewAdapter<User, UserItemModel> {

    private PublishSubject<ClickedItemModel> buttonOneClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public UserItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserItemHolder.create(parent, new UserItemModel(buttonOneClickSubject));
    }

    public Observable<ClickedItemModel> observeButtonOneClick() {
        return buttonOneClickSubject;
    }

}
