package com.android.virgilsecurity.jwtworkexample.data.virgil;

import com.android.virgilsecurity.jwtworkexample.data.remote.ServiceHelper;
import com.virgilsecurity.sdk.jwt.accessProviders.CallbackJwtProvider;

import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class GetTokenCallbackImpl implements CallbackJwtProvider.GetTokenCallback {

    private ServiceHelper helper;
    private AsyncGetToken asyncGetToken;

    public GetTokenCallbackImpl(ServiceHelper helper) {
        this.helper = helper;

        asyncGetToken = () -> null
        helper.getToken(googleToken)
              .subscribe((tokenResponse, throwable) -> tokenResponse)
    }

    @Override public String onGetToken() {
        return asyncGetToken.onAsyncGetToken();
    }

    private interface AsyncGetToken {
        String onAsyncGetToken();
    }
}
