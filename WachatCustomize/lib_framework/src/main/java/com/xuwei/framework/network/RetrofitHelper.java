package com.xuwei.framework.network;

import com.xuwei.framework.utils.CommonUtils;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 对Retrofit2的封装，主要使用了单例的okHttpClient，
 * 并暴露出了okHttpClient，以便对okHttpClient进行定制
 *
 * @author xw 2018/5/3
 */
public class RetrofitHelper {
    /** 连接超时：10秒 */
    public static final int TIME_OUT_CONNECT = 10 * 1000;

    /** 读数据超时：10秒 */
    public static final int TIME_OUT_READ = 10 * 1000;

    /** 转换日志输出格式 */
    private static HttpLoggingInterceptor.Logger mLogger = new XbdLogger();

    private static OkHttpClient mOkHttpClient;

    /**
     * 获取带有json转换器的Retrofit对象，如果不需要json转换器请使用
     * buildRetrofit(String baseUrl, boolean isNeedGsonConverter)方法获取
     *
     * @author xw 2018/5/6
     */
    public static Retrofit buildRetrofit(String baseUrl) {
        return buildRetrofit(baseUrl, true);
    }

    /**
     * 构建统一封装的Retrofit对象
     *
     * @param baseUrl
     * @param isNeedGsonConverter 是否需要gson转换器
     * @return Retrofit对象
     */
    public static Retrofit buildRetrofit(String baseUrl, boolean isNeedGsonConverter) {
        Retrofit.Builder builder = new Retrofit.Builder()
            .client(getSingleOkHttpClient())
            .baseUrl(baseUrl);
        if (isNeedGsonConverter) {
            builder.addConverterFactory(GsonConverterFactory.create());
        }
        return builder.build();
    }

    /**
     * 获取OkHttpClient对象，用于需要定制OkHttpClient的情况。
     * 该对象已经做了基本的封装，使用者可以在此基础上做进一步定制。
     * 用法：
     * Retrofit retrofit = RetrofitHelper.buildRetrofit("baseUrl");
     * OkHttpClient okHttpClient = RetrofitHelper.getmOkHttpClient();
     * ... // 对okHttpClient做一些个性化定制
     * retrofit = retrofit.newBuilder().client(okHttpClient).build(); //重新设置定制好的client
     * ... //继续后续操作
     * <p>
     * 通常情况下不需要获取OkHttpClient对象，直接使用buildRetrofit()方法返回的Retrofit对象即可。
     *
     * @return
     */
    public static OkHttpClient getmOkHttpClient() {
        return buildClient();
    }

    /**
     * 获取单例的okHttpClient对象
     *
     * @return
     */
    private synchronized static OkHttpClient getSingleOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = buildClient();
        }
        return mOkHttpClient;
    }

    /**
     * 构建统一封装的OkHttpClient对象，
     * 该对象增加了日志拦截器及连接超时等参数
     *
     * @return
     */
    private static OkHttpClient buildClient() {
        // 日志拦截器
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(mLogger);
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
            .connectTimeout(TIME_OUT_CONNECT, TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT_READ, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(logInterceptor);
        return builder.build();
    }

    /**
     * 将http报文中的json字符串格式化并使用com.orhanobut.logger.Logger日志系统打印日志的类
     *
     * @author xw 2018/5/25
     */
    private static class XbdLogger implements HttpLoggingInterceptor.Logger {
        StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 打印请求或者响应报文中的每一行都会调用此方法，所以用一个StringBuilder把它们串起来

            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            boolean isJsonStr = (message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"));
            if (isJsonStr) {
                message = CommonUtils.formatJsonString(message);
            }
            mMessage.append(message.concat("\n"));
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Logger.d(mMessage.toString());
                mMessage.setLength(0);
            }
        }
    }
}
