package com.test.presentation.screeens.user.detais;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.test.app.App;
import com.test.domain.entity.User;
import com.test.domain.entity.UserDetails;
import com.test.domain.usecases.GetUserDetailsUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.screeens.user.list.UserListRouter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class UserDetailsViewModel extends BaseViewModel<UserListRouter, User> {

    public ObservableInt progress = new ObservableInt(View.VISIBLE);
    public ObservableInt emailLayout = new ObservableInt(View.VISIBLE);
    public ObservableInt organizationLayout = new ObservableInt(View.VISIBLE);

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> organization = new ObservableField<>("");
    public ObservableField<String> following = new ObservableField<>("");
    public ObservableField<String> followers = new ObservableField<>("");
    public ObservableField<String> created = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");

    @Inject
    public GetUserDetailsUseCase userDetailsUseCase;


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Override
    public void setItem(User item) {

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void detailsImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }


    public void getUser(String login) {

        userDetailsUseCase
                .getUsers(login)
                .subscribe(new Observer<UserDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(UserDetails userDetails) {
                        initUserDetails(userDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                        progress.set(View.GONE);
                        router.goBack();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initUserDetails(UserDetails userDetails) {
        imageUrl.set(userDetails.getAvatarUrl());
        name.set(userDetails.getLogin());
        following.set(String.valueOf(userDetails.getFollowing()));
        followers.set(String.valueOf(userDetails.getFollowers()));
        created.set(userDetails.getCreatedAt());

        if (userDetails.getEmail() != null) {
            email.set(userDetails.getEmail());
        } else {
            emailLayout.set(View.GONE);
        }

        getOrganizations(userDetails.getLogin());
    }

    private void getOrganizations(String login) {
        userDetailsUseCase
                .getOrganizations(login)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<String> organizations) {
                        initOrganizations(organizations);
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showError(e);
                    }

                    @Override
                    public void onComplete() {
                        progress.set(View.GONE);
                    }
                });

    }

    private void initOrganizations(List<String> organizations) {
        if (organizations.size() > 0) {
            StringBuilder s = new StringBuilder();
            for (String list : organizations) {
                s.append(list);
                s.append(", ");
            }

            organization.set(String.valueOf(s).substring(0, s.length() - 2));
        } else {
            organizationLayout.set(View.GONE);
        }
    }


}


