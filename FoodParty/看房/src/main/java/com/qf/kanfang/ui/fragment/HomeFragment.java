package com.qf.kanfang.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qf.kanfang.R;
import com.qf.kanfang.ui.activity.CityChoseActivity;
import com.qf.kanfang.utils.Constant;

/**
 * Created by Administrator on 2017/1/16.
 */
public class HomeFragment extends Fragment {

    //控件声明
    Button btn_city;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        // 设置监听
        setListener();
    }

    private void setListener() {
        btn_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //有返回的跳转到城市选择界面
                Intent intent = new Intent(getActivity(), CityChoseActivity.class);
                startActivityForResult(intent, Constant.INTENT_BACK_CITYCHOSE);
            }
        });
    }

    private void initViews(View view) {
        btn_city = (Button) view.findViewById(R.id.btn_city);
    }


    //接收 Intent返回

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //接收Intent返回
        switch (requestCode) {
            case Constant.INTENT_BACK_CITYCHOSE: {
                //如果data不为null，且有城市数据，就显示
                if (data != null) {
                    //取出城市名称
                    String cityName = data.getStringExtra(Constant.INTENT_KEY_CITYNAME);
                    if (cityName != null) {
                        btn_city.setText(cityName);
                    }
                }
            }
            break;
        }
    }
}
