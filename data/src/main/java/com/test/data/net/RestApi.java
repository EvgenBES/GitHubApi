package com.test.data.net;

import com.test.data.entity.UserDetailsResponce;
import com.test.data.entity.UserOrganizationsResponse;
import com.test.data.entity.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    String URL = "https://api.github.com/";

    @GET("users")
    Observable<List<UserResponse>> getUsers();

    @GET("users")
    Observable<List<UserResponse>> getNextUsers(@Query("since") int lastUser);

    @GET("users/{name}")
    Observable<UserDetailsResponce> getUserDetails(@Path("name") String userName);

    @GET("users/{name}/orgs")
    Observable<List<UserOrganizationsResponse>> getUserOrganizations(@Path("name") String userName);

}
