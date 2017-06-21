package com.solvo.hoam.data.rx;

import com.solvo.hoam.data.network.ErrorHandler;
import com.solvo.hoam.data.network.ServerError;
import com.solvo.hoam.domain.exception.HttpException;
import com.solvo.hoam.domain.exception.SessionExpiredException;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;
import retrofit2.Response;

public class ApiErrorHandleTransformer<M, T extends Response<M>> implements SingleTransformer<T, M> {

    private ErrorHandler errorHandler;

    public ApiErrorHandleTransformer(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public SingleSource<M> apply(Single<T> upstream) {
        return upstream.flatMap(new Function<T, SingleSource<M>>() {
            @Override
            public Single<M> apply(final T response) {
                return Single.create(e -> {
                    if (e != null && !e.isDisposed()) {
                        handleResponse(response, e, errorHandler);
                    }
                });
            }
        });
    }

    private void handleResponse(T response, SingleEmitter<M> e, ErrorHandler errorHandler) {
        if (response.isSuccessful()) {
            e.onSuccess(response.body());
        } else {
            if (response.code() == 401) {
                e.onError(new SessionExpiredException());
            } else {
                ServerError serverError = errorHandler.parseError(response);
                if (serverError != null && serverError.getMessage() != null) {
                    HttpException error = new HttpException();
                    error.setErrorCode(serverError.getStatusCode());
                    error.setMessage(serverError.getMessage());
                    e.onError(error);
                } else {
                    HttpException error = new HttpException();
                    error.setErrorCode(response.code());
                    error.setMessage(response.message());
                    e.onError(error);
                }
            }
        }
    }

}

