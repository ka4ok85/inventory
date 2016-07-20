package com.example.security;

import java.io.Serializable;

public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;
    private String storeId;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password, String storeId) {
        this.setUsername(username);
        this.setPassword(password);
        this.setStoreId(storeId);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getDetails() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

	@Override
	public String toString() {
		return "JwtAuthenticationRequest [username=" + username + ", password=" + password + ", storeId=" + storeId
				+ "]";
	}
    
    
}