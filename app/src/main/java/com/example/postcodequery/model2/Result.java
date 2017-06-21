package com.example.postcodequery.model2;

import android.location.Address;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class Result {

    @SerializedName("address")
    public List<String> addresses;
    @SerializedName("cId")
    public  String cId;
    @SerializedName("city")
    public  String city;
    @SerializedName("dId")
    public  String dId;
    @SerializedName("district")
    public  String district;
    @SerializedName("province")
    public  String province;
    @SerializedName("size")
    public  String size;
    @SerializedName("pId")
    public  String pId;
    @SerializedName("postNumber")
    public   String postNumber;



    public List<String> getAddresses(){
        return addresses;
    }

    public String getPostNumber(){
        return  postNumber;
    }
}
