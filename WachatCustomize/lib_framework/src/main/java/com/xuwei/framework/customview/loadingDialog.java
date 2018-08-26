package com.xuwei.framework.customview;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.xuwei.framework.R;

/**
 * 统一风格的Loading框，所有Loading框都应该使用本类来显示和关闭
 *
 * @author xw  2018/5/17
 */
public class loadingDialog {
    private static final String TAG = "loadingDialog";
    private static Dialog mDialog;

    /**
     * 显示Loading框
     *
     * @param activity
     */
    public static void show(Activity activity) {
        if (mDialog != null) {
            Log.d(TAG, "dialog已显示，不能重复显示。");
        } else {
            createDialog(activity).show();
        }
    }

    /**
     * 关闭Loading框
     */
    public static void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            Log.d(TAG, "dialog已关闭");
        } else {
            Log.d(TAG, "没用正在显示的dialog");
        }
        mDialog = null;
    }

    private static Dialog createDialog(Activity activity) {
        mDialog = new Dialog(activity, R.style.LoadingDialog);
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.loading_dialog);
        return mDialog;
    }
}
