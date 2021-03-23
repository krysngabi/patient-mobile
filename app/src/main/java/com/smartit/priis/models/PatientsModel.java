package com.smartit.priis.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientsModel {
    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("age")
    @Expose
    private int age;

    @SerializedName("id_patient")
    @Expose
    private int id_patient;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }
}
