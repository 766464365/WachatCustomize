package com.xuwei.wachatcustomize.main.post.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.util.SharepreferenceUtil;

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



    private String[] nameStr = {"Allen", "王不留行夕", "那一爪的风情", "hahahah", "Allen", "王不留行夕", "那一爪的风情", "hahahah",
            "Allen", "王不留行夕", "那一爪的风情", "hahahah"};

    private Context context;

    private OnItemClickListener mItemClickListener;
    private OnGotoClickListener onGotoClickListener;

    public PostAdapter(Context context) {
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_rv_item, parent, false);//
        MyViewHolder viewHolder = new MyViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(position==0){
            ViewGroup.LayoutParams layoutParams = holder.relaParent.getLayoutParams();
            layoutParams.height=400;
            holder.relaParent.setLayoutParams(layoutParams);
        }
        if (SharepreferenceUtil.readStrFromPre("postStr") != null && !SharepreferenceUtil.readStrFromPre("postStr").equals("")) {
            holder.tvContent.setText((String) SharepreferenceUtil.readStrFromPre("postStr"));
        }
        holder.tvName.setText(nameStr[position]);
        holder.bind();


    }

    @Override
    public int getItemCount() {
        return 10;          //暂时设定
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rela_parent)
        RelativeLayout relaParent;
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind() {


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