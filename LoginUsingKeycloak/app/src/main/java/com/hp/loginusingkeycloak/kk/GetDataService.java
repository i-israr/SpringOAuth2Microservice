package com.hp.loginusingkeycloak.kk;

import com.hp.loginusingkeycloak.kk.AccessToken;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {

// We will get access token and refresh token from keycloak that's way we need to post this methode to get information
    @FormUrlEncoded
    @POST("/auth/realms/fitness2/protocol/openid-connect/token")
    Call<AccessToken> getAccessToken(
            @Field("client_id") String client_id,
            @Field("grant_type") String grant_type,
            @Field("client_secret") String client_secret,
            @Field("scope") String scope,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/auth/realms/fitness2/protocol/openid-connect/logout")
    Call<ResponseBody> logout(
            @Field("client_id") String client_id,
            @Field("refresh_token") String refreshToken,
            @Field("client_secret") String client_secret
    );

    @GET("/ship/list")
    Call<ResponseBody> getAllData(@Query("username") String username,@Header("Authorization") String authHeader);

}
