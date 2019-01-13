package com.test.injection;

import com.test.presentation.screeens.user.detais.UserDetailsViewModel;
import com.test.presentation.screeens.user.list.UserListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void runInject(UserListViewModel listViewModel);
    void runInject(UserDetailsViewModel listViewModel);
}