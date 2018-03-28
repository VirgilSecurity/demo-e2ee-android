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

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.model.Message;
import com.android.virgilsecurity.jwtworkexample.ui.chat.threadList.ChatThreadsAdapter;
import com.virgilsecurity.sdk.client.exceptions.VirgilKeyIsNotFoundException;
import com.virgilsecurity.sdk.crypto.PrivateKey;
import com.virgilsecurity.sdk.crypto.VirgilPrivateKey;
import com.virgilsecurity.sdk.crypto.exceptions.CryptoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Danylo Oliinyk on 3/27/18 at Virgil Security.
 * -__o
 */

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.MessageViewHolder> {

    @IntDef({MessageType.ME, MessageType.YOU})
    private @interface MessageType {
        int ME = 0;
        int YOU = 1;
    }

    private List<Message> items;

    ThreadAdapter() {
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        MessageViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case MessageType.ME:
                viewHolder = new MessageViewHolder(inflater.inflate(R.layout.layout_holder_me,
                                                                    viewGroup,
                                                                    false));
                break;
            case MessageType.YOU:
                viewHolder = new MessageViewHolder(inflater.inflate(R.layout.layout_holder_you,
                                                                    viewGroup,
                                                                    false));
                break;
            default:
                viewHolder = new MessageViewHolder(inflater.inflate(R.layout.layout_holder_me,
                                                                    viewGroup,
                                                                    false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int position) {
        viewHolder.bind(items.get(position));
    }

    @Override public int getItemViewType(int position) {
        if (items.get(position).getSender()
                 .equals("")) { // FIXME: 3/27/18 get current user
            return MessageType.ME;
        } else {
            return MessageType.YOU;
        }
    }

    @Override public int getItemCount() {
        return items != null ? items.size() : -1;
    }

    void setItems(List<Message> items) {
        if (items != null) {
            this.items = new ArrayList<>(items);
        } else {
            this.items = Collections.emptyList();
        }
        notifyDataSetChanged();
    }

    void addItems(List<Message> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    void addItem(int position, Message item) {
        if (items == null)
            items = new ArrayList<>();

        items.add(position, item);
        notifyDataSetChanged();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private PrivateKey privateKey;

        @BindView(R.id.tvMessage) TextView tvMessage;

        MessageViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            privateKey = new VirgilPrivateKey(); // FIXME: 3/27/18 load user's key
        }

        void bind(Message message) {
            tvMessage.setText(decrypt(message.getText()));
        }

        /**
         * Decrypt data
         *
         * @param text to encrypt
         * @return decrypted data
         */
        String decrypt(String text) {
//            try {
//                return privateKey.decrypt(text)
//                                .toString();
//            } catch (CryptoException e) {
//                e.printStackTrace();
//                return "";
//            }
            return "";
        }
    }
}
