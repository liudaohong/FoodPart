package com.qf.kanfang.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qf.kanfang.R;
import com.qf.kanfang.utils.Constant;

/**
 * Created by Administrator on 2017/1/16.
 */
public class SlpashFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.slpash_fragment, container, false);
    }

    //四个界面，根据传入的 index ,决定加载对应的图片

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //得到传入的参数
        Bundle bundle = getArguments();
        int index = bundle.getInt(Constant.KEY_INDEX, 0);
        //根据参数值，设置对应的图片
        ImageView iv1 = (ImageView) view.findViewById(R.id.iv1);
        ImageView iv2 = (ImageView) view.findViewById(R.id.iv2);
        ImageView iv3 = (ImageView) view.findViewById(R.id.iv3);
        switch (index) {
            case 0: {
                iv1.setImageResource(R.drawable.guide_content_new_0);
                iv2.setImageResource(R.drawable.guide_content_0);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_0);
            }
            break;
            case 1: {
                iv1.setImageResource(R.drawable.guide_content_new_1);
                iv2.setImageResource(R.drawable.guide_content_1);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_1);
            }
            break;
            case 2: {
                iv1.setImageResource(R.drawable.guide_content_new_2);
                iv2.setImageResource(R.drawable.guide_content_2);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_2);
            }
            break;
            case 3: {
                iv1.setImageResource(R.drawable.guide_content_new_3);
                iv2.setImageResource(R.drawable.guide_content_3);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_3);
            }
            break;
        }
    }
}
