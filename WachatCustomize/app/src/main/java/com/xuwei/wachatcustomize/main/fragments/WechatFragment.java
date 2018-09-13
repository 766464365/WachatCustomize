package com.xuwei.wachatcustomize.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuwei.framework.recyclerview.SpaceItemDecoration;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.fragments.adapter.WechatAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WechatFragment extends Fragment implements WechatAdapter.OnItemClickListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    LinearLayoutManager mLayoutManager;
    WechatAdapter wechatAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new SpaceItemDecoration(getResources()
                .getDimensionPixelOffset(R.dimen.y30)));
        wechatAdapter = new WechatAdapter(getActivity());
        wechatAdapter.setmItemClickListener(this);
        rv.setAdapter(wechatAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
