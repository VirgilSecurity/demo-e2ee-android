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

import com.virgilsecurity.sdk.cards.Card;
import com.virgilsecurity.sdk.cards.model.RawSignedModel;
import com.virgilsecurity.sdk.client.exceptions.VirgilServiceException;
import com.virgilsecurity.sdk.crypto.exceptions.CryptoException;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Danylo Oliinyk on 3/21/18 at Virgil Security.
 * -__o
 */

public class VirgilRx {

    private final VirgilHelper virgilHelper;

    public VirgilRx(VirgilHelper virgilHelper) {
        this.virgilHelper = virgilHelper;
    }

    public Single<Card> publishCard(String identity) {
        return Single.create(e -> {
            try {
                e.onSuccess(virgilHelper.publishCard(identity));
            } catch (CryptoException | VirgilServiceException exception) {
                exception.printStackTrace();
                e.onError(exception);
            }
        });
    }

    public Single<Card> publishAndoutdateCard(String identity, String previousCardId) {
        return Single.create(e -> {
            try {
                e.onSuccess(virgilHelper.outdateCard(identity, previousCardId));
            } catch (CryptoException | VirgilServiceException exception) {
                exception.printStackTrace();
                e.onError(exception);
            }
        });
    }

    public Single<Card> getCard(String cardId) {
        return Single.create(e -> {
            try {
                e.onSuccess(virgilHelper.getCard(cardId));
            } catch (CryptoException | VirgilServiceException exception) {
                exception.printStackTrace();
                e.onError(exception);
            }
        });
    }

    public Single<List<Card>> searchCards(String identity) {
        return Single.create(e -> {
            try {
                e.onSuccess(virgilHelper.searchCards(identity));
            } catch (CryptoException | VirgilServiceException exception) {
                exception.printStackTrace();
                e.onError(exception);
            }
        });
    }

    public VirgilHelper getVirgilHelper() {
        return virgilHelper;
    }
}
