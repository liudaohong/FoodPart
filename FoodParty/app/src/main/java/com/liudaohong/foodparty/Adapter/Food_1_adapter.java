package com.liudaohong.foodparty.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liudaohong.foodparty.Bean.FoodVp1Bean;
import com.liudaohong.foodparty.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by liuhong on 2017/3/20.
 */

public class Food_1_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FoodVp1Bean.FeedsBean> list;
    private Context context;
    private LayoutInflater inflater;

    public Food_1_adapter(List<FoodVp1Bean.FeedsBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType){
            case 0:{
                view=inflater.inflate(R.layout.food_1_item,parent,false);
                return new VH1(view);

            }
            case 1:{
                view=inflater.inflate(R.layout.foodfooter,parent,false);
                return new VH2(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type=getItemViewType(position);
        switch (type){
            case 0:{
                FoodVp1Bean.FeedsBean feedsBean = list.get(position);
                VH1 holder1 = (VH1) holder;
                holder1.title.setText(feedsBean.getTitle());
                holder1.source.setText(feedsBean.getSource());
                holder1.tail.setText(feedsBean.getTail());
                Picasso.with(context).load(feedsBean.getBackground()).into(holder1.iv);

            }
            break;
            case 1:{

            }
            break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==list.size()){
            return 1;
        }else {
            return 0;
        }

    }

    private class VH1 extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView source,title,tail;
        public VH1(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            source= (TextView) itemView.findViewById(R.id.source);
            title= (TextView) itemView.findViewById(R.id.title);
            tail= (TextView) itemView.findViewById(R.id.tail);
        }
    }
    private  class VH2 extends RecyclerView.ViewHolder{

        private ProgressBar pb;
        private TextView tv;
        public VH2(View itemView) {
            super(itemView);
            pb= (ProgressBar) itemView.findViewById(R.id.pb);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }




}
