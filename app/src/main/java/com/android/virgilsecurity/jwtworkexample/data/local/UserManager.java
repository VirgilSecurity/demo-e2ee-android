package com.android.virgilsecurity.jwtworkexample.data.local;

import android.content.Context;

import com.android.virgilsecurity.jwtworkexample.data.model.User;
import com.google.gson.Gson;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class UserManager extends PropertyManager {

    private static final String CURRENT_USER = "CURRENT_USER";

    public UserManager(Context context) {
        super(context);
    }

    public void setCurrentUser(User user) {
        setValue(CURRENT_USER, new Gson().toJson(user));
    }

    public User getCurrentUser() {
        return getValue(CURRENT_USER,
                        PropertyManager.SupportedTypes.STRING,
                        null);
    }
}