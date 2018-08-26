package com.xuwei.framework.utils;

import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 日志工具，主要用于日志系统的初始化
 *
 * @author xw  2018/5/25
 */
public class LogUtil {

    /**
     * 初始化日志系统
     *
     * @param isLoggable 是否要打印日志
     */
    public static void init(boolean isLoggable) {
        Logger.addLogAdapter(new XbdLogAdapter(isLoggable));
    }


    private static class XbdLogAdapter extends AndroidLogAdapter {
        /** 是否输出日志 */
        private boolean isLoggable;

        /** 定制TAG为“XBD_LOGGER” */
        private static FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("XBD_LOGGER")
            .build();

        /**
         * 构造方法
         *
         * @param isLoggable 是否要打印日志
         */
        public XbdLogAdapter(boolean isLoggable) {
            super(formatStrategy);
            this.isLoggable = isLoggable;
        }

        @Override
        public boolean isLoggable(int priority, @Nullable String tag) {
            return isLoggable;
        }
    }
}
