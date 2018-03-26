package com.android.virgilsecurity.jwtworkexample.data.remote;

import com.android.virgilsecurity.jwtworkexample.data.model.TokenResponse;
import com.android.virgilsecurity.jwtworkexample.data.model.UsersRepsonse;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class RxServiceHelper {

    private JwtExampleService service;

    public RxServiceHelper(Retrofit retrofit) {
        this.service = retrofit.create(JwtExampleService.class);
    }

    public Single<UsersRepsonse> getUsers() {
        return service.getUsersRx();
    }

    public Single<TokenResponse> getToken(String googleToken) {
        return service.getTokenRx(googleToken);
    }
}
