package com.xuwei.framework.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 公共工具类
 *
 * @author xw  2018/5/25
 */
public class CommonUtils {
    /**
     * 将json字符串格式化成逐层缩进的格式
     *
     * @param jsonString
     * @return
     */
    public static String formatJsonString(String jsonString) {
        int JSON_INDENT = 2;
        if (TextUtils.isEmpty(jsonString)) {
            Logger.e("Empty/Null 的json字符串！");
            return "";
        }
        try {
            jsonString = jsonString.trim();
            if (jsonString.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonString);
                String message = jsonObject.toString(JSON_INDENT);
                return message;
            }
            if (jsonString.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonString);
                String message = jsonArray.toString(JSON_INDENT);
                return message;
            }
            Logger.e("非法的json字符串:[" + jsonString + "]");
        } catch (JSONException e) {
            Logger.e("非法的json字符串:[" + jsonString + "]");
        }
        return "";
    }

}
