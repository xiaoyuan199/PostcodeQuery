package com.example.postcodequery.model2;

import com.example.postcodequery.model2.Result;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class ValuePost {
    @SerializedName("msg")
    public String msg;

    @SerializedName("result")
    public List<Result> postNumList;

    @SerializedName("retCode")
    public String retCode;

    public List<Result> getPostNumList(){
        return  postNumList;
    }
}
