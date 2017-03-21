package com.liudaohong.foodparty.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liudaohong.foodparty.Adapter.Food_1_adapter;
import com.liudaohong.foodparty.Bean.FoodVp1Bean;
import com.liudaohong.foodparty.Iterface.IFood;
import com.liudaohong.foodparty.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by liuhong on 2017/3/18.
 */

public class Food_1_Fragment extends Fragment {
    private View view;
    private RecyclerView recycler;
    private int page=1;
    List<FoodVp1Bean.FeedsBean> list=new ArrayList<>();
    private Food_1_adapter adapter;
    private int total_pages=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view = inflater.inflate(R.layout.food_1_layout,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent!=null){
            parent.removeView(view);
        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (adapter==null){
        intiview(view);
        intiadapter();
        intidata();
            intilistener();
        }
    }

    private void intilistener() {
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public boolean isSlidingToLast=false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (isSlidingToLast&&total_pages!=0&&page<total_pages&&lastVisibleItemPosition==list.size()){
                    intidata();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                }else{
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }

            }
        });
    }

    private void intiadapter() {
        adapter = new Food_1_adapter(list,getContext());
        recycler.setAdapter(adapter);

    }

    private void intidata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://food.boohee.com/")
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IFood iFood = retrofit.create(IFood.class);

        Call<FoodVp1Bean> foodVp1BeanCall = iFood.callbackvp1(page++);
        foodVp1BeanCall.enqueue(new Callback<FoodVp1Bean>() {
            @Override
            public void onResponse(Call<FoodVp1Bean> call, Response<FoodVp1Bean> response) {
                if (response.isSuccess()){

                    FoodVp1Bean body = response.body();
                    total_pages = body.getTotal_pages();
                    List<FoodVp1Bean.FeedsBean> feeds = body.getFeeds();
                    list.addAll(feeds);
                    adapter.notifyItemInserted(list.size()-feeds.size());

                }
            }

            @Override
            public void onFailure(Call<FoodVp1Bean> call, Throwable t) {

            }
        });

    }

    private void intiview(View view) {
       recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));


    }






}
