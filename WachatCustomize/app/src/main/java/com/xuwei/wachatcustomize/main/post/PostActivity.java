package com.xuwei.wachatcustomize.main.post;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xuwei.framework.base.BaseActivity;
import com.xuwei.framework.recyclerview.SpaceItemDecoration;
import com.xuwei.framework.utils.ToastUtils;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.post.adapter.PostAdapter;
import com.xuwei.wachatcustomize.main.util.SharepreferenceUtil;
import com.xuwei.wachatcustomize.main.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends BaseActivity implements PostAdapter.OnItemClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    LinearLayoutManager mLayoutManager;
    PostAdapter postAdapter;
    @BindView(R.id.cover)
    ImageView cover;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_hint)
    TextView tvHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        initView();
        initRecyclerview();
        setStatsBarColor(R.color.bg_C1C1C1);       //设置标题栏颜色
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, tvHint.getBottom());
            }
        },200);         //scrollview重新定位需要在加载完view之后才能进行，使用了延时
    }

    private void initView() {

    }

    private void initRecyclerview() {
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new SpaceItemDecoration(getResources()
                .getDimensionPixelOffset(R.dimen.y30)));
        postAdapter = new PostAdapter(this);
        postAdapter.setmItemClickListener(this);
        rv.setAdapter(postAdapter);

    }


    @Override
    public void onItemClick(View v, int position) {
        ToastUtils.getInstance(this).show("颠倒了");
        if (position == 0 && SharepreferenceUtil.readStatusFromPre("urlAble") == true) {
//            Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("http://www.baidu.com"));
//            startActivity(intent);
            startActivity(new Intent(PostActivity.this, WebViewActivity.class));
        }
    }
}
