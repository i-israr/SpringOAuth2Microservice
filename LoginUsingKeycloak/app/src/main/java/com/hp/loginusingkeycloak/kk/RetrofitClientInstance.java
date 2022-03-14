package com.hp.loginusingkeycloak.kk;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Before working with keycloak we should Setting up our API environment
public class RetrofitClientInstance {

    private static Retrofit retrofit1;
    private static Retrofit retrofit2;
    //    private static final String BASE_URL = "http://127.0.0.1:8080";
    private static final String BASE_URL = "http://10.0.2.2:8080";
    private static final String BASE_URL_Rest = "http://10.0.2.2:8090";

    // private static final String BASE_URL = "http://192.168.10.110:8080";

//    https://stackoverflow.com/questions/5495534/java-net-connectexception-localhost-127-0-0-18080-connection-refused

    public static Retrofit getRetrofitInstance() {
        if (retrofit1 == null) {
            retrofit1 = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }

    public static Retrofit getRestRetrofitInstance() {
        if(retrofit2==null) {
            retrofit2 = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_Rest)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }

}
