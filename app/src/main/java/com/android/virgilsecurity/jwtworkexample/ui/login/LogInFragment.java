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

package com.android.virgilsecurity.jwtworkexample.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.local.UserManager;
import com.android.virgilsecurity.jwtworkexample.data.model.GoogleToken;
import com.android.virgilsecurity.jwtworkexample.data.model.Token;
import com.android.virgilsecurity.jwtworkexample.data.model.User;
import com.android.virgilsecurity.jwtworkexample.ui.base.BaseFragmentDi;
import com.android.virgilsecurity.jwtworkexample.util.ErrorResolver;
import com.android.virgilsecurity.jwtworkexample.util.UiUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.virgilsecurity.sdk.cards.Card;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Danylo Oliinyk on 3/21/18 at Virgil Security.
 * -__o
 */

public final class LogInFragment
        extends BaseFragmentDi<LogInActivity>
        implements LogInVirgilInteractor, LogInKeyStorageInteractor {

    private static final String REQUEST_ID_TOKEN = "681673183198-m9sm47puqhdirvms732m8fa4k996n1ev.apps.googleusercontent.com";
    private static final String REQUEST_SERVER_AUTH_CODE = "snubKcPLscvA9owuCtlAZAOv";

    private static final int RC_SIGN_IN = 42;

    private GoogleSignInClient googleSignInClient;
    @Inject protected LogInPresenter presenter;
    @Inject protected UserManager userManager;
    @Inject protected ErrorResolver errorResolver;

    @BindView(R.id.signInButton)
    protected SignInButton signInButton;

    public static LogInFragment newInstance() {

        Bundle args = new Bundle();

        LogInFragment fragment = new LogInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override protected int getLayout() {
        return R.layout.fragment_log_in;
    }

    @Override protected void postButterInit() {
        initGoogleAuth();
    }

    @SuppressLint("RestrictedApi") private void initGoogleAuth() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(REQUEST_ID_TOKEN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(activity, gso);

        signInButton.setOnClickListener(v -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            @SuppressLint("RestrictedApi")
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            UiUtils.toast(this, "Signed In as " + account.getDisplayName());

            User user = new User(account.getEmail());
            userManager.setCurrentUser(user);
            userManager.setGoogleToken(new GoogleToken(account.getIdToken()));

            presenter.requestSearchCards(user.getEmailPrefix());
        } catch (ApiException ignored) {
            UiUtils.toast(this, "Error\nCode: " + ignored.getStatusCode()
                    + "\nMessage: " + ignored.getMessage());
        }
    }

    @Override public void onSearchCardSuccess(List<Card> cards) {
        if (cards.size() > 1) {
            presenter.disposeAll();
            UiUtils.toast(this, R.string.multiply_cards);
        }

        presenter.requestIfKeyExists(cards.get(0).getIdentity());
    }

    @SuppressLint("RestrictedApi") @Override public void onSearchCardError(Throwable t) {
        String error = errorResolver.resolve(t, resolvedError -> null); // If we can't resolve error here -
                                                                        // then it's normal behaviour. Proceed.
        if (error != null) {
            UiUtils.toast(this, error);
            googleSignInClient.signOut();
        }

        presenter.requestPublishCard(userManager.getCurrentUser().getEmailPrefix());
    }

    @Override public void onPublishCardSuccess(Card card) {
        userManager.setUserCard(card);
        activity.startChatControlActivity(userManager.getCurrentUser().getEmailPrefix());
    }

    @Override public void onPublishCardError(Throwable t) {
        UiUtils.toast(this, errorResolver.resolve(t));
    }

    @Override public void onKeyExists() {
        activity.startChatControlActivity(userManager.getCurrentUser().getEmailPrefix());
    }

    @Override public void onKeyNotExists() {
        presenter.disposeAll();
        UiUtils.toast(this, R.string.no_private_key); // TODO: 3/28/18 add dialog with explanation that
                                                               //               user can sign in again but won't
                                                               //               be able to decrypt old messages
    }
}
