package com.qf.kanfang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.kanfang.R;
import com.qf.kanfang.bean.CityBean;
import com.qf.kanfang.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 头部悬停adapter
 * 1.写一个class，继承自BaseAdapter，并实现StickyListHeadersAdapter接口
 * 2.重写BaseAdapter中的四个方法
 * 3.重写StickListHeadersAdapter中的两个方法
 * getHeaderView   返回头部悬停 视图，ViewHoder视图复用
 * getHeaderId      返回头部分类id
 */
public class CityChoseAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    //数据源
    List<CityBean> cities;
    /**
     * 装所有数据
     */
    List<CityBean> allData = new ArrayList<>();
    //LayoutInflater
    LayoutInflater inflater;
    //Context
    Context mContext;

    public CityChoseAdapter(Context mContext, List<CityBean> cities) {
        this.mContext = mContext;
        this.cities = cities;
        inflater = LayoutInflater.from(mContext);
        //保存一份数据到allData
        allData.addAll(cities);
    }

    /**
     * 添加所有数据
     *
     * @param list
     */
    public void setAllData(List<CityBean> list) {
        allData.clear();
        allData.addAll(list);
    }


    class HeaderViewHolder {
        TextView tv;

        public HeaderViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.tv_head);
        }
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_head_layout, parent, false);
            viewHolder = new HeaderViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HeaderViewHolder) convertView.getTag();
        }
        //设置数据
        viewHolder.tv.setText(cities.get(position).getFirstLetter());

        return convertView;
    }

    /**
     * 根据position，取出数据源中的分类id
     *
     * @param position
     * @return
     */
    @Override
    public long getHeaderId(int position) {
        return cities.get(position).getLetterId();
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_name_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(cities.get(position).getCityname());
        return convertView;
    }

    class ViewHolder {
        TextView tv;

        public ViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.tv_city_name);
        }

    }

    /**
     * 按城市名进行搜索
     *
     * @param str
     */
    //实现搜索功能
    public void search(String str) {
        L.d("搜索：" + str);
        //先清掉显示集合中的数据
        cities.clear();
        //从所有数据集合中，按条件检索出对应的数据，并添加到显示集合中
        //如果str为null，获长度为0 ,表示要显示所有数据
        if (str == null || str.length() == 0) {
            //添加所有数据
            cities.addAll(allData);
            //界面更新
            notifyDataSetChanged();
            return;
        }
        for (CityBean bean : allData) {
            //如果str中，含有中文，就按中文名称进行搜索
            if (isChinese(str)) {
                if (bean.getCityname().contains(str)) {
                    //表示符合条件
                    cities.add(bean);
                }
            } else {
                //按拼音检索
                if (bean.getCitypinyin().contains(str)) {
                    //表示符合条件
                    cities.add(bean);
                }
            }
        }
        //更新界面
        notifyDataSetChanged();
    }


    // 判断字符串是否包含有中文
    public static boolean isChinese(String str) {
        String regex = "[\\u4e00-\\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }


}
