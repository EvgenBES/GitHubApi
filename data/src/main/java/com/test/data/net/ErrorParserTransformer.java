package com.test.data.net;

import com.test.domain.entity.Error;
import com.test.domain.entity.ErrorType;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class ErrorParserTransformer<S> {

    public <T, E extends Throwable> ObservableTransformer<T, T> parseHttpError() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Throwable throwable) throws Exception {

                                Error error;
                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;

                                    error = new Error("Ошибка, попробуйте еще раз", ErrorType.UNEXPECTED_ERROR);

                                } else if (throwable instanceof SocketTimeoutException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else if (throwable instanceof UnknownHostException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else {
                                    error = new Error("Unexpected error",
                                            ErrorType.UNEXPECTED_ERROR);
                                }

                                return Observable.error(error);
                            }
                        });
            }
        };
    }
}
