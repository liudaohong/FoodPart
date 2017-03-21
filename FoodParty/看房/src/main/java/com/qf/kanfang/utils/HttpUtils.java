package com.qf.kanfang.utils;

import com.qf.kanfang.bean.CityBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
public class HttpUtils {

    static final String[] letters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };


    //解析城市数据
    public static List<CityBean> getCities(String data) throws JSONException {
        //声明数据集合
        ArrayList<CityBean> cities = new ArrayList<>();
        //解析JSONObject
        JSONObject object = new JSONObject(data);
        //得到citiesObject
        JSONObject citiesObject = object.getJSONObject("cities");
        // A-Z解析
        //从字母数组中，取出每个字母，
        for (int i = 0; i < letters.length; i++) {
            //从JSON中，按字母取出每个JSONArray，循环解析SJONArray
            JSONArray arr = citiesObject.optJSONArray(letters[i]);
            if (arr != null) {
                int len = arr.length();
                for (int j = 0; j < len; j++) {
                    JSONObject obj = arr.getJSONObject(j);
                    //创建CityBean
                    CityBean bean = new CityBean(letters[i], i, obj);
                    //添加到集合中
                    cities.add(bean);
                }
            }

        }

        //返回数据集合
        return cities;
    }
}
