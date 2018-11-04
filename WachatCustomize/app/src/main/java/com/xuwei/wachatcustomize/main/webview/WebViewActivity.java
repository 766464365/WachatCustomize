package com.xuwei.wachatcustomize.main.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xuwei.framework.utils.ToastUtils;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.util.SharepreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.iv_bottom_left)
    ImageView ivBottomLeft;
    @BindView(R.id.iv_bottom_right)
    ImageView ivBottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initWebView();
    }

    private void initWebView() {
        String a=SharepreferenceUtil.readStrFromPre("postUrl");
        if(a!=null&&!a.equals("")){
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new WebViewClient());
            webview.loadUrl(a);
        }
        else {
            ToastUtils.getInstance(WebViewActivity.this).show("其他错误");
            finish();
        }
    }

    @OnClick({R.id.iv_left, R.id.iv_right, R.id.iv_bottom_left, R.id.iv_bottom_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_right:
                break;
            case R.id.iv_bottom_left:
                webview.goBack();
                if(!webview.canGoBack()){
                    ToastUtils.getInstance(this).show("已经到底啦");
                }
                break;
            case R.id.iv_bottom_right:
                webview.goForward();
                if(!webview.canGoForward()){
                    ToastUtils.getInstance(this).show("已经到尽头啦");
                }
                break;
        }
    }
}
