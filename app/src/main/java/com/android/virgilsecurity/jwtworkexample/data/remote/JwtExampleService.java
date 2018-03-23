package com.android.virgilsecurity.jwtworkexample.data.remote;

import com.android.virgilsecurity.jwtworkexample.data.model.UsersRepsonse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public interface JwtExampleService {

    @GET("users") Single<UsersRepsonse> getUsers();
}
