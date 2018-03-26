package com.android.virgilsecurity.jwtworkexample.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

import com.android.virgilsecurity.jwtworkexample.R;
import com.android.virgilsecurity.jwtworkexample.data.local.PropertyManager;
import com.android.virgilsecurity.jwtworkexample.ui.base.BaseActivity;
import com.android.virgilsecurity.jwtworkexample.ui.chat.thread.ThreadFragment;
import com.android.virgilsecurity.jwtworkexample.ui.chat.threadList.ThreadsListFragment;
import com.android.virgilsecurity.jwtworkexample.ui.login.LogInActivity;
import com.android.virgilsecurity.jwtworkexample.util.UiUtils;
import com.android.virgilsecurity.jwtworkexample.util.common.OnFinishTimer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;

/**
 * Created by Danylo Oliinyk on 3/21/18 at Virgil Security.
 * -__o
 */

public class ChatControlActivity extends BaseActivity {

    private ThreadsListFragment threadsListFragment;
    private ThreadFragment threadFragment;
    private boolean secondPress;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.nvNavigation)
    protected NavigationView nvNavigation;
    @BindView(R.id.dlDrawer)
    protected DrawerLayout dlDrawer;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ChatState.THREADS_LIST, ChatState.THREAD})
    public @interface ChatState {
        String THREADS_LIST = "THREADS_LIST";
        String THREAD = "THREAD";
    }

    public static void start(Activity from) {
        from.startActivity(new Intent(from, ChatControlActivity.class));
    }

    public static void startWithFinish(Activity from) {
        from.startActivity(new Intent(from, ChatControlActivity.class));
        from.finish();
    }

    @Override protected int getLayout() {
        return 0;
    }

    @Override protected void postButterInit() {
        initToolbar(toolbar, getString(R.string.threads_list_name));
        initDrawer();

        threadsListFragment = (ThreadsListFragment) getFragmentManager().findFragmentById(R.id.threadsListFragment);
        threadFragment = (ThreadFragment) getFragmentManager().findFragmentById(R.id.threadFragment);

        changeFragment(ChatState.THREADS_LIST);

        hideKeyboard();
        showHamburger(true, view -> {
            if (!dlDrawer.isDrawerOpen(Gravity.START))
                dlDrawer.openDrawer(Gravity.START);
            else
                dlDrawer.closeDrawer(Gravity.START);
        });
    }

    public void changeFragment(@ChatState String tag) {
        if (tag.equals(ChatState.THREADS_LIST)) {
            UiUtils.hideFragment(getFragmentManager(), threadFragment);
            UiUtils.showFragment(getFragmentManager(), threadsListFragment);
        } else {
            UiUtils.hideFragment(getFragmentManager(), threadsListFragment);
            UiUtils.showFragment(getFragmentManager(), threadFragment);
        }
    }

    private void initDrawer() {
        TextView tvUsernameDrawer =
                nvNavigation.getHeaderView(0)
                            .findViewById(R.id.tvUsernameDrawer);
        tvUsernameDrawer.setText(PropertyManager.UserManager.getCurrentUser()
                                                            .getEmailPrefix());

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }

        nvNavigation.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.itemLogOut:
                    dlDrawer.closeDrawer(Gravity.START);
                    showBaseLoading(true);
                    threadFragment.disposeAll();
                    threadsListFragment.disposeAll();
                    LogInActivity.startClearTop(this);
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override public void onBackPressed() {

        if (secondPress)
            super.onBackPressed();
        else
            UiUtils.toast(this, getString(R.string.press_exit_once_more));

        secondPress = true;

        new OnFinishTimer(2000, 100) {

            @Override public void onFinish() {
                secondPress = false;
            }
        }.start();
    }
}
