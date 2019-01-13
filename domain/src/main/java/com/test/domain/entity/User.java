package com.test.domain.entity;


public class User implements DomainModel {

    private String avatarUrl;
    private String login;
    private int id;

    public User(String avatarUrl, String login, int id) {
        this.avatarUrl = avatarUrl;
        this.login = login;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
