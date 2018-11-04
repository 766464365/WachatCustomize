package com.xuwei.wachatcustomize.main.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharepreferenceUtil {
    public static SharepreferenceUtil sharepreferenceUtilInstance;
    private SharepreferenceUtil(){};
    public static SharepreferenceUtil getSpInstance(){
        if(sharepreferenceUtilInstance==null){
            synchronized (SharepreferenceUtil.class){
                if (sharepreferenceUtilInstance==null){
                    sharepreferenceUtilInstance=new SharepreferenceUtil();
                }
            }
        }
        return sharepreferenceUtilInstance;
    }
    //读取指定key的字符串型
    public static String readStrFromPre(String key) {
        Context context=MyApplication.getContext();
        SharedPreferences sharedPreferences =MyApplication.getContext().getSharedPreferences("settingInfo",
                MODE_PRIVATE);
        String string= sharedPreferences.getString(key, "");
        return string;
    }

    //读取指定值的状态
    public static boolean readStatusFromPre(String key){
        Context context=MyApplication.getContext();
        SharedPreferences sharedPreferences =MyApplication.getContext().getSharedPreferences("settingInfo",
                MODE_PRIVATE);
        boolean status= sharedPreferences.getBoolean(key, false);
        return status;
    }

    public static void saveStrToPre(String key, String string) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("settingInfo",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, string);
        editor.commit();
    }
    public static void saveStatusToPre(String key,boolean status){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("settingInfo",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, status);
        editor.commit();
    }

//    public static void deleteToPre(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo",
//                MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();
//    }

}
