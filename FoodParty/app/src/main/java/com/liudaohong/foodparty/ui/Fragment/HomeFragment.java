package com.liudaohong.foodparty.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.liudaohong.foodparty.Adapter.Home_lv_adapter;
import com.liudaohong.foodparty.Bean.HomeBean;
import com.liudaohong.foodparty.Bean.HomeListBean;
import com.liudaohong.foodparty.BuildConfig;
import com.liudaohong.foodparty.Iterface.Ihome;
import com.liudaohong.foodparty.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by liuhong on 2017/3/17.
 */

public class HomeFragment extends Fragment {

    private ListView lv;
    private static String uri= "http://food.boohee.com/fb/v1/categories/list?token=&user_key=&app_version=2.6";
    private List<HomeListBean> list =new ArrayList<>();
    private Home_lv_adapter adapter;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.home_fragment_ayout, container, false);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = ((ListView) view.findViewById(R.id.lv));
        if (adapter==null){
            intiadapter();
            intiData();
        }else {

        }

    }

    private void intiadapter() {
        adapter = new Home_lv_adapter(list,getContext());
        lv.setAdapter(adapter);
    }

    private void intiData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://food.boohee.com/")
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Ihome ihome = retrofit.create(Ihome.class);
        Call<HomeBean> callback = ihome.callback();
        callback.enqueue(new Callback<HomeBean>() {

            private HomeListBean homeListBean;
            private String name;

            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                if (response.isSuccess()){
                    List<HomeBean.GroupBean> group = response.body().getGroup();
                    for (int i = 0; i < group.size(); i++) {
                        HomeBean.GroupBean groupBean = group.get(i);
                        if (i==0){
                            name = "食物分类";
                        }else if (i==1){
                            name="热门品牌";
                        }else {
                            name="连锁餐饮";
                        }
                            homeListBean=new HomeListBean(name,groupBean);
                            list.add(homeListBean);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });


    }
}
