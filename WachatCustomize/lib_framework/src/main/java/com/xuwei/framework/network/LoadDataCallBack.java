package com.xuwei.framework.network;

import retrofit2.Response;

/**
 * 加载数据回调接口
 *
 * @author xw  2018/5/11
 */
public interface LoadDataCallBack<T> {
    /**
     * 加载数据成功
     *
     * @param response 返回的数据
     */
    void onSuccess(Response<T> response);

    /**
     * 加载数据失败
     *
     * @param throwable 错误信息
     */
    void onError(Throwable throwable);
}
