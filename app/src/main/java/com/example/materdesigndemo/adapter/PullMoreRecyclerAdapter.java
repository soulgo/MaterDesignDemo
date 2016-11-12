package com.example.materdesigndemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.materdesigndemo.R;
import com.example.materdesigndemo.bean.CardInfo;

import java.util.List;

/**
 * 下拉刷新，下拉加载更多
 * Created by：赖上罗小贱 on 2016/11/11
 * 邮箱：ljq@luojunquan.top
 * 个人博客：www.luojunquan.top
 * CSDN:http://blog.csdn.net/u012990171
 */


public class PullMoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_NORMAL_ITEM = 0;  //普通Item
    private static final int TYPE_FOOTER_ITEM = 1;  //底部FooterView
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 1;
    //正在加载中
    public static final int LOADING_MORE = 2;
    //默认为0
    private int load_more_status = 1;
    public List<CardInfo> list;
    private OnItemClickListener mClickListener;
    public PullMoreRecyclerAdapter(List<CardInfo> list) {
        this.list = list;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//如果viewType是普通item返回普通的布局，否则是底部布局并返回
        if (viewType == TYPE_NORMAL_ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_recyclerview_item,parent,false);
            final NormalItmeViewHolder holder = new NormalItmeViewHolder(view);
            if (mClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                    }
                });
            }
            return holder;
        }else {
            //底部
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recyclerview_footer_view,parent,false);
            FooterViewHolder holder = new FooterViewHolder(view);
            return holder;
        }
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof NormalItmeViewHolder) {
            ((NormalItmeViewHolder) viewHolder).titleTv.setText(list.get(position).getTitle());
            ((NormalItmeViewHolder) viewHolder).contentTv.setText(list.get(position).getContent());
        } else if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder footViewHolder = (FooterViewHolder) viewHolder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setVisibility(View.VISIBLE);
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多");
                    footViewHolder.pb.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setVisibility(View.GONE);
                    footViewHolder.pb.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        //+1是加入底部的加载布局项
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 如果position+1等于整个布局所有数总和就是底部布局
        if (position + 1 == getItemCount()){
            return TYPE_FOOTER_ITEM;
        }else {
            return TYPE_NORMAL_ITEM;
        }
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class NormalItmeViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv,contentTv;
        public ImageView iv;
        public NormalItmeViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_content_tv);
            iv = (ImageView) itemView.findViewById(R.id.item_iv);
        }
    }
    //底部FooterView布局
    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        public TextView foot_view_item_tv;
        public ProgressBar pb;
        public FooterViewHolder(View view) {
            super(view);
            pb = (ProgressBar) view.findViewById(R.id.progress_view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.tv_content);
        }
    }
    //加载状态
    public void setMoreStatus(int status){
        load_more_status = status;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mClickListener = listener;
    }
//监听器接口
    public interface OnItemClickListener{
        public void onItemClick(View itemView,int pos);
    }
}
