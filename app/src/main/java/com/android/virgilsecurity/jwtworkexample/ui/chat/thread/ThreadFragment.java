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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.model.DefaultMessage;
import com.android.virgilsecurity.jwtworkexample.data.model.ResponseType;
import com.android.virgilsecurity.jwtworkexample.data.model.response.DefaultResponse;
import com.android.virgilsecurity.jwtworkexample.data.model.response.UsersResponse;
import com.android.virgilsecurity.jwtworkexample.ui.base.BaseFragmentDi;
import com.android.virgilsecurity.jwtworkexample.ui.chat.ChatControlActivity;
import com.android.virgilsecurity.jwtworkexample.util.SerializationUtils;
import com.appunite.websocket.rx.RxWebSockets;
import com.appunite.websocket.rx.messages.RxEventStringMessage;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Danylo Oliinyk on 3/21/18 at Virgil Security.
 * -__o
 */

public class ThreadFragment extends BaseFragmentDi<ChatControlActivity> {

    @Inject protected RxWebSockets rxWebSockets;
    @Inject protected ThreadRVAdapter adapter;

    @BindView(R.id.rvChat) protected RecyclerView rvChat;

    @Override protected int getLayout() {
        return R.layout.fragment_thread;
    }

    @Override protected void postButterInit() {
        rxWebSockets.webSocketObservable()
                    .subscribe(rxEvent -> {
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

                                activity.runOnUiThread(() -> {
                                    adapter.addItem(message);
                                    activity.showBaseLoading(false);
                                });
                            }
                        }
                    });

        rvChat.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setReverseLayout(true);
        rvChat.setLayoutManager(layoutManager);
    }

    public void disposeAll() {

    }
}
