package com.liudaohong.foodparty.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liudaohong.foodparty.Bean.FoodTabBean;
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
 * Created by liuhong on 2017/3/17.
 */

public class foodFragment extends Fragment {

    private View rootView;
    private TabLayout tab;
    private ViewPager vp;
    private List<Fragment> listFragment;
    private Retrofit retrofit;
    List<FoodTabBean.CategoriesBean> listTab=new ArrayList<>();
    private FragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.food_fragment_layout, container, false);
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
        if (adapter==null){
        intiview(view);
        initfragments();
        intiadapter();
        intitabtitle();
        }
    }

    private void initfragments() {
        listFragment=new ArrayList<>();
        listFragment.add(new Food_0_fragment());
        listFragment.add(new Food_1_Fragment());
        listFragment.add(new Food_2_Fragment());
        listFragment.add(new Food_3_Fragment());
    }

    private void intiadapter() {
        adapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (listTab.size()==0){
                    return "";
                }else {

                return listTab.get(position).getName();
                }
            }
        };
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    private void intitabtitle() {
        //增加返回值为Gson的支持(以实体类返回)
        retrofit = new Retrofit.Builder()
                .baseUrl("http://food.boohee.com/")
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IFood iFood = retrofit.create(IFood.class);
        Call<FoodTabBean> callback = iFood.callback();
        callback.enqueue(new Callback<FoodTabBean>() {
            @Override
            public void onResponse(Call<FoodTabBean> call, Response<FoodTabBean> response) {
                if (response.isSuccess()){
                    List<FoodTabBean.CategoriesBean> categories = response.body().getCategories();
                    listTab.addAll(categories);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<FoodTabBean> call, Throwable t) {

            }
        });

    }

    private void intiview(View view) {
        tab = ((TabLayout) view.findViewById(R.id.tablayout));
        vp = ((ViewPager) view.findViewById(R.id.vp));

    }
}
