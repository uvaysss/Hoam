package com.solvo.hoam;

import android.app.Application;

import com.solvo.hoam.di.ApplicationComponent;
import com.solvo.hoam.di.DaggerApplicationComponent;
import com.solvo.hoam.di.module.ContextModule;
import com.solvo.hoam.di.module.ApplicationModule;

public class HoamApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .applicationModule(new ApplicationModule())
                .build();
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
