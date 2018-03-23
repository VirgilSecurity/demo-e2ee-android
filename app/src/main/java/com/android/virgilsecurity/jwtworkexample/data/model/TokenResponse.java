package com.android.virgilsecurity.jwtworkexample.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class TokenResponse {

    @SerializedName("access_token")
    private final String token;

    public TokenResponse(String token) {

        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
