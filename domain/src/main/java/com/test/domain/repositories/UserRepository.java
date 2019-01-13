package com.test.domain.repositories;

import com.test.domain.entity.User;
import com.test.domain.entity.UserDetails;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<List<User>> getUsers ();

    Observable<UserDetails> getUserDetails (String userName);

    Observable<List<String>> getOrganizations (String userName);
}
