package com.xuwei.framework.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * 所有Activity的基类，处理了一些公共的事件
 *
 * @author xw  2018/5/29
 */
public abstract class BaseActivity extends AppCompatActivity {
    /** 是否注册EventBus */
    private boolean isRegisterEventBus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // 设置fitsSystemWindows参数为true
        setFitsSystemWindows(true);
    }

    /**
     * 注册EventBus，子类必须要在onCreare()方法中调用才能生效
     */
    protected void registerEventBus(){
        isRegisterEventBus = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isRegisterEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        if (isRegisterEventBus) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    /**
     * 设置状态栏颜色
     *
     * @param resourceId 颜色资源id
     */
    protected void setStatsBarColor(int resourceId) {
        // 1,先将状态栏设置为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                localLayoutParams.flags);
        }

        // 2,再设置颜色
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(resourceId);
    }

    /**
     * 设置fitsSystemWindows参数，等同于在布局中设置 android:fitsSystemWindows="true/false"
     */
    protected void setFitsSystemWindows(boolean isFitsSystemWindows) {
        ViewGroup parent = findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(isFitsSystemWindows);
                ((ViewGroup) childView).setClipToPadding(isFitsSystemWindows);
            }
        }
    }
}
