package com.kaya.risk31app.Service;

import com.kaya.risk31app.Models.User;

import java.util.List;

public class UserResponse {
    List<User> data;
    User user;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
