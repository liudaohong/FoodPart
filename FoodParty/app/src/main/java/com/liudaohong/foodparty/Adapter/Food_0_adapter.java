package com.liudaohong.foodparty.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liudaohong.foodparty.Bean.FoodVp0Bean;
import com.liudaohong.foodparty.Iterface.IFood;
import com.liudaohong.foodparty.Iterface.IFood_0_Listener;
import com.liudaohong.foodparty.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liuhong on 2017/3/18.
 */

public class Food_0_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;
    private List<FoodVp0Bean.FeedsBean> list;
    private IFood_0_Listener listener;


    public Food_0_adapter(Context context, List<FoodVp0Bean.FeedsBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }
    public void SetLister(IFood_0_Listener listener){
        this.listener=listener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if (viewType==0){
            view=inflater.inflate(R.layout.food_0_1item,parent,false);
           return  new VH(view);
        }else if (viewType==1){
            view=inflater.inflate(R.layout.food_0_item,parent,false);
        return new VH1(view);
        }else {
            view=inflater.inflate(R.layout.foodfooter,parent,false);
            return new VHfooter(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);


        switch (itemViewType){
            case 0:{
                FoodVp0Bean.FeedsBean feedsBean = list.get(position);
                VH  holder1 =   (VH) holder;
                Picasso.with(context).load(feedsBean.getCard_image()).into(holder1.iv);
            }
            break;
            case 1:{
                FoodVp0Bean.FeedsBean feedsBean = list.get(position);
                VH1 holder1 = (VH1) holder;
                holder1.name.setText(feedsBean.getPublisher());
                holder1.title.setText(feedsBean.getTitle());
                Picasso.with(context).load(feedsBean.getPublisher_avatar()).into(holder1.tot);
                Picasso.with(context).load(feedsBean.getCard_image()).into(holder1.iv);
                holder1.good.setText(feedsBean.getLike_ct()+"");
            }
            break;
            case 2:{

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
        if (position==0&&list.size()!=0){
            return 0;
        }else if (position==list.size()){
            return 2;
        }else {
            return 1;
        }
    }

    class  VH extends RecyclerView.ViewHolder{
        private ImageView iv;
        public VH(View itemView) {
            super(itemView);
            if (listener!=null){

          iv= (ImageView) itemView.findViewById(R.id.iv);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setOnClickListener(v,getLayoutPosition());
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return listener.setOnLongClickListener(v,getLayoutPosition());
                    }
                });
            }
        }
    }
    class VH1 extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView  name;
        private Button  good;
        private CircleImageView tot;
        private TextView title;
        public VH1(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.tv);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            name= (TextView) itemView.findViewById(R.id.name);
            good= (Button) itemView.findViewById(R.id.good);
            tot= (CircleImageView) itemView.findViewById(R.id.tot);
            if (listener!=null){

                iv= (ImageView) itemView.findViewById(R.id.iv);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setOnClickListener(v,getLayoutPosition());
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return listener.setOnLongClickListener(v,getLayoutPosition());
                    }
                });
            }
        }
    }

    class  VHfooter extends RecyclerView.ViewHolder{
        private ProgressBar pb;
        private TextView  tv;
        public VHfooter(View itemView) {
            super(itemView);
            pb= (ProgressBar) itemView.findViewById(R.id.pb);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }


    //

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        // if (isHeader(position) || isFooter(position)) {
        if ( isFooter(position)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }

    private boolean isFooter(int position) {
        if (position==list.size()){
            return true;
        }
        return false;
    }
}
