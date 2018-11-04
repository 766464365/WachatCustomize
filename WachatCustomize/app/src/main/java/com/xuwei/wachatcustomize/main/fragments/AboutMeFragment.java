package com.xuwei.wachatcustomize.main.fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.post.PostActivity;
import com.xuwei.wachatcustomize.main.setting.SettingActivity;
import com.xuwei.wachatcustomize.main.util.view.OrderDetailItemClickable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AboutMeFragment extends Fragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.iv_avator)
    ImageView ivAvator;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R.id.list_item_pocket)
    OrderDetailItemClickable listItemPocket;
    @BindView(R.id.list_item_favorite)
    OrderDetailItemClickable listItemFavorite;
    @BindView(R.id.list_item_post)
    OrderDetailItemClickable listItemPost;
    @BindView(R.id.list_item_setting)
    OrderDetailItemClickable listItemSetting;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutme, null);
        unbinder = ButterKnife.bind(this, view);
        setOnclickView();
        return view;
    }

    private void setOnclickView() {
        ImageView imagePocketLeft=(ImageView)listItemPocket.findViewById(R.id.iv_left);
        TextView tvPocket=(TextView)listItemPocket.findViewById(R.id.tv) ;
        tvPocket.setText("钱包");
        ImageView imagePocketRight=(ImageView)listItemPocket.findViewById(R.id.image_Message);
        imagePocketRight.setVisibility(View.GONE);
        ImageView imagePostLeft=(ImageView)listItemPost.findViewById(R.id.iv_left);
        TextView tvPost=(TextView)listItemPost.findViewById(R.id.tv);
        tvPost.setText("相册");
        ImageView imagePostRight=(ImageView)listItemPost.findViewById(R.id.image_Message);
        imagePostRight.setVisibility(View.GONE);
        ImageView imageFavouriteLeft=(ImageView)listItemFavorite.findViewById(R.id.iv_left);
        TextView tvFavourite=(TextView)listItemFavorite.findViewById(R.id.tv);
        ImageView imageFacouriteRight=(ImageView)listItemFavorite.findViewById(R.id.image_Message);
        tvFavourite.setText("收藏");
        imageFacouriteRight.setVisibility(View.GONE);
        ImageView imageSetLeft=(ImageView)listItemSetting.findViewById(R.id.iv_left);
        TextView tvSet=(TextView)listItemSetting.findViewById(R.id.tv);
        ImageView imageSetRight=(ImageView)listItemSetting.findViewById(R.id.image_Message);
        tvSet.setText("设置");
        imageSetRight.setVisibility(View.GONE);
        listItemSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
