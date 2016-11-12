package com.example.materdesigndemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import com.example.materdesigndemo.R;

/**
 * Created by：赖上罗小贱 on 2016/11/12
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class PaletteFragment extends Fragment{
    private static final String ARG_POSITION = "position";
    private int position;
    private static final int[] drawables = {R.mipmap.one, R.mipmap.two, R.mipmap.four, R.mipmap
            .three, R.mipmap.five};
    public static PaletteFragment newInstance(int position){
        PaletteFragment fragment = new PaletteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        FrameLayout frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(params);
        frameLayout.setBackgroundResource(drawables[position]);
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8
        ,getResources().getDisplayMetrics());
        TextView textView = new TextView(getActivity());
        params.setMargins(margin,margin,margin,margin);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.BOTTOM);
        textView.setText("在路上的罗小贱" + (position + 1));
        frameLayout.addView(textView);
        return frameLayout;
    }
    //提供当前Fragment的主色调的Bitmap对象,供Palette解析颜色
    public static int getBackgroundBitmapPosition(int selectViewPagerItem){
        return drawables[selectViewPagerItem];
    }
}
