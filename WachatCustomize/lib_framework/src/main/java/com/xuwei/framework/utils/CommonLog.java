package com.xuwei.framework.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;

/**
 * 对com.orhanobut.logger.Logger做了一下包装，
 * 因为SDK中有多个名叫"Logger"的类，使用时要选择包名，不方便，
 * 所以封装一个XbdLogger来专用
 *
 * @author xw  2018/5/30
 */
public class CommonLog {
    /**
     * Given tag will be used as tag only once for this method call regardless of the tag that's
     * been
     * set during initialization. After this invocation, the general tag that's been set will
     * be used for the subsequent log calls
     */
    public static Printer t(@Nullable String tag) {
        return Logger.t(tag);
    }

    /**
     * General log function that accepts all configurations as parameter
     */
    public static void log(int priority, @Nullable String tag, @Nullable String message,
                           @Nullable Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message, args);
    }

    public static void d(@Nullable Object object) {
        Logger.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(null, message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable
        Object... args) {
        Logger.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        Logger.xml(xml);
    }

}
