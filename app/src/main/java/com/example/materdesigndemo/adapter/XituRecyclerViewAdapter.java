package com.example.materdesigndemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by：赖上罗小贱 on 2016/11/12
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class XituRecyclerViewAdapter extends RecyclerView.Adapter<XituRecyclerViewAdapter.ViewHolder>{
    // 数据集
    private List<String> mDataset;

    public XituRecyclerViewAdapter(List<String> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(parent.getContext(),android.R.layout.simple_list_item_1,null);
        // 创建一个ViewHolder
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据到ViewHolder上
            holder.mTextView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
