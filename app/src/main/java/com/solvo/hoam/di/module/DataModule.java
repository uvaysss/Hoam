package com.solvo.hoam.di.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.solvo.hoam.data.db.DatabaseOpenHelper;
import com.solvo.hoam.data.db.Filter;
import com.solvo.hoam.data.network.ErrorHandler;
import com.solvo.hoam.data.network.NetworkCheckInterceptor;
import com.solvo.hoam.data.network.NetworkChecker;
import com.solvo.hoam.data.network.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(NetworkChecker networkChecker) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new NetworkCheckInterceptor(networkChecker))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RestService.HOAM_API)
                .build();
    }

    @Provides
    @Singleton
    Filter provideFilter() {
        return new Filter();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    ErrorHandler provideErrorHandler(Gson gson) {
        return new ErrorHandler(gson);
    }

    @Provides
    @Singleton
    RestService provideApi(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }

    @Provides
    @Singleton
    SQLiteDatabase provideSQLiteDatabase(Context context) {
        return new DatabaseOpenHelper(context)
                .getWritableDatabase();
    }
}
