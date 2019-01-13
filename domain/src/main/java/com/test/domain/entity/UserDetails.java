package com.test.domain.entity;

/**
 * @author Evgeny Butov
 * @version 1.0
 * @since 11.01.2019
 */
public class UserDetails implements DomainModel {
    private String avatarUrl;
    private String login;
    private String email;
    private int followers;
    private int following;
    private String createdAt;

    public UserDetails(String avatarUrl, String login, String email, int followers, int following, String createdAt) {
        this.avatarUrl = avatarUrl;
        this.login = login;
        this.email = email;
        this.followers = followers;
        this.following = following;
        this.createdAt = createdAt;
    }

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
