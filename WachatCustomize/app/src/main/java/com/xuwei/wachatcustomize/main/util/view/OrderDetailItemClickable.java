package com.xuwei.wachatcustomize.main.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuwei.wachatcustomize.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * While writing this code, only God and I know what it is.
 * And now only God knows it
 * <p>
 * Created by xuweijie
 * on 2018/7/27 0027.
 */
public class OrderDetailItemClickable extends LinearLayout {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.image_Message)
    ImageView imageMessage;

    /**
     * Instantiates a new Order detail item clickable.
     *
     * @param context the context
     */
    public OrderDetailItemClickable(Context context) {
        super(context);
        initView();
    }

    public OrderDetailItemClickable(Builder builder) {
        super(builder.context);
        inflate(getContext(), R.layout.common_listitem, this);
        ButterKnife.bind(this);
        ivLeft.setBackgroundResource(builder.imageLeft);
        tv.setText(builder.tv);
        imageMessage.setBackgroundResource(builder.imageRight);
    }
    /**
     * Instantiates a new Order detail item clickable.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public OrderDetailItemClickable(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * Instantiates a new Order detail item clickable.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public OrderDetailItemClickable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * Init view.
     */
    void initView() {
        inflate(getContext(), R.layout.common_listitem, this);
        ButterKnife.bind(this);
    }


    /**
     * Init.
     *
     * @param imageLeft  the image left  左图片资源
     * @param title      the title  文字
     * @param imageRight the image right    右图片资源
     */
    public void init(int imageLeft,String title,int imageRight) {
        tv.setText(title);
    }

    /**
     * Set title color.
     * <p>
     * 设置标题字体颜色
     *
     * @param color the color   int颜色
     */
    public void setTitleColor(int color) {
        tv.setTextColor(color);
    }

    /**
     * Set content color.
     * <p>
     * 设置内容字体颜色
     *
     * @param color the color       int颜色
     */
    public void setContentColor(int color) {
        tv.setTextColor(color);
    }

    /**
     * Set tv content.
     * <p>
     * 设置内容
     *
     * @param str the str
     */
    public void setTvContent(String str) {
        tv.setText(str);
    }

    /**
     * Get tv content string.
     * <p>
     * 获取内容
     *
     * @return the string
     */
    public String getTvContent() {
        return tv.getText().toString().trim();
    }


    //Builder模式初始化控件
    public static class Builder {
        Context context;
        int imageLeft;
        String tv;
        int imageRight;
        public Builder setContext(Context context){
            this.context=context;
            return this;
        }
        public Builder setImageLeft(int source){
            this.imageLeft=source;
            return this;
        }
        public Builder setText(String text){
            this.tv=text;
            return this;
        }
        public Builder setImageRight(int source){
            this.imageRight=source;
            return this;
        }
        public  OrderDetailItemClickable build(){
            return new OrderDetailItemClickable(this);
        }
    }
}
