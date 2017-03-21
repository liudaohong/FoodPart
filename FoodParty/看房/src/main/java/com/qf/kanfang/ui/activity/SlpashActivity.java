package com.qf.kanfang.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.qf.kanfang.R;
import com.qf.kanfang.ui.fragment.SlpashFragment;
import com.qf.kanfang.utils.Constant;
import com.qf.kanfang.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */
public class SlpashActivity extends NoActionBarActivity {

    ViewPager viewpager;
    List<Fragment> fragments = new ArrayList<>();

    //惯性滑动
    boolean isLeftSlid = true;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //全屏 ，没有ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slpash_activity);
        //初始化控件
        viewpager = (ViewPager) findViewById(R.id.viewPager);
        //加载数据
        initDatas();
        //设置adapter
        initAdapter();
        //设置ViewPager的切换动画
        //参数一：boolean:true表示是顺序,false表示反序
        //参数二：动画实现类
        viewpager.setPageTransformer(true, new MyTrans());
        //设置ViewPager的滑动监听，最后一页且没有惯性滑动时，跳转到主页

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            /**滑动状态改变时
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                //如果状态变成停止状态时，最后一页，且没有惯性滑动，就跳转到主页
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING: {
                        //手指拖动状态
                    }
                    break;
                    case ViewPager.SCROLL_STATE_IDLE: {
                        //停止状态
                        //判断是否要跳转
                        //条件：最后一页，且没有惯性滑动
                        if (!isLeftSlid && (viewpager.getCurrentItem() == viewpager.getAdapter().getCount() - 1)) {
                            //跳转到主页
                            Intent intent = new Intent(SlpashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        //把该变量变成false
                        isLeftSlid = false;
                    }
                    break;
                    case ViewPager.SCROLL_STATE_SETTLING: {
                        //惯性滑动状态
                        isLeftSlid = true;
                    }
                    break;
                }
            }
        });

    }

    private void initAdapter() {
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    private void initDatas() {
        //加载四个Fragment
        for (int i = 0; i < 4; i++) {
            SlpashFragment sf = new SlpashFragment();
            //设置参数
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.KEY_INDEX, i);
            sf.setArguments(bundle);
            //添加到集合中
            fragments.add(sf);
        }
    }

    int[] ivIds = {
            R.id.iv1,
            R.id.iv2,
            R.id.iv3
    };

    // 内部类，实现ViewPager.PageTransformer
    class MyTrans implements ViewPager.PageTransformer {

        /**
         * 手指滑动时回调用方法
         *
         * @param page     当前被拖动的界面
         * @param position 表示当前移动的偏移量
         */
        @Override
        public void transformPage(View page, float position) {
            L.d("position=" + position);
            //依次找到三个子控件
            //计算三个子控件的偏移量
            //设置子控件的偏移量
            float dx = page.getWidth() * position;

            for (int i = 0; i < ivIds.length; i++) {
                View view = page.findViewById(ivIds[i]);
                //设置偏移量
                view.setTranslationX(dx);
                //设置下一个子控件的偏移量
                dx *= 10;
            }
        }
    }


}
