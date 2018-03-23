package com.android.virgilsecurity.jwtworkexample.di;

import android.app.Application;

import com.android.virgilsecurity.jwtworkexample.JwtExampleApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        NetworkModule.class,
        VirgilModule.class,
        ActivityBuilder.class
})
public interface AppComponent {

    @Component.Builder interface Builder {
        @BindsInstance Builder application(Application application);

        AppComponent build();
    }

    void inject(JwtExampleApp app);
}
