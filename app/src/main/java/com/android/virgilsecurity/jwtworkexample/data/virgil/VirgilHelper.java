package com.android.virgilsecurity.jwtworkexample.data.virgil;

import com.virgilsecurity.sdk.cards.CardManager;
import com.virgilsecurity.sdk.cards.validation.CardVerifier;
import com.virgilsecurity.sdk.crypto.CardCrypto;
import com.virgilsecurity.sdk.jwt.contract.AccessTokenProvider;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class VirgilHelper {

    private final CardManager cardManager;

    public VirgilHelper(InitCardCrypto initCardCrypto,
                        InitAccessTokenProvider initAccessTokenProvider,
                        InitCardVerifier initCardVerifier) {
        cardManager = initCardManager(initCardCrypto, initAccessTokenProvider, initCardVerifier);
    }

    private CardManager initCardManager(InitCardCrypto initCardCrypto,
                                 InitAccessTokenProvider initAccessTokenProvider,
                                 InitCardVerifier initCardVerifier) {
        return new CardManager.Builder()
                .setCrypto(initCardCrypto.initialize())
                .setAccessTokenProvider(initAccessTokenProvider.initialize())
                .setCardVerifier(initCardVerifier.initialize())
                .build();
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    private interface InitCardCrypto {
        CardCrypto initialize();
    }

    private interface InitAccessTokenProvider {
        AccessTokenProvider initialize();
    }

    private interface InitCardVerifier {
        CardVerifier initialize();
    }
}
