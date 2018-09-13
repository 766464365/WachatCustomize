package com.xuwei.wachatcustomize.main.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.fragments.data.DataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * While writing this code, only God and I know what it is.
 * And now only God knows it
 * <p>
 * Created by xuweijie
 * on 2018/7/26 0026.
 */
public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.MyViewHolder> implements View.OnClickListener {

    private List<DataBean> dataBeans;

    private Context context;

    private OnItemClickListener mItemClickListener;
    private OnGotoClickListener onGotoClickListener;

    public WechatAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<DataBean> dataBean) {
        this.dataBeans = dataBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_wechat_list, parent, false);//
        MyViewHolder viewHolder = new MyViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 5;          //暂时设定
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_lastTalk)
        TextView tvLastTalk;
        @BindView(R.id.tv_time)
        TextView tvTime;

        DataBean itemDataBean;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind() {
            //tvOrderId.setText("试一下");


        }


    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnGotoClickListener {
        void onGotoClick(View v, int position);
    }


    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void setmItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setmGotoClikcListener(OnGotoClickListener monGotoClickListener) {
        onGotoClickListener = monGotoClickListener;
    }
}