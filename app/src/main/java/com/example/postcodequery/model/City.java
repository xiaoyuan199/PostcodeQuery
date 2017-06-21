package com.example.postcodequery.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class City {

    public int id;
    public String city;
    @SerializedName("district")
    public List<District> districts;

public List<District> getDistricts(){
    return districts;

}
    public void setCityId(int cityId){
        this.id=cityId;
    }
    public  void setCityName(String cityName){
        this.city=cityName;
    }
    public  int getCityId(){
        return  id;
    }
    public String getCityName(){
        return city;
    }




}
