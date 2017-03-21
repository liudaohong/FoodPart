package com.qf.kanfang.inter;

import com.qf.kanfang.utils.APIManager;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 城市选择网络请求接口
 * 写法：
 * 定义一个方法,返回 Call<T>
 * 如果是GET请求，再加上@GET注解，跟上请求路径(不包括主机名)
 */
public interface ICityChose {

    @GET(APIManager.URL_CITY_CHOSE)
    Call<String> getData();
}
