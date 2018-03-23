package com.android.virgilsecurity.jwtworkexample.ui.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.ui.base.BaseActivityDi;
import com.android.virgilsecurity.jwtworkexample.util.UiUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import retrofit2.Retrofit;

public class LogInActivity extends BaseActivityDi implements HasFragmentInjector {

    @Inject DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    public static void start(Activity from) {
        from.startActivity(new Intent(from, LogInActivity.class));
    }

    public static void startWithFinish(Activity from) {
        from.startActivity(new Intent(from, LogInActivity.class));
        from.finish();
    }

    public static void startClearTop(Activity from) {
        from.startActivity(new Intent(from, LogInActivity.class)
                                   .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                                     | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override protected int getLayout() {
        return R.layout.activity_log_in;
    }

    @Override protected void postButterInit() {
        UiUtils.replaceFragmentNoTag(getFragmentManager(),
                                     R.id.flBaseContainer,
                                     LogInFragment.newInstance());
    }


    @Override public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
