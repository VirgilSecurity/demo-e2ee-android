package com.android.virgilsecurity.jwtworkexample.data.remote;

import com.android.virgilsecurity.jwtworkexample.data.model.UsersRepsonse;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class ServiceHelper {

    private JwtExampleService service;

    public ServiceHelper(Retrofit retrofit) {
        this.service = retrofit.create(JwtExampleService.class);
    }

    public Single<UsersRepsonse> getUsers() {
        return service.getUsers();
    }
}
