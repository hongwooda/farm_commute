package com.example.farm_commute.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDataListResponse {

    @SerializedName("remaining")
    @Expose
    public String remaining;
    @SerializedName("result")
    @Expose
    public List<Result> result = null;

    public class Result implements Serializable {

        @SerializedName("companyID")
        @Expose
        public String companyID;
        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("onworkTime")
        @Expose
        public String onworkTime;
        @SerializedName("offworkTime")
        @Expose
        public String offworkTime;
        @SerializedName("total")
        @Expose
        public String total;
        @SerializedName("holiday_yn")
        @Expose
        public String holidayYn;

    }
}
