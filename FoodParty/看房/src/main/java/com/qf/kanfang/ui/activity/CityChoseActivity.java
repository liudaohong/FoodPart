package com.qf.kanfang.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.qf.kanfang.R;
import com.qf.kanfang.adapter.CityChoseAdapter;
import com.qf.kanfang.bean.CityBean;
import com.qf.kanfang.inter.ICityChose;
import com.qf.kanfang.utils.APIManager;
import com.qf.kanfang.utils.Constant;
import com.qf.kanfang.utils.FileUtils;
import com.qf.kanfang.utils.HttpUtils;
import com.qf.kanfang.utils.L;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Administrator on 2017/1/17.
 */
public class CityChoseActivity extends NoActionBarActivity {
    //声明控件
    StickyListHeadersListView stickyList;
    EditText et_search;
    //数据源
    List<CityBean> data = new ArrayList<>();
    //Adapter
    CityChoseAdapter adapter;


    Handler handler = new Handler();

    /**
     * 用于搜索的字符串
     */
    String searchStr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_chose);
        //找到控件
        initViews();
        //初始化adapter
        adapter = new CityChoseAdapter(this, data);
        //设置adapter
        stickyList.setAdapter(adapter);
        //设置点击跳转
        setItemClickListener();
        //设置搜索监听
        setSearchListener();
        //联网获取数据
        getData();
    }

    private void setSearchListener() {
        //输入框 文本变化监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                searchStr = s.toString();
                //文本发生改变后，进行搜索
                //取消前面的搜索条件
                handler.removeCallbacks(searchRun);
                //发一个新的搜索条件
                handler.postDelayed(searchRun, 200);
            }
        });
    }

    Runnable searchRun = new Runnable() {
        @Override
        public void run() {
            //进行搜索
            adapter.search(searchStr);
        }
    };

    private void setItemClickListener() {
        stickyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //得到数据源
                CityBean bean = data.get(position);
                //设置Intent
                Intent intent = getIntent();
                intent.putExtra(Constant.INTENT_KEY_CITYNAME, bean.getCityname());
                intent.putExtra(Constant.INTENT_KEY_CITYID, bean.getCityid());
                //setResult
                setResult(0, intent);
                //finish
                finish();
            }
        });
    }

    private void initViews() {
        stickyList = (StickyListHeadersListView) findViewById(R.id.stickyList);
        et_search = (EditText) findViewById(R.id.et_search);
    }

    private void getData() {
        //从缓存中，取数据，如果为null,从网络上取
        String value = FileUtils.readString(Constant.CITY_FILE);
        if (value != null) {
            //解析数据
            try {
                List<CityBean> beans = HttpUtils.getCities(value);
                L.d("从缓存中取：" + beans.size());
                //添加到总集合中
                data.clear();
                data.addAll(beans);
                //更新界面
                adapter.setAllData(beans);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return;
        }


        //retrofit请求获取数据
        //1.通过构建者模式，得到Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //添加主机地址
                .baseUrl(APIManager.BASE_URL)
                //添加转换工厂
                .addConverterFactory(ScalarsConverterFactory.create())
                //构建
                .build();
        //2. 通过Retrofit对象，得到接口实例对象
        ICityChose cityChose = retrofit.create(ICityChose.class);
        // 3.通过接口实例对象，得到数据
        Call<String> call = cityChose.getData();
        //4.发请异步请求,
        //参数：CallBack，请求回调
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //服务器有响应返回：服务器返回错误，正常返回
                //参数一:call为当前请求 对象
                //参数二：服务器的响应体：泛型为接口中指定的类型，包含了需要的数据
                L.d("城市选择数据： " + response.body());
                //缓存到内部存储中
                FileUtils.saveString(response.body(), Constant.CITY_FILE);
                try {
                    List<CityBean> data = HttpUtils.getCities(response.body());
                    L.d("解析的城市数据：" + data.size());
                    CityChoseActivity.this.data.clear();
                    CityChoseActivity.this.data.addAll(data);
                    //更新界面
                    adapter.setAllData(data);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //服务器没返回：网络异常
            }
        });
    }
}
