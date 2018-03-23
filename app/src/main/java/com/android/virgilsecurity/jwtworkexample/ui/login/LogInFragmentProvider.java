package com.android.virgilsecurity.jwtworkexample.ui.login;

import com.android.virgilsecurity.jwtworkexample.ui.login.LogInFragment;
import com.android.virgilsecurity.jwtworkexample.ui.login.LogInFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

@Module
public abstract class LogInFragmentProvider {

    @ContributesAndroidInjector(modules = LogInFragmentModule.class)
    abstract LogInFragment bindLogInFragment();
}
