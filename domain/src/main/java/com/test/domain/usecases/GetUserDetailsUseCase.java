package com.test.domain.usecases;

import com.test.domain.entity.UserDetails;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUserDetailsUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserDetailsUseCase(UserRepository userRepository,
                                 PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


    public Observable<UserDetails> getUsers(String userName) {
        return userRepository
                .getUserDetails(userName)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }

    public Observable<List<String>> getOrganizations(String userName) {
        return userRepository
                .getOrganizations(userName)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }

}
