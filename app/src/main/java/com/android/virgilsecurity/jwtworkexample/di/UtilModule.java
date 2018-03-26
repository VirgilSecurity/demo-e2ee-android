package com.android.virgilsecurity.jwtworkexample.di;

import android.app.Application;
import android.content.Context;

import com.android.virgilsecurity.jwtworkexample.data.local.PropertyManager;
import com.android.virgilsecurity.jwtworkexample.data.local.UserManager;
import com.android.virgilsecurity.jwtworkexample.ui.chat.ChatControlActivityComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Danylo Oliinyk on 3/26/18 at Virgil Security.
 * -__o
 */

@Module(subcomponents = ChatControlActivityComponent.class)
public class UtilModule {

    @Provides @Singleton static Context provideContext(Application application) {
        return application;
    }

    @Provides @Singleton static PropertyManager providePropertyManager(Context context) {
        return new PropertyManager(context);
    }

    @Provides @Singleton static UserManager provideUserManager(Context context) {
        return new UserManager(context);
    }
}
