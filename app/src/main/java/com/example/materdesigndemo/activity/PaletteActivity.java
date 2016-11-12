package com.example.materdesigndemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.example.materdesigndemo.R;
import com.example.materdesigndemo.adapter.PaletteViewPagerAdapter;
import com.example.materdesigndemo.fragment.PaletteFragment;

import static android.os.Build.VERSION.SDK;

/**
 * Created by：赖上罗小贱 on 2016/11/12
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class PaletteActivity extends BaseActivity{
    private Toolbar toolbar;
    private TabLayout toolbar_tab;
    private ViewPager main_vp_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        toolbar = (Toolbar) findViewById(R.id.toolbar);//注意用V7下的Toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_tab = (TabLayout) findViewById(R.id.toolbar_tab);
        main_vp_container = (ViewPager) findViewById(R.id.main_vp_container);
        PaletteViewPagerAdapter pvAdapter = new PaletteViewPagerAdapter(
                getSupportFragmentManager(),this);
        main_vp_container.setAdapter(pvAdapter);
        toolbar_tab.setupWithViewPager(main_vp_container);
        changeTopBgColor(0);
        main_vp_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTopBgColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
//根据Palette提取的颜色，修改tab和toolbar以及状态栏的颜色
    private void changeTopBgColor(int position) {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                PaletteFragment.getBackgroundBitmapPosition(position));
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，
                // 标题等，使整个UI界面颜色统一
                toolbar_tab.setBackgroundColor(vibrant.getRgb());
                toolbar_tab.setSelectedTabIndicatorColor(colorBurn(vibrant.getRgb()));
                toolbar.setBackgroundColor(vibrant.getRgb());

                if (Build.VERSION.SDK_INT >= 21){
                    Window window = getWindow();
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }
    public int colorBurn(int RGBValues){
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red,green,blue);
    }
}
