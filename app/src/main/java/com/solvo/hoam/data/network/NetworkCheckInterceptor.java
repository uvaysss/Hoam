package com.solvo.hoam.data.network;

import com.solvo.hoam.domain.exception.NoNetworkException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkCheckInterceptor implements Interceptor {

    private NetworkChecker networkChecker;

    public NetworkCheckInterceptor(NetworkChecker networkChecker) {
        this.networkChecker = networkChecker;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        if (!networkChecker.isConnected()) {
            throw new NoNetworkException("No network connection");
        }

        return chain.proceed(requestBuilder.build());
    }

}
