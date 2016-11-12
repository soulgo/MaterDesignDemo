package com.example.materdesigndemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.materdesigndemo.R;
import com.example.materdesigndemo.adapter.PullMoreRecyclerAdapter;
import com.example.materdesigndemo.bean.CardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by：赖上罗小贱 on 2016/11/11
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class CardViewActivity extends BaseActivity{
    private RecyclerView rv;
    private PullMoreRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    List<CardInfo> list = new ArrayList<CardInfo>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.add_bg_color, R
                .color.colorPrimary, R.color.colorPrimaryDark, R.color.add_selected_color);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        //创建并设置Adapter
        adapter = new PullMoreRecyclerAdapter(getDatas());
        rv.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        getDatas();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setEnabled(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!swipeRefreshLayout.isRefreshing()){
                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && lastVisibleItem + 1 == adapter.getItemCount()){
                        swipeRefreshLayout.setEnabled(false);
                        adapter.setMoreStatus(PullMoreRecyclerAdapter.LOADING_MORE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getDatas();
                                swipeRefreshLayout.setEnabled(true);
                                adapter.setMoreStatus(PullMoreRecyclerAdapter.PULLUP_LOAD_MORE);
                                adapter.notifyDataSetChanged();
                            }
                        }, 2000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private List<CardInfo> getDatas(){
        for (int i = 0;i < 10;i++){
            CardInfo ci = new CardInfo();
            ci.setContent("罗小贱说：走自己的路，让别人去说吧！努力是我的毕生追求！");
            ci.setTitle("在路上的罗小贱" + i);
            list.add(ci);
        }
        return list;
    }
}
