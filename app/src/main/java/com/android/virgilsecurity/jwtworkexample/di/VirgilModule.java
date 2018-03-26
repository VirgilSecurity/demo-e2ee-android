package com.android.virgilsecurity.jwtworkexample.di;

import com.android.virgilsecurity.jwtworkexample.data.local.PropertyManager;
import com.android.virgilsecurity.jwtworkexample.data.local.UserManager;
import com.android.virgilsecurity.jwtworkexample.data.remote.RxServiceHelper;
import com.android.virgilsecurity.jwtworkexample.data.remote.ServiceHelper;
import com.android.virgilsecurity.jwtworkexample.data.virgil.GetTokenCallbackImpl;
import com.android.virgilsecurity.jwtworkexample.ui.chat.ChatControlActivityComponent;
import com.virgilsecurity.sdk.crypto.CardCrypto;
import com.virgilsecurity.sdk.crypto.PrivateKeyExporter;
import com.virgilsecurity.sdk.crypto.VirgilCardCrypto;
import com.virgilsecurity.sdk.crypto.VirgilCrypto;
import com.virgilsecurity.sdk.crypto.VirgilPrivateKeyExporter;
import com.virgilsecurity.sdk.jwt.accessProviders.CallbackJwtProvider;
import com.virgilsecurity.sdk.jwt.contract.AccessTokenProvider;
import com.virgilsecurity.sdk.storage.JsonFileKeyStorage;
import com.virgilsecurity.sdk.storage.KeyStorage;
import com.virgilsecurity.sdk.storage.PrivateKeyStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

@Module(subcomponents = ChatControlActivityComponent.class)
public class VirgilModule {

    @Provides @Singleton static VirgilCrypto provideVirgilCrypto() {
        return new VirgilCrypto();
    }

    @Provides @Singleton static CardCrypto provideCardCrypto() {
        return new VirgilCardCrypto();
    }

    @Provides @Singleton static CallbackJwtProvider.GetTokenCallback provideGetTokenCallback(
            ServiceHelper serviceHelper,
            UserManager userManager) {

        return new GetTokenCallbackImpl(serviceHelper, userManager.getCurrentUser());
    }

    @Provides @Singleton static AccessTokenProvider provideAccessTokenProvider(
            CallbackJwtProvider.GetTokenCallback getTokenCallback) {

        return new CallbackJwtProvider(getTokenCallback);
    }

    @Provides @Singleton static PrivateKeyExporter providePrivateKeyExporter() {
        return new VirgilPrivateKeyExporter();
    }

    @Provides @Singleton static KeyStorage provideKeyStorage() {
        return new JsonFileKeyStorage();
    }

    @Provides @Singleton static PrivateKeyStorage providePrivateKeyStorage(
            PrivateKeyExporter privateKeyExporter,
            KeyStorage keyStorage) {

        return new PrivateKeyStorage(privateKeyExporter, keyStorage);
    }
}
