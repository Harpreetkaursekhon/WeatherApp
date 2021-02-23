package com.cloud.myweatherapp;

import com.cloud.myweatherapp.mymodel.Data;
import com.cloud.myweatherapp.view.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Test   {

    private void hh()
    {

        String name=null;
        ArrayList<Data> countryList=new ArrayList<>();
        Collections.reverse(countryList);
        Date ct= Calendar.getInstance().getTime();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String cd=sdf.format(new Date());

//        String wethertype;
//        if(wethertype!=null)
//        {
//            tv_weather_type.setT;
//        }




        //
        ArrayList<Data> tempList=new ArrayList<>();

        for (int i=0;i<countryList.size();i++)
        {
           // hashSet.add(item.get(i)._country.get_name());
            if(countryList.get(i).get_country().get_name().equals(name))
            {
                tempList.add(countryList.get(i));
            }
        }


    }

}
