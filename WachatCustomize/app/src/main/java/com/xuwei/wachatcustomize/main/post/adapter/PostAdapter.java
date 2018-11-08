package com.xuwei.wachatcustomize.main.post.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.util.SharepreferenceUtil;

import java.io.ByteArrayInputStream;

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
    private ImageView ivcontent;


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
        ivcontent=holder.ivContent;
        holder.itemView.setTag(position);
        ViewGroup.LayoutParams layoutParams = holder.relaParent.getLayoutParams();
        ViewGroup.LayoutParams layoutParamsContent = holder.ivContent.getLayoutParams();
        if (position == 0) {
            switch (SharepreferenceUtil.readStrFromPre("model")) {
                case "0":       //文字模式
                    holder.tvContent.setText((String) SharepreferenceUtil.readStrFromPre("postStr"));
                    break;
                case "1":       //图片模式
                    holder.tvContent.setText((String) SharepreferenceUtil.readStrFromPre("postStr"));
                    layoutParams.height = 800;
                    holder.relaParent.setLayoutParams(layoutParams);
                    layoutParamsContent.height=600;
                    layoutParamsContent.width=600;
                    getBitmapFromSharedPreferences();
                    holder.ivContent.setLayoutParams(layoutParamsContent);
                    holder.ivContent.setVisibility(View.VISIBLE);

                    break;
                case "2":       //链接模式
                    holder.tvContent.setText((String) SharepreferenceUtil.readStrFromPre("postStr"));
                    layoutParams.height = 400;
                    holder.relaParent.setLayoutParams(layoutParams);
                    layoutParamsContent.height=200;
                    layoutParamsContent.width=600;
                    getBitmapFromSharedPreferences();
                    holder.ivContent.setLayoutParams(layoutParamsContent);
                    holder.ivContent.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }

//
//            ViewGroup.LayoutParams layoutParams = holder.relaParent.getLayoutParams();
//            layoutParams.height=400;
//            holder.relaParent.setLayoutParams(layoutParams);
        }
//        if (SharepreferenceUtil.readStrFromPre("postStr") != null && !SharepreferenceUtil.readStrFromPre("postStr").equals("")) {
//            holder.tvContent.setText((String) SharepreferenceUtil.readStrFromPre("postStr"));
//        }
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
        @BindView(R.id.iv_content)
        ImageView ivContent;
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
        //链接模式才能开启webview
        if (mItemClickListener != null && SharepreferenceUtil.readStrFromPre("model").equals("2")) {
            mItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void setmItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setmGotoClikcListener(OnGotoClickListener monGotoClickListener) {
        onGotoClickListener = monGotoClickListener;
    }
    //从SharedPreferences获取图片
    private  void getBitmapFromSharedPreferences() {
        //第一步:取出字符串形式的Bitmap
        String imageString = SharepreferenceUtil.readStrFromPre("image");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
            ivcontent.setImageResource(R.mipmap.ic_launcher);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            ivcontent.setImageBitmap(bitmap);
        }
    }
}