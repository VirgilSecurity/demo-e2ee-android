package com.android.virgilsecurity.jwtworkexample;

import android.app.Activity;
import android.app.Application;

import com.android.virgilsecurity.jwtworkexample.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public class JwtExampleApp extends Application implements HasActivityInjector {

    @Inject DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                          .application(this)
                          .build()
                          .inject(this);
    }

    @Override public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
