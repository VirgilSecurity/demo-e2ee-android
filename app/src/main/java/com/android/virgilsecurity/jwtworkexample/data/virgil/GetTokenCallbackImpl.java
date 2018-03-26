package com.android.virgilsecurity.jwtworkexample.data.virgil;

import android.accounts.NetworkErrorException;

import com.android.virgilsecurity.jwtworkexample.data.model.User;
import com.android.virgilsecurity.jwtworkexample.data.model.exception.ServiceException;
import com.android.virgilsecurity.jwtworkexample.data.remote.ServiceHelper;
import com.virgilsecurity.sdk.jwt.accessProviders.CallbackJwtProvider;

import java.io.IOException;

import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class GetTokenCallbackImpl implements CallbackJwtProvider.GetTokenCallback {

    private ServiceHelper helper;
    private User currentUser;

    public GetTokenCallbackImpl(ServiceHelper helper, User currentUser) {
        this.helper = helper;
        this.currentUser = currentUser;
    }

    @Override public String onGetToken() {
        try {
            return helper.getToken(currentUser.getEmailPrefix()).execute().body().getToken();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ServiceException("Failed on get token");
        }
    }
}
