package com.example.materdesigndemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.materdesigndemo.fragment.PaletteFragment;

/**
 * Created by：赖上罗小贱 on 2016/11/12
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class PaletteViewPagerAdapter extends FragmentPagerAdapter{
    private String tabTitles[] = new String[]{"主页", "分享", "收藏", "关注", "微博"};
    private Context context;

    public PaletteViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PaletteFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
//传递tab名字
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
