package com.android.virgilsecurity.jwtworkexample.ui.chat;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

@Subcomponent(modules = ChatControlActivityModule.class)
public interface ChatControlActivityComponent {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ChatControlActivity> {
    }

}
