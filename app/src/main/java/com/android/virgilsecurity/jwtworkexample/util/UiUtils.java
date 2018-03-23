package com.android.virgilsecurity.jwtworkexample.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import com.android.virgilsecurity.jwtworkexample.R;

/**
 * Created by Danylo Oliinyk on 11/17/17 at Virgil Security.
 * -__o
 */

public class UiUtils {

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Fragment fragment, String text) {
        Toast.makeText(fragment.getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int stringResId) {
        Toast.makeText(context,
                       context.getString(stringResId),
                       Toast.LENGTH_SHORT).show();
    }

    public static void toast(Fragment fragment, int stringResId) {
        Toast.makeText(fragment.getActivity(),
                       fragment.getActivity().getString(stringResId),
                       Toast.LENGTH_SHORT).show();
    }

    public static void log(String tag, String text) {
        Log.d(tag, text);
    }

    public static void replaceFragmentNoTag(FragmentManager fm, int containerId, Fragment fragment) {
        fm.beginTransaction()
          .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
          .replace(containerId, fragment)
          .commit();
    }

    public static void replaceFragmentNoBackStack(FragmentManager fm, int containerId, Fragment fragment, String tag) {
        fm.beginTransaction()
          .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
          .replace(containerId, fragment, tag)
          .commit();
    }

    public static void replaceFragment(FragmentManager fm, int containerId, Fragment fragment, String tag) {
        fm.beginTransaction()
          .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
          .replace(containerId, fragment, tag)
          .addToBackStack(tag)
          .commit();
    }

    public static void showFragment(FragmentManager fm, Fragment fragment) {
        fm.beginTransaction()
          .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
          .show(fragment)
          .commit();
    }

    public static void hideFragment(FragmentManager fm, Fragment fragment) {
        fm.beginTransaction()
          .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
          .hide(fragment)
          .commit();
    }
}
