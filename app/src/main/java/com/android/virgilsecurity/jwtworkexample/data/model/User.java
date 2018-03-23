package com.android.virgilsecurity.jwtworkexample.data.model;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public class User {

    final private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailPrefix() {
        return email.split("@")[0];
    }
}
