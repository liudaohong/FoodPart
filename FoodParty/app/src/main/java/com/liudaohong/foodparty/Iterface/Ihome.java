package com.liudaohong.foodparty.Iterface;

import com.liudaohong.foodparty.Bean.HomeBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by liuhong on 2017/3/17.
 */

public interface Ihome {
    @GET("fb/v1/categories/list?token=&user_key=&app_version=2.6")
    Call<HomeBean> callback();
}
