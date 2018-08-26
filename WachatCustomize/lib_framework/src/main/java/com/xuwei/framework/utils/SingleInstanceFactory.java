package com.xuwei.framework.utils;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例对象工厂，用于创建指定类的单例对象
 *
 * @author xw  2018/5/12
 */
public class SingleInstanceFactory {
    /** 所有单例对象的集合 */
    private static final Map<Class<?>, Object> instanceMap = new HashMap<>();

    /**
     * 取泛型类 “T” 的单例对象，
     *
     * 注意：T必须有一个显式的无参构造函数！！
     *
     * 而且应该是私有的，不然可以new实例
     *
     * @param clazz
     * @return
     */
    synchronized public static <T> T getInstance(Class<? extends T> clazz) {
        Object object = instanceMap.get(clazz);
        if (object == null) {
            try {
                Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                T t = constructor.newInstance();
                instanceMap.put(clazz, t);
                return t;
            } catch (Exception e) {
                Log.e("SingleInstanceFactory", e.getMessage(), e);
            }
        }
        return clazz.cast(object);
    }
}
