package com.example.farm_commute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class QuotesResponse {

    @SerializedName("quote")
    @Expose
    public String quote;
    @SerializedName("person")
    @Expose
    public String person;


}
