package com.liudaohong.foodparty.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liudaohong.foodparty.Bean.FoodVp2Bean;
import com.liudaohong.foodparty.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by liuhong on 2017/3/21.
 */

public class Food_2_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<FoodVp2Bean.FeedsBean> list;

    public Food_2_adapter(Context context, List<FoodVp2Bean.FeedsBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder VH = null;
        switch (viewType){
            case 0:{
                view=inflater.inflate(R.layout.food_2_item,parent,false);
                VH=new VH1(view);

            }break;
            case 1:{
                view=inflater.inflate(R.layout.food_2_2item,parent,false);
                VH=new VH2(view);
            }break;
            case 2:{
                view=inflater.inflate(R.layout.foodfooter,parent,false);
                VH=new VH3(view);
            }
            break;
        }

        return VH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type=getItemViewType(position);
        switch (type){
            case 0:{
                VH1 holder1 = (VH1) holder;
                FoodVp2Bean.FeedsBean feedsBean = list.get(position);
                holder1.title.setText(feedsBean.getTitle());
                holder1.name.setText(feedsBean.getSource());
                holder1.tail.setText(feedsBean.getTail());
                Picasso.with(context).load(feedsBean.getImages().get(0)).into(holder1.iv);
            }
            break;
            case 1:{
                VH2 holder1 = (VH2) holder;
                FoodVp2Bean.FeedsBean feedsBean = list.get(position);
                holder1.title.setText(feedsBean.getTitle());
                holder1.name.setText(feedsBean.getSource());
                holder1.tail.setText(feedsBean.getTail());
                Picasso.with(context).load(feedsBean.getImages().get(0)).into(holder1.iv0);
                Picasso.with(context).load(feedsBean.getImages().get(1)).into(holder1.iv1);
                Picasso.with(context).load(feedsBean.getImages().get(2)).into(holder1.iv2);
            }
            break;
            case 2:{
                VH3 holder1 = (VH3) holder;
            }break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }


    @Override
    public int getItemViewType(int position) {
       /* int size = list.get(position).getImages().size();
        if (size ==1){
            return 0;
        }else if (size==3){
            return 1;
        }else if (position==list.size()){
            return 2;
        }*/
        if (position==list.size()){
            return 2;
        }
        int size = list.get(position).getImages().size();
        if (size ==1){
            return 0;
        }else if (size==3){
            return 1;
        }
        return super.getItemViewType(position);
    }

    private class VH1 extends RecyclerView.ViewHolder{
        private TextView title,name,tail;
        private ImageView iv;
        public VH1(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            name= (TextView) itemView.findViewById(R.id.name);
            tail= (TextView) itemView.findViewById(R.id.tail);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }


    private class VH2 extends RecyclerView.ViewHolder{
            private ImageView iv0,iv1,iv2;
        private TextView title,name,tail;
        public VH2(View itemView) {
            super(itemView);
            iv0= (ImageView) itemView.findViewById(R.id.iv0);
            iv1= (ImageView) itemView.findViewById(R.id.iv1);
            iv2= (ImageView) itemView.findViewById(R.id.iv2);
            title= (TextView) itemView.findViewById(R.id.title);
            name= (TextView) itemView.findViewById(R.id.name);
            tail= (TextView) itemView.findViewById(R.id.tail);
        }
    }


    private  class VH3 extends RecyclerView.ViewHolder{

        private ProgressBar pb;
        private TextView tv;
        public VH3(View itemView) {
            super(itemView);
            pb= (ProgressBar) itemView.findViewById(R.id.pb);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }



}
