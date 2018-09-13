package com.xuwei.wachatcustomize.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuwei.wachatcustomize.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StatementFragment extends Fragment {


    // @BindView(R.id.rvOrders)
    // RecyclerView rvOrders;
    // @BindView(R.id.tvNoOrder)
    // TextView tvNoOrder;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
