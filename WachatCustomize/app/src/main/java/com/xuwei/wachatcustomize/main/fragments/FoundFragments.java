package com.xuwei.wachatcustomize.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuwei.framework.utils.ToastUtils;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.post.PostActivity;
import com.xuwei.wachatcustomize.main.util.view.OrderDetailItemClickable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FoundFragments extends Fragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.list_item_post)
    OrderDetailItemClickable listItemPost;
    Unbinder unbinder;
    @BindView(R.id.list_item_scan)
    OrderDetailItemClickable listItemScan;
    @BindView(R.id.list_item_mini)
    OrderDetailItemClickable listItemMini;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_found, null);
        unbinder = ButterKnife.bind(this, view);
        initview();
        return view;
    }

    private void initview() {
        OrderDetailItemClickable.Builder builder = new OrderDetailItemClickable.Builder();
        listItemPost = builder.setContext(getContext()).setText("朋友圈").build();
        listItemScan.init(R.color.bg_662faeed, "扫一扫", R.color.bg_303030);
        listItemMini.init(R.color.bg_662faeed, "小程序", R.color.bg_303030);

//        listItemPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.getInstance(getContext()).show("111");
//                startActivity(new Intent(getContext(), PostActivity.class));
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.list_item_post, R.id.list_item_scan, R.id.list_item_mini})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list_item_post:
                ToastUtils.getInstance(getContext()).show("111");
                startActivity(new Intent(getContext(), PostActivity.class));
                break;
            case R.id.list_item_scan:
                break;
            case R.id.list_item_mini:
                break;
        }
    }
}
