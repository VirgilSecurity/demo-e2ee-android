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

package com.android.virgilsecurity.jwtworkexample.ui.chat.thread;

import android.content.Context;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.local.UserManager;
import com.android.virgilsecurity.jwtworkexample.data.model.DefaultMessage;
import com.android.virgilsecurity.jwtworkexample.data.model.Message;
import com.android.virgilsecurity.jwtworkexample.data.model.ResponseType;
import com.android.virgilsecurity.jwtworkexample.data.remote.WebSocketHelper;
import com.android.virgilsecurity.jwtworkexample.data.virgil.VirgilHelper;
import com.android.virgilsecurity.jwtworkexample.data.virgil.VirgilRx;
import com.android.virgilsecurity.jwtworkexample.ui.base.BasePresenter;
import com.android.virgilsecurity.jwtworkexample.ui.chat.DataReceivedInteractor;
import com.android.virgilsecurity.jwtworkexample.util.SerializationUtils;
import com.appunite.websocket.rx.RxMoreObservables;
import com.appunite.websocket.rx.messages.RxEventConn;
import com.appunite.websocket.rx.messages.RxEventConnected;
import com.appunite.websocket.rx.messages.RxEventDisconnected;
import com.appunite.websocket.rx.messages.RxEventStringMessage;
import com.google.gson.JsonObject;
import com.virgilsecurity.sdk.cards.Card;
import com.virgilsecurity.sdk.crypto.VirgilPublicKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.WebSocket;
import rx.Subscription;

/**
 * . _  _
 * .| || | _
 * -| || || |   Created by:
 * .| || || |-  Danylo Oliinyk
 * ..\_  || |   on
 * ....|  _/    4/17/18
 * ...-| | \    at Virgil Security
 * ....|_|-
 */
public class ThreadFragmentPresenter implements BasePresenter {

    private WebSocketHelper socketHelper;
    private Context context;
    private DataReceivedInteractor<Message> messageReceivedInteractor;
    private WebSocketInteractor webSocketInteractor;
    private OnMessageSentInteractor onMessageSentInteractor;
    private WebSocket webSocketInterlocutor;
    private Subscription sendMessageSubscription;
    private CompositeDisposable compositeDisposable;
    private VirgilRx virgilRx;
    private SearchCardsInteractor searchCardsInteractor;
    private UserManager userManager;
    private VirgilHelper virgilHelper;

    @Inject
    public ThreadFragmentPresenter(WebSocketHelper socketHelper,
                                   Context context,
                                   DataReceivedInteractor<Message> messageReceivedInteractor,
                                   WebSocketInteractor webSocketInteractor,
                                   OnMessageSentInteractor onMessageSentInteractor,
                                   VirgilRx virgilRx,
                                   SearchCardsInteractor searchCardsInteractor,
                                   UserManager userManager,
                                   VirgilHelper virgilHelper) {
        this.socketHelper = socketHelper;
        this.context = context;
        this.messageReceivedInteractor = messageReceivedInteractor;
        this.webSocketInteractor = webSocketInteractor;
        this.onMessageSentInteractor = onMessageSentInteractor;
        this.virgilRx = virgilRx;
        this.searchCardsInteractor = searchCardsInteractor;
        this.userManager = userManager;
        this.virgilHelper = virgilHelper;

        compositeDisposable = new CompositeDisposable();
    }

    public void turnOnMessageListener() {
        socketHelper.setOnMessageReceiveListener(rxEvent -> {
            if (rxEvent instanceof RxEventConnected) {
                webSocketInterlocutor = ((RxEventConn) rxEvent).sender();
                webSocketInteractor.onConnected();
            } else if (rxEvent instanceof RxEventDisconnected) {
                webSocketInterlocutor = null;
                webSocketInteractor.onDisconnected();
            }

            if (rxEvent instanceof RxEventStringMessage) {
                JsonObject jsonObject =
                        SerializationUtils.fromJson(((RxEventStringMessage) rxEvent).message(),
                                                    JsonObject.class);

                if (jsonObject.get("type")
                              .getAsString()
                              .equals(ResponseType.MESSAGE.getType())) {
                    DefaultMessage message =
                            SerializationUtils.fromJson(jsonObject.get("responseObject")
                                                                  .toString(),
                                                        DefaultMessage.class);

                    messageReceivedInteractor.onDataReceived(message);
                }
            }
        });
    }

    public void turnOffMessageListener() {
        socketHelper.setOnMessageReceiveListener(rxEvent -> {
        });
    }

    public void requestSendMessage(Card interlocutorCard, Message message) {
        List<VirgilPublicKey> publicKeys = new ArrayList<>();
        publicKeys.add((VirgilPublicKey) userManager.getUserCard()
                                                    .getPublicKey());
        publicKeys.add((VirgilPublicKey) interlocutorCard.getPublicKey());

        String encryptedText = virgilHelper.encrypt(message.getText(), publicKeys);
        Message encryptedMessage = new DefaultMessage(message.getSender(),
                                                      message.getReceiver(),
                                                      encryptedText);

        sendMessageSubscription =
                RxMoreObservables.sendMessage(webSocketInterlocutor,
                                              SerializationUtils.toJson(encryptedMessage))
                                 .subscribe(success -> {
                                     if (success)
                                         onMessageSentInteractor.onSendMessageSuccess();
                                     else
                                         onMessageSentInteractor.onSendMessageError(
                                                 new Throwable(
                                                         context.getString(R.string.error_sending_message)));
                                 });
    }

    public void requestSearchCards(String identity) {
        Disposable searchCardDisposable =
                virgilRx.searchCards(identity)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe((cards, throwable) -> {
                            if (throwable == null && cards.size() > 0)
                                searchCardsInteractor.onSearchSuccess(cards);
                            else
                                searchCardsInteractor.onSearchError(throwable);
                        });

        compositeDisposable.add(searchCardDisposable);
    }

    @Override public void disposeAll() {
        compositeDisposable.clear();
        if (sendMessageSubscription != null)
            sendMessageSubscription.unsubscribe();
    }
}
