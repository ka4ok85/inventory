package com.example.wrappers;

import com.example.entity.User;
import com.fasterxml.jackson.annotation.JsonView;

public class UserWrapperShort {

    @JsonView(com.example.wrappers.UserWrapperShort.class)
    private Long id;

    @JsonView(com.example.wrappers.UserWrapperShort.class)
    private String login;

    public UserWrapperShort(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserWrapperShort [id=" + id + ", login=" + login + "]";
    }

}

