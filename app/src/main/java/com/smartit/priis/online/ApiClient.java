package com.smartit.priis.online;

/**
 * Created by Ngabi on 25/08/2017.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shaon on 11/7/2016.
 */

public class ApiClient {
    //put the ip address  and the name of your php project here
    public static final String BASE_URL_PATIENT = "http://10.0.2.2/patient/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient2( ) {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_PATIENT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}