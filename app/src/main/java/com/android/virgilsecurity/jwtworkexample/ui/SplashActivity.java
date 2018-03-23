package com.android.virgilsecurity.jwtworkexample.ui;

import android.app.Activity;
import android.os.Bundle;

import com.android.virgilsecurity.jwtworkexample.ui.chat.ChatControlActivity;
import com.android.virgilsecurity.jwtworkexample.ui.login.LogInActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isAuthenticated()) {
            LogInActivity.startWithFinish(this);
        } else {
            ChatControlActivity.startWithFinish(this);
        }
    }

    private boolean isAuthenticated() {
        return true;
    }

    @Override public void onBackPressed() {
        // Must be empty, so we can't press back from the splash screen
    }
}
