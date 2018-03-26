package com.android.virgilsecurity.jwtworkexample.data.remote;

import com.android.virgilsecurity.jwtworkexample.data.model.TokenResponse;
import com.android.virgilsecurity.jwtworkexample.data.model.UsersRepsonse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public interface JwtExampleService {

    @GET("users") Single<UsersRepsonse> getUsersRx();

    @GET("auth/login/{google_token}") Single<TokenResponse> getTokenRx(
            @Path("google_token") String googleToken
    );

    @GET("auth/login/{google_token}") Call<TokenResponse> getToken(
            @Path("google_token") String googleToken
    );
}
