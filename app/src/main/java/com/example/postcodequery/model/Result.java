package com.example.postcodequery.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class Result {
    public int id;
    public String province;

    @SerializedName("city")
    public List<City> citys;

    public List<City> getCitys(){
        return  citys;
    }

    public void setProvinceId(int provinceId){
        this.id=provinceId;
    }
    public  void setProvinceName(String provinceName){
        this.province=provinceName;
    }
    public int getProvinceId(){
        return id;
    }
    public String getProvinceName(){
        return province;
    }
}
