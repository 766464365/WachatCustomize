package com.xuwei.framework.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
//自定义recyclerview之间的间隔
/**
 * While writing this code, only God and I know what it is.
 * And now only God knows it
 * <p>
 * Created by xuweijie
 * on 2018/8/16 0016.
 *
 * recyclerview的间隔装饰器，使用方法  recyclerview.addItemDecoration(new SpaceItemDecoration(传值))
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    /** 间距 px */
    int mSpace;

    public SpaceItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mSpace;
        }
    }
}
