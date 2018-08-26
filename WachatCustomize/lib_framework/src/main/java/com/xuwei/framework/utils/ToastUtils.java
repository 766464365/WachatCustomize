package com.xuwei.framework.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * While writing this code, only God and I know what it is.
 * And now only God knows it
 * <p>
 * Created by xuweijie
 * on 2018/8/17 0017.
 */
public class ToastUtils {
    private static ToastUtils instance;
    private Toast toast;
    private View view;
    private ToastUtils(Context context){
        toast=new Toast(context);
        view=Toast.makeText(context,"",Toast.LENGTH_SHORT).getView();
        toast.setView(view);
    }
    public static ToastUtils getInstance(Context context){
        if(instance==null){
            synchronized (ToastUtils.class){
                if(instance==null){
                    instance=new ToastUtils(context);
                }
                return instance;
            }
        }
        return instance;
    }
    public void show(String msg){
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
}
