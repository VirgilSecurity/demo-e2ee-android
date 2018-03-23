package com.android.virgilsecurity.jwtworkexample.ui.login;

import com.virgilsecurity.sdk.jwt.accessProviders.CallbackJwtProvider;
import com.virgilsecurity.sdk.jwt.contract.AccessTokenProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

@Module
public class LogInFragmentModule {

    @Provides LogInPresenter provideLogInPresenter(Retrofit retrofit) {
        return new LogInPresenter(retrofit);
    }

}
