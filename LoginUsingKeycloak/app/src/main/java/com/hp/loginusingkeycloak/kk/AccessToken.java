package com.hp.loginusingkeycloak.kk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccessToken  implements Serializable {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Integer expiresIn;
    @SerializedName("refresh_expires_in")
    private Integer refreshExpiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("id_token")
    private String idToken;
    @SerializedName("not_before_policy")
    private Integer notBeforePolicy;
    @SerializedName("session_state")
    private String sessionState;
    @SerializedName("scope")
    private String scope;

    public AccessToken(String accessToken, Integer expiresIn){
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
    public String getRefreshToken(){
        return this.refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Integer refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public Integer getNotBeforePolicy() {
        return notBeforePolicy;
    }

    public void setNotBeforePolicy(Integer notBeforePolicy) {
        this.notBeforePolicy = notBeforePolicy;
    }

    public String getSessionState() {
        return sessionState;
    }

    public void setSessionState(String sessionState) {
        this.sessionState = sessionState;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}


















