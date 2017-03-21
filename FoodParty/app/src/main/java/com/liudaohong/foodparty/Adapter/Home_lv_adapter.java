package com.liudaohong.foodparty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.liudaohong.foodparty.Bean.HomeBean;
import com.liudaohong.foodparty.Bean.HomeListBean;
import com.liudaohong.foodparty.MyGridView;
import com.liudaohong.foodparty.R;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

/**
 * Created by liuhong on 2017/3/17.
 */

public class Home_lv_adapter extends BaseAdapter {
    private List<HomeListBean> list;
    private Context context;
    private LayoutInflater inflater;



    public Home_lv_adapter(List<HomeListBean> list, Context context) {
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
        ViewHolderr vh=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.home_item_layout,parent,false);
            vh=new ViewHolderr(convertView);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolderr) convertView.getTag();
        }

       vh. gridlist.clear();
        //添加数据
        List<HomeBean.GroupBean.CategoriesBean> categories = list.get(position).getGroupBean().getCategories();
        vh. gridlist.addAll(categories);
        //添加适配器
        vh.tv.setText(list.get(position).getName());
        vh.adapter.notifyDataSetChanged();
        return convertView;
    }


    class ViewHolderr{
        private TextView tv;
        private MyGridView gridView;
        private List<HomeBean.GroupBean.CategoriesBean> gridlist;
        private Home_grid_adapter adapter;
        public ViewHolderr(View view) {
            tv= (TextView) view.findViewById(R.id.textname);
            gridView= ((MyGridView) view.findViewById(R.id.grid));
            if (gridlist==null){
                gridlist = new ArrayList<>();
            }
            if (adapter==null){
                adapter = new Home_grid_adapter(gridlist,context);
            }
            gridView.setAdapter(adapter);
        }
    }
}
