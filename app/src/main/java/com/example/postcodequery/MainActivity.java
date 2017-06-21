package com.example.postcodequery;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.postcodequery.adapter.AreaNameAdapter;
import com.example.postcodequery.model.AreaName;
import com.example.postcodequery.model.City;
import com.example.postcodequery.model.District;
import com.example.postcodequery.model.Result;
import com.example.postcodequery.model.Value;
import com.example.postcodequery.model2.ValuePost;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    RequestQueue mqueue;
    final String url="http://apicloud.mob.com/v1/postcode/pcd?key=1da379ab2eb90";
     String post="http://apicloud.mob.com/v1/postcode/search?key=1da379ab2eb90";
     String posturl;

    Value value=new Value();
    ValuePost valuePost=new ValuePost();;


    private AreaNameAdapter adapter;
    private List<AreaName> areaList=new ArrayList<>();
    public List<String> addresses=new ArrayList<>();

    private ListView listView;
    private Button backButton;
    private TextView titleText;

    private List<Result> provincesList;
    private List<City> cityList;
    private List<District> districtList;

    private  List<com.example.postcodequery.model2.Result> postList=new ArrayList<>();

    public static final int LEVEL_PROVINCE=0;
    public  static final int LEVEL_CITY=1;
    public  static  final int LEVEL_COUNTY=2;
    public  static  final int LEVEL_POSTNUM=3;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    /**
     * 选中的城市
     */
    private  City selectedCity;
    /**
     * 选中的省
     */
    private Result selectProvince;
    /**
     * 选中的省id
     */
    private int selectProvinceid;
    /**
     * 选中的城市id
     */
    private  int selectedCityid;
    /**
     * 选中的县/区id
     */
    private int selectDisid;
    /**
     * 选中的县/区
     */
    private String selectDistrict;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mqueue= Volley.newRequestQueue(this);

        titleText= (TextView) findViewById(R.id.title_text);
        backButton= (Button) findViewById(R.id.back_button);
        listView= (ListView) findViewById(R.id.area_list);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(currentLevel==LEVEL_COUNTY){
                   queryCity();
               }else if(currentLevel==LEVEL_CITY){
                   queryProvince();
                   backButton.setVisibility(View.GONE);
               }else if(currentLevel==LEVEL_POSTNUM){
                    queryDistrict();
               }
            }
        });

        listView.setOnItemClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            adapter=new AreaNameAdapter(MainActivity.this
                    ,R.layout.list_item , areaList);

        }

        netRequest();



    }

    private void netRequest() {


        backButton.setVisibility(View.GONE);

        StringRequest reuest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                    try{
                        Gson gson=new Gson();
                        value=gson.fromJson(s,Value.class);
                        Log.d("linguiyuan",value.msg+value.retCode);

                        if(value.getProvinces()!=null&&value.getProvinces().size()>0){

                            if(provincesList==null){
                                provincesList=new ArrayList<>();
                            }
                            provincesList.clear();
                            provincesList=value.provinces;
                        }

                        queryProvince();


                    }catch (JsonSyntaxException e){

                        Snackbar.make(listView,"网络异常",Snackbar.LENGTH_SHORT).show();

}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Snackbar.make(listView,"网络异常",Snackbar.LENGTH_SHORT).show();

            }
        });

      mqueue.add(reuest);

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(currentLevel==LEVEL_PROVINCE) {
            selectProvince = provincesList.get(position);
            queryCity();


        }else if(currentLevel==LEVEL_CITY){

            selectedCity=cityList.get(position);
            queryDistrict();

            }else if (currentLevel==LEVEL_COUNTY){
            selectDisid=districtList.get(position).getCountyId();
            selectDistrict=districtList.get(position).getCountyName();
            queryPostNum();

        }




    }

    private void queryPostNum() {

         posturl=post+"&pid="+selectProvinceid+"&cid="+selectedCityid+"&did="+selectDisid;

        StringRequest postrequest=new StringRequest(Request.Method.GET, posturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                      try {
                          Gson gson=new Gson();

                          valuePost=gson.fromJson(s,ValuePost.class);
                         // Log.d("我想要的：",""+selectProvinceid+selectedCityid+selectDisid);
                          Log.d("我想要的：",""+s);
                          if(valuePost!=null){



                                  postList=valuePost.getPostNumList();



                              if(postList!=null){

                                  if(areaList!=null){
                                      areaList.clear();
                                  }

                                  for(com.example.postcodequery.model2.Result postNum:postList){

                                     addresses=postNum.getAddresses();
                                      areaList.add(new AreaName("邮编"+postNum.postNumber+":"));

                                      for(String ad:addresses){
                                       //  Log.d("邮编地址：：", ad);
                                          areaList.add(new AreaName(ad));
                                      }


                                  }


                                  if(adapter!=null){

                                    adapter.notifyDataSetChanged();
                                     listView.setAdapter(adapter);
                                      titleText.setText(selectDistrict);
                                      currentLevel=LEVEL_POSTNUM;


                                  }

                              }
                          }


                      }catch (JsonSyntaxException e){
                          Log.d("邮编：", ""+e);
                      }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mqueue.add(postrequest);
    }

    private void queryProvince() {

        if(areaList!=null){
            areaList.clear();
        }

        if(provincesList!=null) {
            for (Result procvince : provincesList) {
                     areaList.add(new AreaName(procvince.getProvinceName()));
                }


            if (adapter != null) {
                 adapter.notifyDataSetChanged();
                 listView.setSelection(0);
                listView.setAdapter(adapter);
                currentLevel = LEVEL_PROVINCE;
                titleText.setText("中国");

    }
}

    }

    private void queryDistrict() {

        if(districtList==null){
            districtList=new ArrayList<>();
        }
        districtList=selectedCity.getDistricts();

        selectedCityid=selectedCity.getCityId();
        Log.d("111111", "queryCity: "+selectedCityid);

        if(areaList!=null){
            areaList.clear();
        }
        for (District disteicts : selectedCity.getDistricts()) {
            areaList.add(new AreaName(disteicts.getCountyName()));
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        listView.setAdapter(adapter);
        titleText.setText(selectedCity.getCityName());
        currentLevel=LEVEL_COUNTY;
        backButton.setVisibility(View.VISIBLE);
    }

    private void queryCity() {

        if (cityList == null) {
            cityList = new ArrayList<>();
        }

        cityList = selectProvince.getCitys();

        selectProvinceid=selectProvince.getProvinceId();
        Log.d("888888", "queryProvince: "+selectProvinceid);

            if (areaList != null) {
                areaList.clear();
            }

            for (City citys1 : selectProvince.getCitys()) {
                areaList.add(new AreaName(citys1.getCityName()));
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            listView.setAdapter(adapter);
            currentLevel = LEVEL_CITY;
            titleText.setText(selectProvince.getProvinceName());
            backButton.setVisibility(View.VISIBLE);


    }


}
