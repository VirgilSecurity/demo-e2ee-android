package com.android.virgilsecurity.jwtworkexample.ui.login;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

@Subcomponent(modules = LogInActivityModule.class)
public interface LogInActivityComponent extends AndroidInjector<LogInActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LogInActivity> {
    }
}
