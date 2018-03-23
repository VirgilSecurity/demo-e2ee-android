package com.android.virgilsecurity.jwtworkexample.data.local;

import com.android.virgilsecurity.jwtworkexample.data.model.User;
import com.google.gson.Gson;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class UserManager {

    public static final String CURRENT_USER = "CURRENT_USER";

    public static void setCurrentUser(User user) {
        PropertyManager.setValue(CURRENT_USER, new Gson().toJson(user));
    }

    public static User getCurrentUser() {
        return PropertyManager.getValue(CURRENT_USER,
                                        PropertyManager.SupportedTypes.STRING,
                                        null);
    }
}
