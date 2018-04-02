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

package com.android.virgilsecurity.jwtworkexample.data.local;

import android.content.Context;

import com.android.virgilsecurity.jwtworkexample.data.model.Token;
import com.android.virgilsecurity.jwtworkexample.data.model.User;
import com.google.gson.Gson;
import com.virgilsecurity.sdk.cards.Card;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class UserManager extends PropertyManager {

    private static final String CURRENT_USER = "CURRENT_USER";
    private static final String USER_CARD = "USER_CARD";
    private static final String GOOGLE_TOKEN = "GOOGLE_TOKEN";

    public UserManager(Context context) {
        super(context);
    }


    public void setCurrentUser(User user) {
        setValue(CURRENT_USER, new Gson().toJson(user));
    }

    public User getCurrentUser() {
        return new Gson().fromJson(
                (String) getValue(CURRENT_USER,
                                  PropertyManager.SupportedTypes.STRING,
                                  null),
                User.class);
    }

    public void clearCurrentUser() {
        clearValue(CURRENT_USER);
    }


    public void setUserCard(Card card) {
        setValue(USER_CARD, new Gson().toJson(card));
    }

    public Card getUserCard() {
        return new Gson().fromJson(
                (String) getValue(USER_CARD,
                                  SupportedTypes.STRING,
                                  null),
                Card.class);
    }

    public void clearUserCard() {
        clearValue(USER_CARD);
    }


    public void setGoogleToken(Token token) {
        setValue(GOOGLE_TOKEN, new Gson().toJson(token));
    }

    public Token getGoogleToken() {
        return new Gson().fromJson(
                (String) getValue(GOOGLE_TOKEN,
                                  SupportedTypes.STRING,
                                  null),
                Token.class
        );
    }

    public void clearGoogleToken() {
        clearValue(GOOGLE_TOKEN);
    }
}