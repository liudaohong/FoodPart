package com.liudaohong.foodparty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liudaohong.foodparty.Bean.HomeBean;
import com.liudaohong.foodparty.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by liuhong on 2017/3/17.
 */

public class Home_grid_adapter extends BaseAdapter {
    private List<HomeBean.GroupBean.CategoriesBean> list;
    private Context context;
    private LayoutInflater inflater;

    public Home_grid_adapter(List<HomeBean.GroupBean.CategoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.home_grid_item,parent,false);
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(list.get(position).getName());
        Picasso.with(context).load(list.get(position).getImage_url()).into(vh.iv);
        return convertView;
    }


    class ViewHolder{
        private ImageView iv;
        private TextView tv;
        public ViewHolder(View view) {
            iv= (ImageView) view.findViewById(R.id.iv);
            tv= (TextView) view.findViewById(R.id.name);

        }
    }
}
