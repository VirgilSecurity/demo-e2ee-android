package com.android.virgilsecurity.jwtworkexample.di;

import com.android.virgilsecurity.jwtworkexample.ui.login.LogInFragmentProvider;
import com.android.virgilsecurity.jwtworkexample.ui.login.LogInActivity;
import com.android.virgilsecurity.jwtworkexample.ui.login.LogInActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            LogInActivityModule.class,
            LogInFragmentProvider.class
    })
    abstract LogInActivity bindLogInActivity();
}
