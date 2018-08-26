package com.xuwei.framework.popuppicker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 预约上门收件时间选择器
 *
 * @author xw  2018/6/6
 */
public class BookingTimePicker {
    /** 要显示的时间段格式 */
    private static final String FORMAT_TIME = "%1$02d:00-%2$02d:00";

    /** 上门收件时间从9点开始 */
    private static final int BEGIN_BOOKING_TIME=9;

    /** 上门收件时间至20点结束 */
    private static final int END_BOOKING_TIME=20;

    private Activity mActivity;

    /** 预约完成监听器 */
    private OnBookingTimePickedListener mBookingTimePickedListener;

    public BookingTimePicker(Activity mActivity, OnBookingTimePickedListener
        bookingTimePickedListener) {
        this.mActivity = mActivity;
        this.mBookingTimePickedListener = bookingTimePickedListener;
    }

    /**
     * 显示预约时间选择器
     */
    public void show() {
        LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return true;
            }

            @NonNull
            @Override
            public List<String> provideFirstData() {
                ArrayList<String> firstList = new ArrayList<>();
                firstList.add("小件当日达");
                firstList.add("专车定时达");
                firstList.add("实惠顺风车");
                return firstList;
            }

            @NonNull
            @Override
            public List<String> provideSecondData(int firstIndex) {
                switch (firstIndex) {
                    case 0:
//                        //今天
//                        // 从当前时间的下一个小时开始预约
//                        Calendar calendar = Calendar.getInstance();
//                        // hour的值从 0~23
//                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//                        List<String> todayList = buildSecondList(hour + 1, END_BOOKING_TIME);
//                        todayList.add(0, "半小时内上门");
//                        return todayList;
                        List<String> smallList=new ArrayList<>();
                        smallList.add("首重1KG/10元 \n" + "续重1KG/1元");
                        return smallList;
                    case 1:
                        List<String> specialList=new ArrayList<>();
                        specialList.add("面包车");
                        specialList.add("四米火车");
                        return specialList;
                    case 2:
//                        // 明天跟后天是一样的时间段
//                        return buildSecondList(BEGIN_BOOKING_TIME, END_BOOKING_TIME);
                        List<String> cheapList=new ArrayList<>();
                        cheapList.add("面包车");
                        cheapList.add("四米二厢式货车 60元/5公里\n" + "续3.5元/公里");
                        return cheapList;
                    default:
                        break;
                }
                return null;
            }

            @Nullable
            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return null;
            }

        };
        LinkagePicker picker = new LinkagePicker(mActivity, provider);
        picker.setCycleDisable(true);
        picker.setDividerConfig(null);          //设置选项隐藏分割线
        picker.setTopLineVisible(false);        //设置顶部下划线是否显示
        picker.setLineSpaceMultiplier(2);        //设置弹窗大小
        float a = (float) 0.2;
        float b = (float) 0.8;
        float c = (float) 0;
        picker.setColumnWeight(a,b,c);

        picker.setUseWeight(true);
        // picker.setLabel("小时制", "点");
        picker.setSelectedIndex(0, 0);
        //picker.setSelectedItem("12", "9");
        picker.setContentPadding(10, 0);
        picker.setOnStringPickListener(new LinkagePicker.OnStringPickListener() {
            @Override
            public void onPicked(String first, String second, String third) {
                String lable = first + " " + second;
                String bookingTime = "";
                if (mBookingTimePickedListener != null) {
                    mBookingTimePickedListener.onBookingTimePicked(lable, bookingTime);
                }
            }
        });
        picker.show();
    }

    /**
     * 构建可预约的时间段
     *
     * @param begin 起始时间，单位：时
     * @param end 结束时间，单位：时
     * @return
     */
    private List<String> buildSecondList(int begin, int end) {
        List<String> list = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            String str = String.format(FORMAT_TIME, i, i + 1);
            list.add(str);
        }
        return list;
    }

    /**
     * 预约上门时间选择监听器
     *
     * @author xw 2018/6/6
     */
    public interface OnBookingTimePickedListener {
        /**
         * 预约时间已选定
         *
         * @param label 展示在界面上的文字，如：今天12:00-13:00
         * @param bookingTime 上传到服务端的预约时间，格式待定
         */
        void onBookingTimePicked(String label, String bookingTime);
    }
}
