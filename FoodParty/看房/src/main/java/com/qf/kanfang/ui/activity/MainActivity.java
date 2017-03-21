package com.qf.kanfang.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.qf.kanfang.R;
import com.qf.kanfang.ui.fragment.DiscoverFragment;
import com.qf.kanfang.ui.fragment.HomeFragment;
import com.qf.kanfang.ui.fragment.MessageFragment;
import com.qf.kanfang.ui.fragment.MineFragment;
import com.qf.kanfang.utils.SharedUtils;

public class MainActivity extends NoActionBarActivity {

    //控件声明
    FragmentTabHost tabHost;
    //数据声明
    //底部标签文字数组
    String[] tabText = {
            "首页",
            "发现",
            "消息",
            "我的"
    };
    //底部图片选择器的id数组
    int[] tabImgIds = {
            R.drawable.tab_home_selector,
            R.drawable.tab_discove_selector,
            R.drawable.tab_message_selector,
            R.drawable.tab_mine_selector
    };

    //标签对应的Fragment的Class
    Class[] fragments = {
            HomeFragment.class,
            DiscoverFragment.class,
            MessageFragment.class,
            MineFragment.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置非第一次启动
        SharedUtils.saveFirstRun();
        //找到控件
        initViews();
        //添加标签
        initTabs();


    }

    private void initTabs() {
        //添加四个标签
        for (int i = 0; i < tabText.length; i++) {
            //创建一个新的标签，指定Tag,这个tab就是Fragment的tag
            TabHost.TabSpec tab = tabHost.newTabSpec("" + i);
            //设置标签的视图
            tab.setIndicator(getTabView(i));
            //添加到TabHost中
            //参数一：标签TabSpec
            //参数二：tab标签对应的Fragment的Class
            //参数三：Bundle    可传参数到指定的Fragment中
            tabHost.addTab(tab, fragments[i], null);
            //把tab左右的分隔线设置为透明图片
            tabHost.getTabWidget().setDividerDrawable(new ColorDrawable(0x00000000));
        }

    }

    LayoutInflater inflater;

    private View getTabView(int i) {
        //动态得到View-->tab_item的布局
        if (inflater == null) {
            inflater = LayoutInflater.from(this);
        }

        View view = inflater.inflate(R.layout.tab_item_layout, null);
        //设置view中的控件（ImageView,Text)相关属性
        ImageView iv = (ImageView) view.findViewById(R.id.tab_iv);
        TextView tv = (TextView) view.findViewById(R.id.tab_tv);
        iv.setImageResource(tabImgIds[i]);
        tv.setText(tabText[i]);
        return view;

    }

    private void initViews() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        //初始化
        //参数一:Context
        //参数二：FragmentManager,v4包
        //参数三：指定动态加载Fragment的布局id
        tabHost.setup(this, getSupportFragmentManager(), R.id.fragment_layout);
    }
}
