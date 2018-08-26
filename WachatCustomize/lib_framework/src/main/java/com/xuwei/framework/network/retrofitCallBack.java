package com.xuwei.framework.network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * 封装的callBack抽象类，可以在此对返回结果做一些统一的处理，目前只输出了错误日志，
 * 所有的callback应该实现此类
 *
 * @author xw  2018/5/6
 */
public abstract class retrofitCallBack<T> implements Callback<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // 这里可以对错误做统一的处理，目前只输出错误日志，子类可以重写此方法作进一步处理
        String url = call.request().url().toString();
        Log.e("retrofitCallBack", t.getMessage() + "\nurl = [" + url + "]", t);
    }
}
