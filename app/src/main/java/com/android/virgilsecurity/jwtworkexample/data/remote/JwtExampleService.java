package com.android.virgilsecurity.jwtworkexample.data.remote;

import com.android.virgilsecurity.jwtworkexample.data.model.TokenResponse;
import com.android.virgilsecurity.jwtworkexample.data.model.UsersRepsonse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public interface JwtExampleService {

    @GET("users") Single<UsersRepsonse> getUsers();

    @GET("auth/login/{google_token}") Single<TokenResponse>
    getToken(@Path("google_token") String googleToken);
}
