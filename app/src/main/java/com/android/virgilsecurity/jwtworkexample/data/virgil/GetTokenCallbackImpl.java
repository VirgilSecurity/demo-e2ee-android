/*
 * Copyright (c) 2015-2018, Virgil Security, Inc.
 *
 * Lead Maintainer: Virgil Security Inc. <support@virgilsecurity.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     (1) Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *
 *     (2) Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *     (3) Neither the name of virgil nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
