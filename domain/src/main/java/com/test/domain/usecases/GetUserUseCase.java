package com.test.domain.usecases;

import com.test.domain.entity.User;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserUseCase(UserRepository userRepository,
                          PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


    public Observable<List<User>> getUsers() {
        return userRepository
                .getUsers()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }

    public Observable<List<User>> getNextUsers(int lastUser) {
        return userRepository
                .getNextUsers(lastUser * 46 + 1)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }

}
