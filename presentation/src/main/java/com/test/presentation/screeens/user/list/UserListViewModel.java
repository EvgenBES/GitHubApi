package com.test.presentation.screeens.user.list;

import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.test.app.App;
import com.test.domain.entity.User;
import com.test.domain.usecases.GetUserUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;
import com.test.presentation.screeens.user.list.recycler.UserListAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class UserListViewModel extends BaseViewModel<UserListRouter, User> {

    public ObservableInt peopleProgress = new ObservableInt(View.VISIBLE);
    public UserListAdapter adapter = new UserListAdapter();

    @Inject
    public GetUserUseCase getUserUseCase;


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Override
    public void setItem(User item) {

    }

    public UserListViewModel() {
        initUserList();

        adapter
                .observeItemClick()
                .subscribe(new Observer<ClickedItemModel<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(ClickedItemModel<User> userClickedItemModel) {
                        router.startActivityDetails(userClickedItemModel.getEntity().getLogin());
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void initUserList() {
        peopleProgress.set(View.VISIBLE);

        getUserUseCase
                .getUsers()
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        adapter.setItems(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                        peopleProgress.set(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        peopleProgress.set(View.GONE);
                    }
                });
    }

}


