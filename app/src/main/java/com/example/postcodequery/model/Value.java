package com.example.postcodequery.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class Value {
    public String msg;

    @SerializedName("result")
    public List<Result> provinces;
    public String retCode;


  public List<Result> getProvinces(){
      return provinces;
  };

    public void setProvinces(ArrayList<Result> provinces1){
        this.provinces=provinces1;
    }
    public String getMessage(){
       return msg;
    }

}
