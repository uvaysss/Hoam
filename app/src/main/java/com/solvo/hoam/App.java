package com.solvo.hoam;

import android.app.Application;

import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.di.DaggerApplicationComponent;
import com.solvo.hoam.di.module.ContextModule;
import com.solvo.hoam.di.module.DataModule;
import com.solvo.hoam.di.module.NetworkModule;

import timber.log.Timber;

public class App extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        applicationComponent = buildComponent();
    }

    protected ApplicationComponent buildComponent() {
        return DaggerApplicationComponent
                .builder()
                .dataModule(new DataModule())
                .contextModule(new ContextModule(getApplicationContext()))
                .networkModule(new NetworkModule())
                .build();
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
