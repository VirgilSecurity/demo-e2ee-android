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

import com.android.virgilsecurity.jwtworkexample.data.remote.ServiceHelper;
import com.android.virgilsecurity.jwtworkexample.data.virgil.VirgilRx;
import com.android.virgilsecurity.jwtworkexample.ui.base.BasePresenter;
import com.virgilsecurity.sdk.cards.Card;
import com.virgilsecurity.sdk.cards.model.RawSignedModel;
import com.virgilsecurity.sdk.storage.PrivateKeyStorage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public class LogInPresenter implements BasePresenter {

    private CompositeDisposable compositeDisposable;
    private LogInVirgilInteractor logInVirgilInteractor;
    private LogInKeyStorageInteractor logInKeyStorageInteractor;
    private VirgilRx virgilRx;
    private PrivateKeyStorage privateKeyStorage;
    private ServiceHelper serviceHelper;

    @Inject
    public LogInPresenter(VirgilRx virgilRx,
                          PrivateKeyStorage privateKeyStorage,
                          LogInVirgilInteractor logInVirgilInteractor,
                          LogInKeyStorageInteractor logInKeyStorageInteractor) {
        this.virgilRx = virgilRx;
        this.privateKeyStorage = privateKeyStorage;
        this.logInVirgilInteractor = logInVirgilInteractor;
        this.logInKeyStorageInteractor = logInKeyStorageInteractor;
    }

    public void requestSearchCards(String identity) {
        Disposable searchCardDisposable =
                virgilRx.searchCards(identity)
                        .subscribe((cards, throwable) -> {
                            if (throwable == null)
                                logInVirgilInteractor.onSearchCardSuccess(cards);
                            else
                                logInVirgilInteractor.onSearchCardError(throwable);
                        });

        compositeDisposable.add(searchCardDisposable);
    }

    public void requestPublishCard(String identity) {
        Disposable publishCardDisposable =
                virgilRx.publishCard(identity)
                        .subscribe((card, throwable) -> {
                            if (throwable == null)
                                logInVirgilInteractor.onPublishCardSuccess(card);
                            else
                                logInVirgilInteractor.onPublishCardError(throwable);
                        });

        compositeDisposable.add(publishCardDisposable);
    }

    public void requestIfKeyExists(String keyName) {
        if (privateKeyStorage.exists(keyName))
            logInKeyStorageInteractor.onKeyExists();
        else
            logInKeyStorageInteractor.onKeyNotExists();
    }

    @Override public void disposeAll() {
        compositeDisposable.clear();
    }
}
