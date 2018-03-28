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

package com.android.virgilsecurity.jwtworkexample.ui.chat.threadList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.model.ChatThread;
import com.android.virgilsecurity.jwtworkexample.data.model.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Danylo Oliinyk on 3/27/18 at Virgil Security.
 * -__o
 */

public class ChatThreadsAdapter extends RecyclerView.Adapter<ChatThreadsAdapter.ChatThreadViewHolder> {

    private List<ChatThread> items;

    public ChatThreadsAdapter() {
        items = Collections.emptyList();
    }

    @Override public ChatThreadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_list_threads, parent, false);

        return new ChatThreadViewHolder(view);
    }

    @Override public void onBindViewHolder(ChatThreadViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return items != null ? items.size() : -1;
    }

    public void addItems(List<ChatThread> items) {
        if (items == null)
            items = new ArrayList<>();

        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public class ChatThreadViewHolder extends RecyclerView.ViewHolder {


        public ChatThreadViewHolder(View itemView) {
            super(itemView);
        }

        public void onBind(Message message) {

        }
    }
}
