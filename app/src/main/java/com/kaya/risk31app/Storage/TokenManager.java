package com.kaya.risk31app.Storage;

import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;


    private TokenManager(SharedPreferences preferences) {
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    public static synchronized TokenManager getINSTANCE(SharedPreferences preferences) {
        if (INSTANCE == null) {
            INSTANCE = new TokenManager(preferences);
        }
        return INSTANCE;
    }
    public void saveToken(AccessToken token) {
      editor.putString("ACCESS_TOKEN", token.getAccessToken()).commit();
      editor.putString("REFRESH_TOKEN", token.getRefreshToken()).commit();
    }

    public void deleteToken() {
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }
    public AccessToken getToken() {
        AccessToken token = new AccessToken();
        token.setAccessToken(preferences.getString("ACCESS_TOKEN", null));
        token.setRefreshToken(preferences.getString("REFRESH_TOKEN", null));
        return token;
    }
}
