package com.liudaohong.foodparty.Iterface;

import com.liudaohong.foodparty.Bean.FoodTabBean;
import com.liudaohong.foodparty.Bean.FoodVp0Bean;
import com.liudaohong.foodparty.Bean.FoodVp1Bean;
import com.liudaohong.foodparty.Bean.FoodVp2Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by liuhong on 2017/3/18.
 */

public interface IFood {
    @GET("fb/v1/feed_categories?token=&user_key=&app_version=2.6")
    Call<FoodTabBean> callback();

    @GET("fb/v1/feeds/category_feed?category=1&per=10&token=&user_key=&app_version=2.6&")
    Call<FoodVp0Bean> callbackvp0(@Query("page") int page);

    @GET("fb/v1/feeds/category_feed?category=2&per=10&token=&user_key=&app_version=2.6&")
    Call<FoodVp1Bean> callbackvp1(@Query("page") int page);

    @GET("fb/v1/feeds/category_feed?category=3&per=10&token=&user_key=&app_version=2.6&")
    Call<FoodVp2Bean> callbackvp2(@Query("page") int page);
}
