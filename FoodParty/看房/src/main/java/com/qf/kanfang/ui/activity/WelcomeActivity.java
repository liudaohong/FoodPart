package com.qf.kanfang.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.qf.kanfang.R;
import com.qf.kanfang.utils.SharedUtils;

/**
 * Created by Administrator on 2017/1/16.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actyivity_welcome);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //延迟两秒后执行跳转
                //如果是第一次启动，跳转到引导界面
                //如果不是第一次启动，跳转到主界面
                if (SharedUtils.isFirstRun()) {
                    //是第一次启动
                    Intent intent = new Intent(WelcomeActivity.this, SlpashActivity.class);
                    startActivity(intent);
                } else {
                    //不是第一次
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                //结束自己
                finish();
            }
        }, 2000);
    }
}
