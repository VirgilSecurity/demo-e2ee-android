package com.android.virgilsecurity.jwtworkexample.ui.login;

import android.os.Bundle;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.ui.base.BaseFragmentDi;
import com.android.virgilsecurity.jwtworkexample.util.UiUtils;

import javax.inject.Inject;

/**
 * Created by Danylo Oliinyk on 3/21/18 at Virgil Security.
 * -__o
 */

public class LogInFragment extends BaseFragmentDi<LogInActivity> {

    @Inject LogInPresenter presenter;

    public static LogInFragment newInstance() {

        Bundle args = new Bundle();

        LogInFragment fragment = new LogInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override protected int getLayout() {
        return R.layout.fragment_log_in;
    }

    @Override protected void postButterInit() {
        UiUtils.toast(this, "yo");
        ((LogInPresenter)presenter).startTest("Hello everybody", text -> UiUtils.toast(this, text));
    }
}
