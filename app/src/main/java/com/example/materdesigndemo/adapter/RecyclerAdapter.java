package com.example.materdesigndemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.materdesigndemo.R;

/**
 * Created by：赖上罗小贱 on 2016/11/11
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    public String[] datas = null;
    private OnItemClickListener mClickListener;

    public RecyclerAdapter(String[] datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
        return holder;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(datas[position]);
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.length;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder{
    private TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mClickListener = listener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View itemView, int pos);
    }
}
