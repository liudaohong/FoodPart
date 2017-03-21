package com.liudaohong.foodparty.ui.Fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liudaohong.foodparty.Adapter.Food_0_adapter;
import com.liudaohong.foodparty.Bean.FoodVp0Bean;
import com.liudaohong.foodparty.BuildConfig;
import com.liudaohong.foodparty.Iterface.IFood;
import com.liudaohong.foodparty.Iterface.IFood_0_Listener;
import com.liudaohong.foodparty.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.max;

/**
 * Created by liuhong on 2017/3/18.
 */

public class Food_0_fragment extends Fragment {

    private View view;
    private RecyclerView recycler;
    private int page=1;
    List<FoodVp0Bean.FeedsBean> list=new ArrayList<>();
    private Food_0_adapter adapter;
    private int total_pages;

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
            intilisterner();
            initListener();
        }
    }

    private void initListener() {
        adapter.SetLister(new IFood_0_Listener() {
            @Override
            public void setOnClickListener(View item, int Positon) {
                if(Positon==0){

                }else {

                }
            }

            @Override
            public boolean setOnLongClickListener(View view, int postion) {
                return false;
            }
        });
    }

    private void intilisterner() {
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener(){
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                    int lastVisiblePos = getMaxElem(lastVisiblePositions);
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                        //加载更多功能的代码
                       /* Ln.e("howes right="+manager.findLastCompletelyVisibleItemPosition());
                        Toast.makeText(getActivityContext(),"加载更多",0).show();*/
                       // Toast.makeText(getContext(), "可以加载更多了", Toast.LENGTH_SHORT).show();
                        if (list.size()>0&&total_pages!=0&&page<=total_pages){
                            intidata();
                        }
                    }
                }
            }

            private int getMaxElem(int[] lastVisiblePositions) {
                int max=0;
                for (int i = 0; i < lastVisiblePositions.length; i++) {
                    max=max>lastVisiblePositions[i]?max:lastVisiblePositions[i];
                }
                    return max;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
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

    private void intidata() {
        //增加返回值为Gson的支持(以实体类返回)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://food.boohee.com/")
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IFood iFood = retrofit.create(IFood.class);
        Call<FoodVp0Bean> foodVp0BeanCall = iFood.callbackvp0(page++);
        foodVp0BeanCall.enqueue(new Callback<FoodVp0Bean>() {



            @Override
            public void onResponse(Call<FoodVp0Bean> call, Response<FoodVp0Bean> response) {
              Log.d("Food_0_fragment", "下载成功........");
                if (response.isSuccess()){
                    FoodVp0Bean body = response.body();
                    total_pages = body.getTotal_pages();


                    List<FoodVp0Bean.FeedsBean> feeds = body.getFeeds();
                    list.addAll(feeds);
                    adapter.notifyItemInserted(list.size()-feeds.size());
                }
            }

            @Override
            public void onFailure(Call<FoodVp0Bean> call, Throwable t) {

            }
        });
    }

    private void intiadapter() {
        adapter = new Food_0_adapter(getContext(),list);
        recycler.setAdapter(adapter);


    }

    private void intiview(View view) {

        recycler = ((RecyclerView) view.findViewById(R.id.recycler));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(staggeredGridLayoutManager);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recycler.addItemDecoration(decoration);

    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }


}
