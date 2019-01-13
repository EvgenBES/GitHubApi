package com.test.presentation.screeens.user.list.recycler;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.test.domain.entity.User;
import com.test.presentation.base.recycler.BaseItemViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;

import io.reactivex.subjects.PublishSubject;

public class UserItemModel extends BaseItemViewModel<User> {

    public ObservableField<String> userLogin = new ObservableField<>("");
    public ObservableField<String> userId = new ObservableField<>("");
    public ObservableField<String> avatarUrl = new ObservableField<>("");

    private User user;
    private long id;


    PublishSubject<ClickedItemModel> buttonOneClickSubject;

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url){
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }


    public UserItemModel(PublishSubject<ClickedItemModel> buttonOneClickSubject) {
        this.buttonOneClickSubject = buttonOneClickSubject;
    }


    @Override
    public void setItem(User user, int position) {
        this.user = user;
        this.id = position;

        userLogin.set(user.getLogin());
        userId.set(String.valueOf(user.getId()));
        avatarUrl.set(user.getAvatarUrl());
    }

    public void onMyButtonOneClicked() {
        buttonOneClickSubject.onNext(new ClickedItemModel(user, (int) id));
    }
}
