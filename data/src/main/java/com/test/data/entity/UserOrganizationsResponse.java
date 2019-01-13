package com.test.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Evgeny Butov
 * @version 1.0
 * @since 13.01.2019
 */
public class UserOrganizationsResponse implements DataModel{

    @SerializedName("login")
    @Expose
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
