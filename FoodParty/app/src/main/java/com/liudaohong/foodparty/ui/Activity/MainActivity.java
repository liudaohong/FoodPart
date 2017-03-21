package com.liudaohong.foodparty.ui.Activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.liudaohong.foodparty.R;
import com.liudaohong.foodparty.ui.Fragment.HomeFragment;
import com.liudaohong.foodparty.ui.Fragment.MineFragment;
import com.liudaohong.foodparty.ui.Fragment.foodFragment;

public class MainActivity extends NoActionBarActivity {

    private FragmentTabHost tabHost;
    private String[] tabtitles={"食物百科","逛吃","我的"};
    int[] tabImgIds = {
            R.drawable.tab_0,
            R.drawable.tab_1,
            R.drawable.tab_2
    };
    //标签对应的Fragment的Class
    Class[] fragments = {
            HomeFragment.class,
            foodFragment.class,
            MineFragment.class
    };
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiview();
        initTabs();
    }

    private void intiview() {
        tabHost = ((FragmentTabHost) findViewById(R.id.tabHost));
        tabHost.setup(this,getSupportFragmentManager(),R.id.fragment_layout);

    }

    private void initTabs() {
        //添加四个标签
        for (int i = 0; i < tabtitles.length; i++) {
           //创建一个新的标签，指定Tag,这个tab就是Fragment的tag
            TabHost.TabSpec tab = tabHost.newTabSpec("tab"+i);
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
        tv.setText(tabtitles[i]);
        return view;

    }

}
