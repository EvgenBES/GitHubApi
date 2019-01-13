package com.test.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Evgeny Butov
 * @version 1.0
 * @since 11.01.2019
 */
public class UserDetailsResponce implements DataModel{

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("following")
    @Expose
    private int following;
    @SerializedName("created_at")
    @Expose
    private String createdAt;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
