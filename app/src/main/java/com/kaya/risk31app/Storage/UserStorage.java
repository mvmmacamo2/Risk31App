package com.kaya.risk31app.Storage;

import android.content.SharedPreferences;

import com.kaya.risk31app.Models.User;
import com.kaya.risk31app.Service.UserProfileService;

public class UserStorage {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static UserStorage INSTANCE = null;

    private UserStorage(SharedPreferences preferences) {
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    public static synchronized UserStorage getINSTANCE(SharedPreferences preferences) {
        if (INSTANCE == null) {
            INSTANCE = new UserStorage(preferences);
        }
        return INSTANCE;
    }

    public void saveUserProfile(UserProfileService user) {

        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("nivel", user.getNivel());
        editor.putString("foto", user.getFoto());
        editor.putString("biografia", user.getBiografia());
        editor.putString("endereco", user.getEndereco());
        editor.putString("telefone", user.getTelefone());
        editor.putString("celular", user.getCelular());
        editor.putString("lingua", user.getLingua());
        editor.apply();

    }

    public UserProfileService getUser() {
        UserProfileService user = new UserProfileService();
        user.setId(preferences.getInt("id", 1));
        user.setName(preferences.getString("name", null));
        user.setEmail(preferences.getString("email", null));
        user.setNivel(preferences.getString("nivel", null));
        user.setFoto(preferences.getString("foto", null));
        user.setBiografia(preferences.getString("biografia", null));
        user.setEndereco(preferences.getString("endereco", null));
        user.setTelefone(preferences.getString("telefone", null));
        user.setCelular(preferences.getString("celular", null));
        user.setLingua(preferences.getString("lingua", null));

        return user;
    }
    public void deleteUserProfile(){
        editor.remove("id").commit();
        editor.remove("name").commit();
        editor.remove("nivel").commit();
        editor.remove("email").commit();
        editor.remove("profile").commit();
        editor.remove("foto").commit();
        editor.remove("biografia").commit();
        editor.remove("endereco").commit();
        editor.remove("telefone").commit();
        editor.remove("celular").commit();
        editor.remove("lingua").commit();
    }
}
