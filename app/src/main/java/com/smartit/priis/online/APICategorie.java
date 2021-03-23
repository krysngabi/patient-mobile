package com.smartit.priis.online;

/**
 * Created by User on 25/08/2017.
 */


import com.smartit.priis.models.PatientsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Shaon on 8/15/2016.
 */
public interface APICategorie {

    @FormUrlEncoded
    @POST("api/find/patientCalls.php")
    Call<MSG> newPatient(@Field("insertPatient") String keyPatient,@Field("firstname") String fname,
                          @Field("lastname") String lname, @Field("age") String age);

    @GET("api/find/getPatients.php")
    Call<List<PatientsModel>>  getPatients();


}