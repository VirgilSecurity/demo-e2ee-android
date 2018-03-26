package com.android.virgilsecurity.jwtworkexample.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;

import com.android.virgilsecurity.jwtworkexample.data.model.User;
import com.google.gson.Gson;

/**
 * Created by Danylo Oliinyk on 3/23/18 at Virgil Security.
 * -__o
 */

public class PropertyManager {

    @StringDef({
                       SupportedTypes.STRING,
                       SupportedTypes.BOOLEAN,
                       SupportedTypes.INTEGER,
                       SupportedTypes.FLOAT
               })
    public @interface SupportedTypes {
        String STRING = "STRING";
        String BOOLEAN = "BOOLEAN";
        String INTEGER = "INTEGER";
        String FLOAT = "FLOAT";
    }

    private static SharedPreferences preferences;

    public PropertyManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private void edit(Performer<SharedPreferences.Editor> performer) {
        SharedPreferences.Editor editor = preferences.edit();
        performer.performOperation(editor);
        editor.apply();
    }

    public <T> void setValue(String key, T value) {

        if (value instanceof String) {
            edit((editor) -> editor.putString(key, (String) value));
        } else if (value instanceof Boolean) {
            edit((editor) -> editor.putBoolean(key, (Boolean) value));
        } else if (value instanceof Integer) {
            edit((editor) -> editor.putInt(key, (Integer) value));
        } else if (value instanceof Float) {
            edit((editor) -> editor.putFloat(key, (Float) value));
        } else {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
    }

    public <T> T getValue(String key, @SupportedTypes String type, T defaultValue) {

        Object value;
        if (type.equals(SupportedTypes.STRING)) {
            value = preferences.getString(key, (String) defaultValue);
        } else if (type.equals(SupportedTypes.BOOLEAN)) {
            value = preferences.getBoolean(key, (Boolean) defaultValue);
        } else if (type.equals(SupportedTypes.INTEGER)) {
            value = preferences.getInt(key, (Integer) defaultValue);
        } else if (type.equals(SupportedTypes.FLOAT)) {
            value = preferences.getFloat(key, (Float) defaultValue);
        } else {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
        return (T) value;
    }

    public interface Performer<T> {
        void performOperation(T victim);
    }
}
