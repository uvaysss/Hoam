package com.solvo.hoam.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ApplicationModule {

    public static final String JOB = "JOB";
    public static final String UI = "UI";

    @Provides
    @Singleton
    @Named(JOB)
    Scheduler provideJobScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(UI)
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
