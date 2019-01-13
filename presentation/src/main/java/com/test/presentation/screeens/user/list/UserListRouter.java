package com.test.presentation.screeens.user.list;

import android.content.Intent;

import com.test.presentation.base.BaseRouter;
import com.test.presentation.screeens.user.detais.UserDetailsActivity;

public class UserListRouter extends BaseRouter<UserListActivity> {

    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void startActivityDetails(String login) {
        Intent intent = new Intent(activity, UserDetailsActivity.class);
        intent.putExtra("GET_LOGIN", login);
        activity.startActivity(intent);
    }



}
