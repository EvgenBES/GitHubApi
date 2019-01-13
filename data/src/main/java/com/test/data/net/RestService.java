package com.test.data.net;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.data.entity.UserDetailsResponce;
import com.test.data.entity.UserOrganizationsResponse;
import com.test.data.entity.UserResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestService {

    private RestApi restApi;
    private Gson gson;
    private ErrorParserTransformer errorParserTransformer;

    @Inject
    public RestService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttp = new OkHttpClient
                .Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(
                        new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Log.d("RestService", "HTTP-interceptor: " + message);
                            }
                        })
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                //debugging
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(RestApi.URL)
                .client(okHttp)
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer();
    }

    public Observable<List<UserResponse>> getUsers() {
        return restApi
                .getUsers()
                .compose(errorParserTransformer.<List<UserResponse>, Throwable>parseHttpError());
    }

    public Observable<UserDetailsResponce> getUserDetails(String userName) {
        return restApi
                .getUserDetails(userName)
                .compose(errorParserTransformer.<UserDetailsResponce, Throwable>parseHttpError());
    }

    public Observable<List<UserOrganizationsResponse>> getUserOrganizations(String userName) {
        return restApi
                .getUserOrganizations(userName)
                .compose(errorParserTransformer.<List<UserOrganizationsResponse>, Throwable>parseHttpError());
    }


}
