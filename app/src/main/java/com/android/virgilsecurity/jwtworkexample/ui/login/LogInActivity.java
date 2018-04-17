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
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.local.UserManager;
import com.android.virgilsecurity.jwtworkexample.ui.base.BaseActivityDi;
import com.android.virgilsecurity.jwtworkexample.ui.chat.ChatControlActivity;
import com.android.virgilsecurity.jwtworkexample.util.UiUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * . _  _
 * .| || | _
 * -| || || |   Created by:
 * .| || || |-  Danylo Oliinyk
 * ..\_  || |   on
 * ....|  _/    4/13/18
 * ...-| | \    at Virgil Security
 * ....|_|-
 */
public final class LogInActivity extends BaseActivityDi implements HasFragmentInjector {

    @Inject protected DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject protected UserManager userManager;

    public static void start(Activity from) {
        from.startActivity(new Intent(from, LogInActivity.class));
    }

    public static void startWithFinish(Activity from) {
        from.startActivity(new Intent(from, LogInActivity.class));
        from.finish();
    }

    public static void startClearTop(Activity from) {
        from.startActivity(new Intent(from, LogInActivity.class)
                                   .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                                     | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override protected int getLayout() {
        return R.layout.activity_log_in;
    }

    @Override protected void postButterInit() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("")
//                .requestEmail()
//                .build();
//
//        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
//        googleSignInClient.signOut();


        @SuppressLint("RestrictedApi")
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null)
            startChatControlActivity(userManager.getCurrentUser().getName());

        UiUtils.replaceFragmentNoTag(getFragmentManager(),
                                     R.id.flBaseContainer,
                                     LogInFragment.newInstance());
    }

    @Override public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    public void startChatControlActivity(String username) {
        ChatControlActivity.startWithFinish(this, username);
    }
}
