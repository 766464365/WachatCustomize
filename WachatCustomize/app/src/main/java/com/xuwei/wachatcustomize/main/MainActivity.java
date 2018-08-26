package com.xuwei.wachatcustomize.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xuwei.wachatcustomize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_like)
    Button btnLike;
    @BindView(R.id.btn_forward)
    Button btnForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_like, R.id.btn_forward})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_like:
                break;
            case R.id.btn_forward:
                break;
        }
    }
}
