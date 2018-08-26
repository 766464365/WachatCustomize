package com.xuwei.wachatcustomize.main.post.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.b2bwings.R;
import com.b2bwings.receiveorder.data.bean.WaitOrderDataBase;

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
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> implements View.OnClickListener {

    private List<WaitOrderDataBase.DataBean> dataBeans;

    private Context context;

    private OnItemClickListener mItemClickListener;
    private OnGotoClickListener onGotoClickListener;

    public PostAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<WaitOrderDataBase.DataBean> dataBean) {
        this.dataBeans=dataBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_rv_item.xml, parent, false);//
        MyViewHolder viewHolder = new MyViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemDataBean=dataBeans.get(position);

        holder.tvId.setText("运单号:" + dataBeans.get(position).getWaybillId());
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();          //暂时设定
    }




    public class MyViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.tv_status_left)
        TextView tvStatusLeft;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_status_right)
        TextView tvStatusRight;
        @BindView(R.id.rela_goto)
        RelativeLayout relaGoto;

        WaitOrderDataBase.DataBean itemDataBean;

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

    public interface OnGotoClickListener{
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

    public  void setmGotoClikcListener(OnGotoClickListener monGotoClickListener){
        onGotoClickListener=monGotoClickListener;
    }
}