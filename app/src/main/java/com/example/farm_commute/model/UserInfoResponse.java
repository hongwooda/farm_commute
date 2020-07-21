package com.example.farm_commute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserInfoResponse {

    @SerializedName("companyID")
    @Expose
    public String companyID;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("profileURL")
    @Expose
    public String profileURL;

}
