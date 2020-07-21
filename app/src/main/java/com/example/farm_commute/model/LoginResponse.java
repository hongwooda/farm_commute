package com.example.farm_commute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LoginResponse {
    @SerializedName("result")
    @Expose
    public String result;

}
