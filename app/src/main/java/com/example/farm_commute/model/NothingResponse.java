package com.example.farm_commute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class NothingResponse {

    @SerializedName("result")
    @Expose
    public String result;

}
