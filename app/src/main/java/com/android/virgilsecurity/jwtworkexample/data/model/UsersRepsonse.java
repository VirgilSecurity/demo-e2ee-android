package com.android.virgilsecurity.jwtworkexample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public class UsersRepsonse {

    @SerializedName("users")
    final private List<User> users;

    @SerializedName("message")
    final private String message;

    @SerializedName("status_code")
    final private String statusCode;

    public UsersRepsonse(List<User> users, String message, String statusCode) {
        this.users = users;
        this.message = message;
        this.statusCode = statusCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
