package com.test.data.repositories;

import com.test.data.entity.UserDetailsResponce;
import com.test.data.entity.UserOrganizationsResponse;
import com.test.data.entity.UserResponse;
import com.test.data.net.RestService;
import com.test.domain.entity.User;
import com.test.domain.entity.UserDetails;
import com.test.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    private RestService restService;

    @Inject
    public UserRepositoryImpl(RestService restService) {
        this.restService = restService;
    }


    @Override
    public Observable<List<User>> getUsers() {
        return restService
                .getUsers()
                .map(new Function<List<UserResponse>, List<User>>() {
                    @Override
                    public List<User> apply(List<UserResponse> userResponses) throws Exception {
                        final List<User> listUser = new ArrayList<>();

                        for (UserResponse user : userResponses) {
                            listUser.add(new User(
                                    user.getAvatarUrl(),
                                    user.getLogin(),
                                    user.getId()));
                        }

                        return listUser;
                    }
                });
    }

    @Override
    public Observable<UserDetails> getUserDetails(String userName) {
        return restService
                .getUserDetails(userName)
                .map(new Function<UserDetailsResponce, UserDetails>() {
                    @Override
                    public UserDetails apply(UserDetailsResponce userDetailsResponce) throws Exception {
                        return new UserDetails(
                                userDetailsResponce.getAvatarUrl(),
                                userDetailsResponce.getLogin(),
                                userDetailsResponce.getEmail(),
                                userDetailsResponce.getFollowers(),
                                userDetailsResponce.getFollowing(),
                                userDetailsResponce.getCreatedAt());
                    }
                });
    }

    @Override
    public Observable<List<String>> getOrganizations(String userName) {
        return restService
                .getUserOrganizations(userName)
                .map(new Function<List<UserOrganizationsResponse>, List<String>>() {
                    @Override
                    public List<String> apply(List<UserOrganizationsResponse> userOrganizationsResponses) throws Exception {
                        final List<String> listOrganizations = new ArrayList<>();

                        for (UserOrganizationsResponse organizations : userOrganizationsResponses) {
                            listOrganizations.add(organizations.getLogin());
                        }

                        return listOrganizations;
                    }
                });
    }
}
