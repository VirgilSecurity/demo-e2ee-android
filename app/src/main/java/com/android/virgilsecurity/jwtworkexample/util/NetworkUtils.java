package com.android.virgilsecurity.jwtworkexample.util;

import com.virgilsecurity.sdk.client.exceptions.VirgilCardIsNotFoundException;
import com.virgilsecurity.sdk.client.exceptions.VirgilKeyIsAlreadyExistsException;
import com.virgilsecurity.sdk.client.exceptions.VirgilKeyIsNotFoundException;
import com.virgilsecurity.sdk.crypto.exceptions.KeyEntryNotFoundException;

import retrofit2.HttpException;

/**
 * Created by Danylo Oliinyk on 3/21/18 at Virgil Security.
 * -__o
 */

public class NetworkUtils {

    public static String resolveError(Throwable t) {
        if (t instanceof HttpException) {
            HttpException exception = (HttpException) t;

            switch (exception.code()) {
                case Const.Http.BAD_REQUEST:
                    return "Bad Request";
                case Const.Http.UNAUTHORIZED:
                    return "Unauthorized";
                case Const.Http.FORBIDDEN:
                    return "Forbidden";
                case Const.Http.NOT_ACCEPTABLE:
                    return "Not acceptable";
                case Const.Http.UNPROCESSABLE_ENTITY:
                    return "Unprocessable entity";
                case Const.Http.SERVER_ERROR:
                    return "Server error";
                default:
                    return "Oops.. Something went wrong ):";
            }
        } else if (t instanceof VirgilKeyIsNotFoundException) {
            return "Username is not registered yet";
        } else if (t instanceof VirgilKeyIsAlreadyExistsException) {
            return "Username is already registered. Please, try another one.";
        } else if (t instanceof KeyEntryNotFoundException) {
            return "Username is not found on this device. Maybe you deleted your private key";
        } else if (t instanceof VirgilCardIsNotFoundException) {
            return "Virgil Card is not found.\nYou can not start chat with user without Virgil Card.";
        } else {
            return "Something went wrong";
        }
    }
}
