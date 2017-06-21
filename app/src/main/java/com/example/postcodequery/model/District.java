package com.example.postcodequery.model;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class District {
        public int id;
        public String district;

        public void setCountyId(int countyId){
            this.id=countyId;
        }
        public  void setCountyName(String countyName){
            this.district=countyName;
        }
        public int getCountyId(){
            return  id;
        }
        public String getCountyName(){
            return district;
        }


}
