package com.test.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse implements DataModel{

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private int id;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
